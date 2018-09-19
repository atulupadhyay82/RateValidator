package com.thomsonreuters.extractvalidator.util;


import java.net.URI;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;

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
import com.thomsonreuters.extractvalidator.dto.determination.UiCompanyList;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiScenarioResult;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;


/**
 * ExtractRestClient Description.
 *
 * @author Matt Godsey
 */
@Component(ExternalRestClient.BEAN_NAME)
@Data
public class ExternalRestClient
{
	/**
	 * Denotes the bean name for this component
	 */
	public static final String BEAN_NAME = "JsonObjectRestClient";

	/**
	 * Logging handle for this class
	 */
	private static final Logger LOG = ESAPI.getLogger(ExternalRestClient.class);

	/**
	 * For log message.
	 */
	private static final String MAKING_OUTBOUND_REST_SERVICE_CALL_URI = "Making outbound rest service call, uri=";

	/**
	 * For log message.
	 */
	private static final String REST_SERVICE_CALL_COMPLETE = "Rest service call complete.";

	/**
	 * Used for both Content-Type and Accept header values.
	 */
	private static final String TRI_ONESOURCE_IDT_JSON = "application/vnd.tri.onesource.idt+json";

	/**
	 * The path to Determination rest service: companies
	 */
	static final String REST_SERVICE_URI_CONTENT_EXTRACT = "/taxtreatments/company/{company}/extractName/{extractName}";

	/**
	 * The path to Determination rest service: zones
	 */
	static final String REST_SERVICE_URI_EXTRACT_DEFINITIONS = "/contentExtractService/extract/";

	/**
	 * The path to Determination rest service: companies
	 */
	static final String REST_SERVICE_URI_COMPANIES = "/sabrix/services/rest/companies/";

	/**
	 * The path to Determination rest service: create model scenario.
	 */
	static final String REST_SERVICE_URI_CREATE_MOD_SCEN = "/sabrix/services/rest/modelscenarios/companies/{companyId}/scenarios";

	/**
	 * The path to Determination rest service: update model scenario.
	 */
	static final String REST_SERVICE_URI_UPDATE_MOD_SCEN = "/sabrix/services/rest/modelscenarios/companies/{companyId}/scenarios/{scenarioId}";

	/**
	 * The path to Determination rest service: delete model scenarios.
	 */
	static final String REST_SERVICE_URI_DELETE_MOD_SCEN = "/sabrix/services/rest/modelscenarios/delete";

	/**
	 * The path to Determination rest service: run model scenarios.
	 */
	static final String REST_SERVICE_URI_RUN_MOD_SCEN = "/sabrix/services/rest/modelscenarios/companies/{companyId}/scenarios/{scenarioId}/calculate";

	/**
	 * Used for Content-Type header value.
	 */
	static final String HDR_VALUE_CONTENT_TYPE = TRI_ONESOURCE_IDT_JSON;

	/**
	 * Used for Accept header value.
	 */
	static final String HDR_VALUE_ACCEPT = TRI_ONESOURCE_IDT_JSON;

	/**
	 * Holds value of extract rest service url.
	 */
	private String extractBaseUrl;

	/**
	 * Holds value of determination rest service url.
	 */
	private String determinationBaseUrl;

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
	ExternalRestClient(final RestTemplate restTemplate)
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
	 * Sets the value of Determination's base rest service url.
	 *
	 * @param testRunData The test run data which contains Determination's base rest service url. This must not be {@code null}.
	 */
	public void setDeterminationBaseUrl(final TestRun testRunData)
	{
		determinationBaseUrl = testRunData.getDeterminationBaseUrl();
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


	private String createBasicAuthenticationString(final String userName, final String password)
	{
		final String unEncoded = userName + ":" + password;

		return "Basic " + Base64.getEncoder().encodeToString(unEncoded.getBytes());
	}


	/**
	 * Makes rest service call to Content Extract's fetch extract service.
	 *
	 * @param authorization The authorization details for the call.
	 * @param uri The URI for the call.
	 *
	 * @return The result of the rest service call.
	 */
	public ContentExtract findContentExtractEntity(final String authorization, final URI uri)
	{
		final ResponseEntity<ContentExtract> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				ContentExtract.class
		);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Content Extract's fetch extract service.
	 *
	 * @param authorization The authorization details for the call.
	 * @param uri The URI path for the call.
	 *
	 * @return The result of the rest service call.
	 */
	public JsonObject findContentExtractJson(final String authorization, final URI uri)
	{
		final ResponseEntity<JsonObject> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				JsonObject.class
		);

		return responseEntity.getBody();
	}


	/**
	 * Find the content extract and return it as an Object, which will be either a JsonObject or ContentExtract entity based on if the is static flag is set.
	 * Static means the entity is requested.
	 *
	 * @param testRunData The test run data to use to form the call.
	 * @param isStatic The boolean flag to denote if we want the entity returned or a JsonObject returned.
	 *
	 * @return Returns the object of the response which could wrap either a JsonObject or a ContentExtract object.
	 */
	public Object findContentExtract(final TestRun testRunData, final boolean isStatic)
	{
		final String authorization = createBasicAuthenticationString(testRunData.getServiceUser(), testRunData.getServicePassword());
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(extractBaseUrl).path(REST_SERVICE_URI_CONTENT_EXTRACT);
		final Map<String, String> params = new HashMap<>();

		params.put("company", testRunData.getTestCompanyName());
		params.put("extractName", testRunData.getTestExtractConfigName());

		builder.queryParam("loadMethod", "FULL");

		final URI uri = builder.buildAndExpand(params).encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final Object response = isStatic ? findContentExtractEntity(authorization, uri) : findContentExtractJson(authorization, uri);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return response;
	}


	/**
	 * Makes rest service call to Determination's findCompanies service.
	 *
	 * @param testRunData The test run data containing the authorization info to use for the request.
	 *
	 * @return The result of the rest service call.
	 */
	public UiCompanyList findCompanies(final TestRun testRunData)
	{
		final String authorization = AuthUtils.prefixUDSCredentials(testRunData.getUdsToken());
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_COMPANIES);
		final URI uri = builder.build().encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiCompanyList> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiCompanyList.class
		);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	public UiModelScenarioDetail createModelScenario(final TestRun testRunData, final String companyId, final UiModelScenarioDetail modelScenarioDetail)
	{
		final String authorization = AuthUtils.prefixUDSCredentials(testRunData.getUdsToken());
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_CREATE_MOD_SCEN);
		final Map<String, String> params = new HashMap<>();

		params.put("companyId", companyId);

		final URI uri = builder.buildAndExpand(params).encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiModelScenarioDetail> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.POST,
				new HttpEntity<>(modelScenarioDetail, createHttpHeaders(authorization)),
				UiModelScenarioDetail.class
		);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	public UiModelScenarioDetail updateModelScenario(final TestRun testRunData, final String companyId, final UiModelScenarioDetail modelScenarioDetail)
	{
		final String authorization = AuthUtils.prefixUDSCredentials(testRunData.getUdsToken());
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_UPDATE_MOD_SCEN);
		final Map<String, String> params = new HashMap<>();

		params.put("companyId", companyId);
		params.put("scenarioId", modelScenarioDetail.getScenarioId().toString());

		final URI uri = builder.buildAndExpand(params).encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiModelScenarioDetail> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.PUT,
				new HttpEntity<>(modelScenarioDetail, createHttpHeaders(authorization)),
				UiModelScenarioDetail.class
		);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	public void deleteModelScenario(final TestRun testRunData, final List<String> modelScenarioIds)
	{
		final String authorization = AuthUtils.prefixUDSCredentials(testRunData.getUdsToken());
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_DELETE_MOD_SCEN);
		final URI uri = builder.build().encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<String> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.POST,
				new HttpEntity<>(modelScenarioIds, createHttpHeaders(authorization)),
				String.class
		);

		final String empty = responseEntity.getBody();

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);
	}


	/**
	 * Makes rest service call to calculate for a model scenario.
	 *
	 * @param testRunData The data provided for this test run.
	 * @param modelScenarioId The ID of the scenario to run.
	 * @param companyId The ID of the company to run the scenario for.
	 *
	 * @return The result of the rest service call.
	 */
	public UiScenarioResult runModelScenario(final TestRun testRunData, final String modelScenarioId, final String companyId)
	{
		final String authorization = AuthUtils.prefixUDSCredentials(testRunData.getUdsToken());
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_RUN_MOD_SCEN);
		final Map<String, String> params = new HashMap<>();

		params.put("companyId", companyId);
		params.put("scenarioId", modelScenarioId);

		final URI uri = builder.buildAndExpand(params).encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiScenarioResult> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiScenarioResult.class
		);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}
}
