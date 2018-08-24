package com.thomsonreuters.extractvalidator.util;


import java.net.URI;

import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;

import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.dto.determination.AuthorityDto;
import com.thomsonreuters.extractvalidator.dto.determination.UiAuthorityList;
import com.thomsonreuters.extractvalidator.dto.determination.UiAuthorityTypeList;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompanyList;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompanyRelatedGroupsHolder;
import com.thomsonreuters.extractvalidator.dto.determination.UiLookupList;
import com.thomsonreuters.extractvalidator.dto.determination.UiXmlElement;
import com.thomsonreuters.extractvalidator.dto.determination.UiZoneList;


/**
 * Implements outbound rest service requests to Determination.
 *
 * @author Neal Schultz
 */
@Component(DeterminationRestClient.BEAN_NAME)
@Data
public class DeterminationRestClient
{
	/**
	 * Denotes the bean name for this component
	 */
	public static final String BEAN_NAME = "determinationRestClient";

	/**
	 * Logging handle for this class
	 */
	private static final Logger LOG = ESAPI.getLogger(DeterminationRestClient.class);

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
	private String determinationBaseUrl;

	/**
	 * The path to Determination rest service: companies
	 */
	static final String REST_SERVICE_URI_COMPANIES = "/sabrix/services/rest/companies/";

	/**
	 * The path to Determination rest service: zones
	 */
	static final String REST_SERVICE_URI_ZONES = "/sabrix/services/rest/zones/";

	/**
	 * The path to determination rest service: authorities/all
	 */
	static final String REST_SERVICE_URI_AUTHORITIES_ALL = "/sabrix/services/rest/authorities/all";

	/**
	 * The path to determination rest service: authority types
	 */
	static final String REST_SERVICE_URI_AUTHORITY_TYPES = "/sabrix/services/rest/authoritytypes/";

	/**
	 * The path to determination rest service: lookups
	 */
	static final String REST_SERVICE_URI_LOOKUPS = "/sabrix/services/rest/lookups/";

	/**
	 * The path to determination rest service: xmlelements
	 */
	static final String REST_SERVICE_URI_XML_ELEMENTS = "/sabrix/services/rest/xmlelements/";

	/**
	 * The path to determination rest service: authorization roles
	 */
	static final String REST_SERVICE_URI_AUTHORIZATION_ROLES = "/sabrix/services/internal/v1/authorization/roles/";

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
	DeterminationRestClient(final RestTemplate restTemplate)
	{
		this.restTemplate = restTemplate;
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


	/**
	 * Creates Http Headers including content-type, and authorization set.
	 *
	 * @param authorization The authorization header value to set.
	 *
	 * @return The Http Headers that were setup.
	 */
	private HttpHeaders createHttpHeaders2(final String authorization)
	{
		final HttpHeaders httpHeaders = new HttpHeaders();

		httpHeaders.add("content-type", HDR_VALUE_CONTENT_TYPE);
		httpHeaders.add("authorization", authorization);

		return httpHeaders;
	}


	/**
	 * Makes rest service call to Determination's findCompanies service.
	 *
	 * @param authorization The authorization info to use for the request.
	 *
	 * @return The result of the rest service call.
	 */
	public UiCompanyList findCompanies(final String authorization)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_COMPANIES);
		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiCompanyList> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiCompanyList.class
		);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's findCompanyRelatedGroups service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param companyId The company id to pass to the service as a path param.
	 *
	 * @return The result of the rest service call.
	 */
	public UiCompanyRelatedGroupsHolder findCompanyRelatedGroups(final String authorization, final Long companyId)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_COMPANIES).path(companyId.toString()).path("/companyrelatedgroups");
		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiCompanyRelatedGroupsHolder> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiCompanyRelatedGroupsHolder.class
		);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's findCountries service.
	 *
	 * @param authorization The authorization info to use for the request.
	 *
	 * @return The result of the rest service call.
	 */
	public UiZoneList findCountries(final String authorization)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_ZONES).path("/countries");
		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiZoneList> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiZoneList.class
		);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's findAllAuthorities service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param companyId The company id to pass to the service as a query parameter.
	 * @param zoneId The zone id to pass to the service as a query parameter.
	 *
	 * @return The result of the rest service call.
	 */
	public UiAuthorityList findAllAuthorities(final String authorization, final Long companyId, final Long zoneId)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_AUTHORITIES_ALL)
													 .queryParam("companyId", companyId)
													 .queryParam("zoneId", zoneId);
		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiAuthorityList> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiAuthorityList.class
		);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's findAuthorityTypes service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param companyId The company id to pass to the service as a query parameter.
	 *
	 * @return The result of the rest service call.
	 */
	public UiAuthorityTypeList findAuthorityTypes(final String authorization, final Long companyId)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_AUTHORITY_TYPES)
													 .queryParam("companyId", companyId);
		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiAuthorityTypeList> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiAuthorityTypeList.class
		);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's findLookup service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param codeGroup The code group to lookup. Passed to the service as a query parameter.
	 *
	 * @return The result of the rest service call.
	 */
	public UiLookupList findLookup(final String authorization, final String codeGroup)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_LOOKUPS)
													 .queryParam("codeGroup", codeGroup);
		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiLookupList> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiLookupList.class
		);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's findXmlElements service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param companyId The company id to pass to the service as a path param.
	 *
	 * @return The result of the rest service call.
	 */
	public UiXmlElement[] findXmlElements(final String authorization, final Long companyId)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_XML_ELEMENTS)
													 .path("companies")
													 .path("/")
													 .path(companyId.toString())
													 .queryParam("inOutFlag", "F")
													 .queryParam("leafNode", "Y");
		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<UiXmlElement[]> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiXmlElement[].class
		);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's authorization roles service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param userName The username to pass to the service as a path param.
	 *
	 * @return The result of the rest service call.
	 *
	 * @throws org.springframework.web.client.RestClientException When something went wrong with the request.
	 */
	public AuthorityDto isAuthorized(final String authorization, final String userName)
	{
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(determinationBaseUrl).path(REST_SERVICE_URI_AUTHORIZATION_ROLES)
													 .path(userName);
		final URI uri = builder.build().encode().toUri();

		LOG.trace(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri);

		final ResponseEntity<AuthorityDto> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders2(authorization)),
				AuthorityDto.class
		);

		LOG.trace(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}
}
