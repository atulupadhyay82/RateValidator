package com.thomsonreuters.extractvalidator.util;


import java.net.URI;
import java.util.List;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.dto.extract.config.ExtractDefinition;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;


/**
 * ExtractRestClient Description.
 *
 * @author Matt Godsey
 */
@Component(ExtractRestClient.BEAN_NAME)
@Data
public class ExtractRestClient
{
	/**
	 * Denotes the bean name for this component
	 */
	public static final String BEAN_NAME = "extractRestClient";

	/**
	 * Logging handle for this class
	 */
	private static final Logger LOG = ESAPI.getLogger(ExtractRestClient.class);

	/**
	 * For log message.
	 */
	private static final String MAKING_OUTBOUND_REST_SERVICE_CALL_URI = "Making outbound rest service call, uri=";

	/**
	 * For log message.
	 */
	private static final String REST_SERVICE_CALL_COMPLETE = "Rest service call complete.";

	/**
	 * Holds value of determinations companies rest service url
	 */
	private String extractBaseUrl;

	/**
	 * The path to Determination rest service: companies
	 */
	static final String REST_SERVICE_URI_CONTENT_EXTRACT = "/taxtreatments/company/{company}/extractName/{extractName}";

	/**
	 * The path to Determination rest service: zones
	 */
	static final String REST_SERVICE_URI_EXTRACT_DEFINITIONS = "/contentExtractService/extract/";

	/**
	 * Used for both Content-Type and Accept header values.
	 */
	private static final String TRI_ONESOURCE_IDT_JSON = "application/vnd.tri.onesource.idt+json";

	/**
	 * Used for Content-Type header value.
	 */
	static final String HDR_VALUE_CONTENT_TYPE = TRI_ONESOURCE_IDT_JSON;

	/**
	 * Used for Accept header value.
	 */
	static final String HDR_VALUE_ACCEPT = TRI_ONESOURCE_IDT_JSON;


	/**
	 * The rest template used for outbound rest service calls to Determination.
	 */
	private RestTemplate restTemplate;


	/**
	 * Constructs a DeterminationRestClient.
	 *
	 * @param restTemplate The rest template used for outbound rest service calls to Determination.
	 */
	@Autowired
	ExtractRestClient(final RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
	}


	/**
	 * Sets the value of Content Extract's base rest service url.
	 *
	 * @param testRunData The test run data which contains Content Extract's base rest service url. This must not be {@code null}.
	 */
	public void setExtractBaseUrl(final TestRun testRunData)
	{
		extractBaseUrl = testRunData.getContentExtractBaseUrl();
	}


	/**
	 * Creates Http Headers including accept, content-type, and authorization set.
	 *
	 * @param authorization The authorization header value to set.
	 *
	 * @return The Http Headers that were setup.
	 */
	private HttpHeaders createHttpHeaders(final String authorization)
	{
		final HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("accept", HDR_VALUE_ACCEPT);
		httpHeaders.add("content-type", HDR_VALUE_CONTENT_TYPE);
		httpHeaders.add("authorization", authorization);

		return httpHeaders;
	}


	/**
	 * Makes rest service call to Content Extract's fetch extract definitions service.
	 *
	 * @param authorization The authorization info to use for the request.
	 *
	 * @return The result of the rest service call.
	 */
	public List<ExtractDefinition> findExtractDefinitions(final String authorization)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(extractBaseUrl).path(REST_SERVICE_URI_EXTRACT_DEFINITIONS);
		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<List<ExtractDefinition>> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				new ParameterizedTypeReference<List<ExtractDefinition>>(){}
				);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Content Extract's fetch extract service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param testRunData The data provided for this test run.
	 *
	 * @return The result of the rest service call.
	 */
	public ContentExtract findContentExtract(final String authorization, final TestRun testRunData)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(extractBaseUrl).path(REST_SERVICE_URI_CONTENT_EXTRACT);

		builder.queryParam("company", testRunData.getTestCompanyName());
		builder.queryParam("extractName", testRunData.getTestExtractConfigName());

		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<ContentExtract> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				ContentExtract.class
		);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}
}
