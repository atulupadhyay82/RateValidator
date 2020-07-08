package com.thomsonreuters.extractvalidator.util;


import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.dto.content.ClientZone;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompanyList;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenario;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiScenarioResult;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;
import lombok.Data;
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

import javax.json.JsonObject;
import java.net.URI;
import java.sql.Timestamp;
import java.util.*;


/**
 * Rest client to handle all outbound REST calls to Determination and Content Extract.
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
	private static final String REST_SERVICE_URI_CONTENT_EXTRACT = "/services/rest/taxtreatments/company/{company}/extractName/{extractName}";

	/**
	 * The path to Determination rest service: zones
	 */
	private static final String REST_SERVICE_URI_EXTRACT_DEFINITIONS = "/services/rest/contentExtractService/extract/";

	/**
	 * The path to Determination rest service: companies
	 */
	private static final String REST_SERVICE_URI_COMPANIES = "/sabrix/services/rest/companies/";

	/**
	 * The path to Determination rest service: create model scenario.
	 */
	private static final String REST_SERVICE_URI_CREATE_MOD_SCEN = "/sabrix/services/rest/modelscenarios/companies/{companyId}/scenarios";

	/**
	 * The path to Determination rest service: update model scenario.
	 */
	private static final String REST_SERVICE_URI_UPDATE_MOD_SCEN = "/sabrix/services/rest/modelscenarios/companies/{companyId}/scenarios/{scenarioId}";

	/**
	 * The path to Determination rest service: delete model scenarios.
	 */
	private static final String REST_SERVICE_URI_DELETE_MOD_SCEN = "/sabrix/services/rest/modelscenarios/delete";

	/**
	 * The path to Determination rest service: run model scenarios.
	 */
	private static final String REST_SERVICE_URI_RUN_MOD_SCEN = "/sabrix/services/rest/modelscenarios/companies/{companyId}/scenarios/{scenarioId}/calculate";

	/**
	 * The path to Determination rest service: find all model scenarios that user has access to.
	 */
	private static final String REST_SERVICE_URI_FIND_MOD_SCEN = "/sabrix/services/rest/modelscenarios";

	/**
	 * The path to Determination rest service: find all model scenarios that user has access to.
	 */
	private static final String REST_SERVICE_URI_FIND_COUNTRIES = "/sabrix/services/internal/certificatemanager/contentmanagement/countries";

	/**
	 * Used for Content-Type header value.
	 */
	private static final String HDR_VALUE_CONTENT_TYPE = TRI_ONESOURCE_IDT_JSON;

	/**
	 * Used for Accept header value.
	 */
	private static final String HDR_VALUE_ACCEPT = TRI_ONESOURCE_IDT_JSON;

	/**
	 * Constant to use for replacing company ID in the URI.
	 */
	private static final String COMPANY_ID = "companyId";

	/**
	 * Constant to use for replacing scenario ID in the URI.
	 */
	private static final String SCENARIO_ID = "scenarioId";

	/**
	 * The rest template used for outbound rest service calls to Determination.
	 */
	private RestTemplate restTemplate;

	private static final String UDS_TOKEN_URL="http://10.202.96.120:5011/api/LoneStar/UdsToken";

	private static final Map<String,String> UDS_TOKEN_MAP = new HashMap<>();


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
	 * Create the basic authentication header string.
	 *
	 * @param userName The username to include in the string.
	 * @param password The password to include in the string.
	 *
	 * @return The basic authentication header string encoded correctly.
	 */
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
	private ContentExtract findContentExtractEntity(final String authorization, final URI uri)
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
	private JsonObject findContentExtractJson(final String authorization, final URI uri)
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
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testRunData.getContentExtractBaseUrl()).path(REST_SERVICE_URI_CONTENT_EXTRACT);
		final Map<String, String> params = new HashMap<>();

		params.put("company", testRunData.getTestCompanyName());
		params.put("extractName", testRunData.getTestExtractConfigName());

		builder.queryParam("loadMethod", "FULL");

		final URI uri = builder.buildAndExpand(params).encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri + " findContentExtract");

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
		final String authorization = checkUDSTokenExpiry() ? getUDSToken(testRunData.getEnvCredentialsID(),testRunData.getEnvCredentialsPassword(),testRunData.getEnvironmentMS()) : UDS_TOKEN_MAP.get("UDS_Token");
		LOG.info(Logger.EVENT_UNSPECIFIED, "Fetch UDS token: "+authorization);
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testRunData.getDeterminationBaseUrl()).path(REST_SERVICE_URI_COMPANIES);
		final URI uri = builder.build().encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri + " findCompanies");

		final ResponseEntity<UiCompanyList> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiCompanyList.class
		);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's create model scenario service.
	 *
	 * @param testRunData The test run data containing the authorization info to use for the request.
	 * @param companyId The company ID of the company to create the model scenario for.
	 * @param modelScenarioDetail The model scenario to create.
	 *
	 * @return The result of the rest service call.
	 */
	public UiModelScenarioDetail createModelScenario(final TestRun testRunData, final String companyId, final UiModelScenarioDetail modelScenarioDetail)
	{
		final String authorization = checkUDSTokenExpiry() ? getUDSToken(testRunData.getEnvCredentialsID(),testRunData.getEnvCredentialsPassword(),testRunData.getEnvironmentMS()) : UDS_TOKEN_MAP.get("UDS_Token");
		LOG.info(Logger.EVENT_UNSPECIFIED, "Fetch UDS token: "+authorization);
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testRunData.getDeterminationBaseUrl()).path(REST_SERVICE_URI_CREATE_MOD_SCEN);
		final Map<String, String> params = new HashMap<>();

		params.put(COMPANY_ID, companyId);

		final URI uri = builder.buildAndExpand(params).encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri + " createModelScenario");

		final ResponseEntity<UiModelScenarioDetail> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.POST,
				new HttpEntity<>(modelScenarioDetail, createHttpHeaders(authorization)),
				UiModelScenarioDetail.class
		);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's update model scenario service.
	 *
	 * @param testRunData The test run data containing the authorization info to use for the request.
	 * @param companyId The company ID of the company to update the model scenario for.
	 * @param modelScenarioDetail The model scenario data to update.
	 */
	public void updateModelScenario(final TestRun testRunData, final String companyId, final UiModelScenarioDetail modelScenarioDetail)
	{
		final String authorization = checkUDSTokenExpiry() ? getUDSToken(testRunData.getEnvCredentialsID(),testRunData.getEnvCredentialsPassword(),testRunData.getEnvironmentMS()) : UDS_TOKEN_MAP.get("UDS_Token");
		LOG.info(Logger.EVENT_UNSPECIFIED, "Fetch UDS token: "+authorization);
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testRunData.getDeterminationBaseUrl()).path(REST_SERVICE_URI_UPDATE_MOD_SCEN);
		final Map<String, String> params = new HashMap<>();

		params.put(COMPANY_ID, companyId);
		params.put(SCENARIO_ID, modelScenarioDetail.getScenarioId().toString());

		final URI uri = builder.buildAndExpand(params).encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri + " updateModelScenario");

		final ResponseEntity<UiModelScenarioDetail> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.PUT,
				new HttpEntity<>(modelScenarioDetail, createHttpHeaders(authorization)),
				UiModelScenarioDetail.class
		);

		// Only here to avoid a larger complain block from Intellij.
		final UiModelScenarioDetail unused = responseEntity.getBody();

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);
	}


	/**
	 * Makes rest service call to Determination's delete model scenario service.
	 *
	 * @param testRunData The test run data containing the authorization info to use for the request.
	 * @param modelScenarioIds The IDs of the model scenarios to delete.
	 */
	public void deleteModelScenario(final TestRun testRunData, final List<String> modelScenarioIds)
	{
		final String authorization = checkUDSTokenExpiry() ? getUDSToken(testRunData.getEnvCredentialsID(),testRunData.getEnvCredentialsPassword(),testRunData.getEnvironmentMS()) : UDS_TOKEN_MAP.get("UDS_Token");
		LOG.info(Logger.EVENT_UNSPECIFIED, "Fetch UDS token: "+authorization);
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testRunData.getDeterminationBaseUrl()).path(REST_SERVICE_URI_DELETE_MOD_SCEN);
		final URI uri = builder.build().encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri + " deleteModelScenario");

		final ResponseEntity<String> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.POST,
				new HttpEntity<>(modelScenarioIds, createHttpHeaders(authorization)),
				String.class
		);

		// Only here to avoid a larger complain block from Intellij.
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
		final String authorization = checkUDSTokenExpiry() ? getUDSToken(testRunData.getEnvCredentialsID(),testRunData.getEnvCredentialsPassword(),testRunData.getEnvironmentMS()) : UDS_TOKEN_MAP.get("UDS_Token");
		LOG.info(Logger.EVENT_UNSPECIFIED, "Fetch UDS token: "+authorization);
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testRunData.getDeterminationBaseUrl()).path(REST_SERVICE_URI_RUN_MOD_SCEN);
		final Map<String, String> params = new HashMap<>();

		params.put(COMPANY_ID, companyId);
		params.put(SCENARIO_ID, modelScenarioId);

		final URI uri = builder.buildAndExpand(params).encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri + " runModelScenario");

		final ResponseEntity<UiScenarioResult> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				UiScenarioResult.class
		);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's find model scenarios service.
	 *
	 * @param testRunData The test run data containing the authorization info to use for the request.
	 *
	 * @return The result of the rest service call.
	 */
	public List<UiModelScenario> findModelScenarios(final TestRun testRunData)
	{
		final String authorization = checkUDSTokenExpiry() ? getUDSToken(testRunData.getEnvCredentialsID(),testRunData.getEnvCredentialsPassword(),testRunData.getEnvironmentMS()) : UDS_TOKEN_MAP.get("UDS_Token");
		LOG.info(Logger.EVENT_UNSPECIFIED, "Fetch UDS token: "+authorization);
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testRunData.getDeterminationBaseUrl()).path(REST_SERVICE_URI_FIND_MOD_SCEN);
		final URI uri = builder.build().encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri + " findModelScenarios");

		final ResponseEntity<List<UiModelScenario>> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				new ParameterizedTypeReference<List<UiModelScenario>>()
				{
				}
		);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}


	/**
	 * Makes rest service call to Determination's find countries service.
	 *
	 * @param testRunData The test run data containing the authorization info to use for the request.
	 *
	 * @return The result of the rest service call.
	 */
	public List<ClientZone> getCountries(final TestRun testRunData)
	{
		final String authorization = checkUDSTokenExpiry() ? getUDSToken(testRunData.getEnvCredentialsID(),testRunData.getEnvCredentialsPassword(),testRunData.getEnvironmentMS()) : UDS_TOKEN_MAP.get("UDS_Token");
		LOG.info(Logger.EVENT_UNSPECIFIED, "Fetch UDS token: "+authorization);
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(testRunData.getDeterminationBaseUrl()).path(REST_SERVICE_URI_FIND_COUNTRIES);
		final URI uri = builder.build().encode().toUri();

		LOG.info(Logger.EVENT_UNSPECIFIED, MAKING_OUTBOUND_REST_SERVICE_CALL_URI + uri + " getCountries");

		final ResponseEntity<List<ClientZone>> responseEntity = restTemplate.exchange(
				uri,
				HttpMethod.GET,
				new HttpEntity<String>(createHttpHeaders(authorization)),
				new ParameterizedTypeReference<List<ClientZone>>()
				{
				}
		);

		LOG.info(Logger.EVENT_UNSPECIFIED, REST_SERVICE_CALL_COMPLETE);

		return responseEntity.getBody();
	}

	public boolean checkUDSTokenExpiry()  {
		if(UDS_TOKEN_MAP.size()==0){
			return true;
		}
		long currentTime = new Timestamp(new Date().getTime()).getTime();
		long lastFetchUDSTime= Long.parseLong(UDS_TOKEN_MAP.get("lastUpdated"));
		long difference = currentTime - lastFetchUDSTime;
		boolean result= difference > 7200000 ? true : false;
		LOG.info(Logger.EVENT_UNSPECIFIED,"UDS token is "+ (difference/1000)+" second old");
		return result;

	}

	public String getUDSToken(String userName, String password, String environment) {

		final UDSTokenUtility authUtils=new UDSTokenUtility(userName,password);
		final UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(UDS_TOKEN_URL);
		LOG.info(Logger.EVENT_UNSPECIFIED,"UDS token expired. Fetching new one");

		builder.queryParam("env", environment);
		UDS_TOKEN_MAP.clear();
		final URI uri = builder.build().encode().toUri();
		ResponseEntity<String> responseEntity;
		int counter=0;
		do{
			responseEntity= restTemplate.exchange(
					uri,
					HttpMethod.POST,
					new HttpEntity<UDSTokenUtility>(authUtils),
					String.class
			);
			counter++;
			LOG.info(Logger.EVENT_UNSPECIFIED,"Trying to fetch UDS token: "+counter);
		}while(responseEntity.getBody()==null && counter<5);
		Date date = new Date();
		Timestamp timestamp1 = new Timestamp(date.getTime());

		UDS_TOKEN_MAP.put("UDS_Token",responseEntity.getBody());
		UDS_TOKEN_MAP.put("lastUpdated",Long.toString(timestamp1.getTime()));


		LOG.info(Logger.EVENT_UNSPECIFIED, "Fetch UDS token: "+responseEntity.getBody());
		return UDS_TOKEN_MAP.get("UDS_Token");
	}
}
