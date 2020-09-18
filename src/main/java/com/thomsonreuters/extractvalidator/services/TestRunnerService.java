package com.thomsonreuters.extractvalidator.services;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thomsonreuters.extractvalidator.dao.SrcRuleQualifierDao;
import com.thomsonreuters.extractvalidator.dto.RunResults;
import com.thomsonreuters.extractvalidator.dto.TestCase;
import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.dto.determination.*;
import com.thomsonreuters.extractvalidator.dto.extract.TestAddress;
import com.thomsonreuters.extractvalidator.dto.extract.content.*;
import com.thomsonreuters.extractvalidator.util.ExternalRestClient;
import com.thomsonreuters.extractvalidator.util.LocationTreatmentBuilder;
import com.thomsonreuters.extractvalidator.util.ModelScenarioUtil;
import com.thomsonreuters.extractvalidator.util.SoapClient;
import com.thomsonreuters.extractvalidator.wsdl.*;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Service to manage the test run, all parts of the test will be managed from here.
 *
 * @author Matt Godsey
 */
@Service
public final class TestRunnerService
{
	/**
	 * Test case status for when rates don't match.
	 */
	private static final String FAILED = "FAILED";

	/**
	 * Test case status for when rates are fine.
	 */
	private static final String PASSED = "PASSED";

	/**
	 * Test case status for when rates don't match due to rule qualifier.
	 */
	private static final String RULEQUALIFIER = "RULEQUALIFIER";

	/**
	 * Test case status to denote failure from Determination
	 */
	private static final String DETISSUE = "DETISSUE";

	/**
	 * BigDecimal comparison result constant for greater than.
	 */
	private static final List<Integer> IS_GREATER_THAN_EQUAL_TO = new ArrayList<>();

	/**
	 * BigDecimal comparison result constant for less than.
	 */
	private static final List<Integer>  IS_LESS_THAN_EQUAL_TO =  new ArrayList<>();

	private static final int IS_EQUAL= 0;
	/**
	 * Double value to multiply the rate from the model scenario by to match up with the rates from the extract.
	 */
	private static final double RATE_MULTIPLIER = 100.0;

	/**
	 * hyphen delimilter
	 */
	private static final String HYPHEN_DELIMITER = "-";

	/**
	 *
	 */
	private static final String TAX = "TAX";

	/**
	 * Cascading rule found for admin authority
	 */
	private static final String CASCADING_RULE_WAS_FOUND = "Cascading Rule was found";

	/**
	 *
	 */
	private  static final String RULE_NO_TAX = "RULE_NO_TAX";

	/**
	 *
	 */
	private static final String RULE_NO_TAX_AUTHORITY = "Authority:";

	/**
	 *
	 */
	private  static final String ADMIN_AUTHORITY = "authority";


	/**
	 * The rest client to use when making REST calls to Content Extract endpoints or Determination Endpoints.
	 */
	private final ExternalRestClient externalRestClient;

	@Autowired
	private SrcRuleQualifierDao srcRuleQualifierDao;


	/**
	 * Logging handle for this class
	 */
	private static final Logger LOGGER = ESAPI.getLogger(TestRunnerService.class);

	@Autowired
	private SoapClient soapClient;


	/**
	 * Create the test runner service.
	 *
	 * @param externalRestClient The rest client to use when making REST calls to Content Extract endpoints or Determination Endpoints.
	 */
	@Autowired
	public TestRunnerService(final ExternalRestClient externalRestClient)
	{
		this.externalRestClient = externalRestClient;
	}


	/**
	 * Execute the validation using java classes for the extract and model scenario data.
	 *
	 * @param testRunData The test run data provided.
	 *
	 * @return A set of test case results based on the products and rates in the extract.
	 */
	public RunResults generateRunResults(final TestRun testRunData)
	{
		final LocalDateTime startTime = LocalDateTime.now();
		LOGGER.info(Logger.EVENT_UNSPECIFIED, "Starting test run for company: " + testRunData.getTestCompanyName());
		ContentExtract extract = null;
		UiCompany testCompany = null;
		IS_GREATER_THAN_EQUAL_TO.add(1);
		IS_GREATER_THAN_EQUAL_TO.add(0);

		IS_LESS_THAN_EQUAL_TO.add(0);
		IS_LESS_THAN_EQUAL_TO.add(-1);

		final List<TestCase> testCases = new LinkedList<>();
		final RunResults runResults = new RunResults(testCases, testRunData.getTestRunNumber());

		try
		{
			LOGGER.info(Logger.EVENT_UNSPECIFIED, "Retrieving extract for company: " + testRunData.getTestCompanyName());
			extract = (ContentExtract) externalRestClient.findContentExtract(testRunData, true);
		}
		catch (final Exception ex)
		{
			final String message = String.format("Rest call to find extract or companies failed, message: %s", ex.getMessage());

			LOGGER.error(Logger.EVENT_FAILURE, message);

			runResults.getTestCases().add(prepareErrorTestCase(message));
		}

		if (null != extract && null == extract.getAddresses()) //&& null == extract.getLocations()
		{
			final String message = String.format("Extract found is incomplete: %s", extract.getExtractName());

			LOGGER.error(Logger.EVENT_FAILURE, message);

			runResults.getTestCases().add(prepareErrorTestCase(message));
		}
		else if (null != extract && null == extract.getProducts()) {

			final String message = String.format("Extract without any products: %s", extract.getExtractName());

			LOGGER.error(Logger.EVENT_FAILURE, message);

			runResults.getTestCases().add(prepareErrorTestCase(message));
		}
		else if ( null != extract && null == extract.getJurisdictionAuthorities() && null == extract.getJurisdictionTreatmentMappings() ) {

			final String message = String.format("Extract without any jurisdictionAuthorities, jurisdictionTreatmentMappings : %s", extract.getExtractName());

			LOGGER.error(Logger.EVENT_FAILURE, message);

			runResults.getTestCases().add(prepareErrorTestCase(message));
		}
		else if (null != extract)
		{
			try
			{
				LOGGER.info(Logger.EVENT_UNSPECIFIED, "Running extract validation.");
				verifyContentExtractWithModelScenario(extract, testRunData, testCases);
			}
			catch (final Exception ex)
			{
				runResults.getTestCases().add(prepareErrorTestCase(ex.getMessage()));

				LOGGER.error(Logger.EVENT_FAILURE, "Something went wrong. Message: " + ex.getCause()+"\n"+ex.getStackTrace());
			}
		}

		LOGGER.info(Logger.EVENT_UNSPECIFIED, "All runs complete, returning test cases.");
		final LocalDateTime endTime = LocalDateTime.now();
		LOGGER.info(Logger.EVENT_UNSPECIFIED, "Test cases complete at: " + endTime);
		final Duration elapsedTime = Duration.between(startTime, endTime);
		LOGGER.info(Logger.EVENT_UNSPECIFIED,
				 String.format("Total time took test cases run was %s hours, %s minutes, %s seconds.",
							   elapsedTime.toHours(), elapsedTime.toMinutes() - elapsedTime.toHours() * 60,
							   elapsedTime.toMillis() / 1000 - elapsedTime.toMinutes() * 60));

		return runResults;

	}


	/**
	 * Prepare a test case with an error message when an exception occurs.
	 *
	 * @param message The error message to set.
	 *
	 * @return At test case with ERROR set as the status and an error message as the message.
	 */
	private TestCase prepareErrorTestCase(final String message)
	{
		final TestCase testCase = new TestCase();

		testCase.setTestResult("ERROR");
		testCase.setMessage(message);

		return testCase;
	}





	/**
	 * Find the correct company based on the name from the test run data and the list of companies returned from Determination.
	 *
	 * @param testRunData The test run data passed in.
	 * @param companies The list of companies from Determination.
	 *
	 * @return A specific company that matches the name provided in the test run data.
	 */
	private UiCompany findTestCompany(final TestRun testRunData, final UiCompanyList companies)
	{
		UiCompany testCompany = null;

		for (final UiCompany company : companies.getCompanies())
		{
			if (company.getCompanyName().equals(testRunData.getTestCompanyName()))
			{
				testCompany = company;
			}
		}

		return testCompany;
	}

//	public String getUDSToken(String userName, String password, String env){
//		return externalRestClient.getUDSToken(userName,password,env);
//
//	}

	private IndataType buildIndata(String externalCompanyID)
	{
		IndataType indata = new IndataType();
		indata.setVersion(VersionType.G);
		indata.setEXTERNALCOMPANYID(externalCompanyID);

		IndataInvoiceType indataInvoice = new IndataInvoiceType();
		indataInvoice.setCALCULATIONDIRECTION("F");
		indataInvoice.setCOMPANYROLE("S");
		indataInvoice.setCURRENCYCODE("USD");
//		indataInvoice.setINVOICEDATE("2018-08-08");
		indataInvoice.setISAUDITED("N");
//		ZoneAddressType zoneAddressType = new ZoneAddressType();
//		zoneAddressType.setCOUNTRY("UNITED STATES");
//		zoneAddressType.setSTATE("NEW YORK");
//		zoneAddressType.setCITY("NEW YORK");
//		zoneAddressType.setPOSTCODE("10001");
//		indataInvoice.setSHIPFROM(zoneAddressType);
//		indataInvoice.setSHIPTO(zoneAddressType);
		indataInvoice.setTRANSACTIONTYPE("GS");
//		IndataLineType indataLineType = new IndataLineType();
//		indataLineType.setID("1");
//		indataLineType.setLINENUMBER(BigDecimal.valueOf(1));
//		indataLineType.setGROSSAMOUNT("100");
//		indataLineType.setPRODUCTCODE("clothes");
//		indataInvoice.getLINE().add(indataLineType);

		indata.getINVOICE().add(indataInvoice);
		return indata;
	}

	private IndataInvoiceType getIndataInvoice(IndataType indata)
	{
		if (indata.getINVOICE().size()==1)
			return indata.getINVOICE().get(0);
		else
		{
			LOGGER.error(Logger.EVENT_FAILURE, "invoice number is not 1");
			return null;
		}
	}

	private ZoneAddressType buildZoneAddress(Address address)
	{
		ZoneAddressType zoneAddressType = new ZoneAddressType();
		zoneAddressType.setCOUNTRY(address.getCountry());
		zoneAddressType.setSTATE(address.getState());
		zoneAddressType.setCOUNTY(address.getCounty());
		zoneAddressType.setCITY(address.getCity());
		zoneAddressType.setPOSTCODE(address.getPostalCode());
		String postalRange= address.getGeocode();
		String splitRange[];
		String geocode=null;

		if(postalRange != null){
			splitRange=postalRange.split("-");
			geocode=splitRange[0].trim();
		}
		zoneAddressType.setGEOCODE(geocode);
		return zoneAddressType;
	}

	private void setZoneAddress(IndataType indata, Address address, String taxType)
	{
		ZoneAddressType zoneAddressType = buildZoneAddress(address);

		if(taxType.equalsIgnoreCase("US")){
			ZoneAddressType specialAddress_US = new ZoneAddressType();
			specialAddress_US.setCOUNTRY("UNITED STATES");
			specialAddress_US.setSTATE("CALIFORNIA");
			specialAddress_US.setPOSTCODE("95823");
			specialAddress_US.setGEOCODE("3000");
			getIndataInvoice(indata).setSHIPFROM(specialAddress_US);
		}
		else{
			getIndataInvoice(indata).setSHIPFROM(zoneAddressType);
		}

		getIndataInvoice(indata).setSHIPTO(zoneAddressType);
	}

	private void setTaxType (IndataType indata, String taxType)
	{
		AddressType addressType= new AddressType();
		addressType.setALL(taxType);
		addressType.setCITY(taxType);
		addressType.setCOUNTRY(taxType);
		addressType.setCOUNTY(taxType);
		addressType.setPROVINCE(taxType);
		addressType.setDISTRICT(taxType);
		addressType.setGEOCODE(taxType);
		addressType.setPOSTCODE(taxType);
		addressType.setSTATE(taxType);
		getIndataInvoice(indata).setTAXTYPE(addressType);
	}

	private void addInvoiceLines(IndataType indata, UiModelScenarioDetail uiModelScenarioDetail, String lineTaxCode)
	{
		getIndataInvoice(indata).getLINE().clear();
		for (UiModelScenarioLine line: uiModelScenarioDetail.getScenarioLines())
		{
			IndataLineType indataLineType = new IndataLineType();
			indataLineType.setID(String.valueOf(line.getLineNumber()));
			indataLineType.setLINENUMBER(BigDecimal.valueOf(line.getLineNumber()));
			indataLineType.setGROSSAMOUNT(line.getGrossAmount().toString());
			indataLineType.setPRODUCTCODE(line.getProductCode());
			indataLineType.setTAXCODE(lineTaxCode);
			getIndataInvoice(indata).getLINE().add(indataLineType);
		}
	}

	/**
	 * Verify the extract by building a model scenario for each address in the extract. Each line of the scenario will be built for each product in the extract.
	 *
	 * @param contentExtract The extract data.
	 * @param testRunData The test run data passed in.
	 *  @param testCases The list of test cases to populate.
	 */
	private void verifyContentExtractWithModelScenario(final ContentExtract contentExtract,
													   final TestRun testRunData,
													    final List<TestCase> testCases) throws Exception
	{
		final List<String> lineGrossAmounts = null == testRunData.getLineGrossAmounts() ? new LinkedList<>() : testRunData.getLineGrossAmounts();
		final String companyID= testRunData.getTestCompanyID();
		final String taxType=testRunData.getTaxType();
		final UiModelScenarioDetail uiModelScenarioDetail = ModelScenarioUtil.buildNewModelScenario(testRunData.getTestCompanyName(),
																									testRunData.getTestCompanyUUID(),
																									testRunData.getProductCategoryName(),
																									contentExtract,
																									lineGrossAmounts,
																									testRunData.getModelScenarioName(),
																									taxType);
		LOGGER.info(Logger.EVENT_UNSPECIFIED, "Invoice line number: "+uiModelScenarioDetail.getScenarioLines().size());
		final IndataType indata = buildIndata(testRunData.getExternalCompanyID());

		final String invoiceTaxCode=testRunData.getInvoiceTaxCode();
		final String lineTaxCode=testRunData.getLineTaxCode();

		int scenarioCounter = 1;
		List<String> jurisdictionList =testRunData.getJurisdictionKeys();
		List<String> postalcodeList=testRunData.getPostalCodeList();
		String jurisdictionKey;
		HashMap<String, String> jurisdictionMap =new HashMap<>();
		Set<LocalDate> effectiveDates;
		String filePath= System.getProperty("user.dir")+System.getProperty("file.separator")+testRunData.getTestExtractConfigName()+"_Result_"+System.currentTimeMillis()+".json";

		File outputFile= new File(filePath);
		LOGGER.info(Logger.EVENT_UNSPECIFIED,"file created at "+filePath);
		List<Address> addresses= contentExtract.getAddresses();

		Collections.sort(addresses);
		LOGGER.info(Logger.EVENT_UNSPECIFIED,"Skipped list : "+jurisdictionList);
		setTaxType(indata,taxType);

		getIndataInvoice(indata).setTAXCODE(invoiceTaxCode);

		LOGGER.info(Logger.EVENT_UNSPECIFIED, "Total address number: "+addresses.size());
		for (final Address address : addresses)
		{
			jurisdictionKey=address.getJurisdictionKey();
			if( ((jurisdictionMap.containsKey(jurisdictionKey)) && (jurisdictionMap.get(jurisdictionKey).contains("GEOCODE")))
					|| ((jurisdictionMap.containsKey(jurisdictionKey)) && (jurisdictionMap.get(jurisdictionKey).contains("POSTAL")) && (address.getGeocode()==null))
					|| ((jurisdictionMap.containsKey(jurisdictionKey)) && (jurisdictionMap.get(jurisdictionKey).contains("NoCode")) && (address.getGeocode()==null) && (address.getPostalCode() == null)) )
			{
				continue;
			}
			else {
				//LOGGER.info(Logger.EVENT_UNSPECIFIED, "Building location treatment data for jurisdiction:"+ address.getJurisdictionKey());

				final LocationTreatmentData locationTreatmentData = LocationTreatmentBuilder.buildLocationTreatmentData(address, contentExtract,taxType);

				if ((!locationTreatmentData.getProductAuthorityData().isEmpty() || !locationTreatmentData.getProductJurisdictionData().isEmpty()))
				{
					LOGGER.info(Logger.EVENT_UNSPECIFIED, "Building new scenario for Address: " + address.getPostalCode()+" and with jurisdiction : "+jurisdictionKey);
					//uiModelScenarioDetail.setLocationList(ModelScenarioUtil.buildLocations(address, countryList,testRunData.getTestExtractConfigName()));
					if(address.getPostalCode()!=null && address.getGeocode()!=null){
						if(jurisdictionMap.get(jurisdictionKey)!=null){
							String value= jurisdictionMap.get(jurisdictionKey) +"GEOCODE";
							jurisdictionMap.put(jurisdictionKey,value);
						}
						else{
							jurisdictionMap.put(jurisdictionKey,"POSTALGEOCODE");

						}
						LOGGER.info(Logger.EVENT_UNSPECIFIED, "For jurisdiction: "+jurisdictionKey+" and with geocode: "+address.getGeocode());
					}
					else if(address.getPostalCode()!=null && address.getGeocode()==null){
						jurisdictionMap.put(jurisdictionKey,"POSTAL");
						LOGGER.info(Logger.EVENT_UNSPECIFIED, "For jurisdiction (without geocode) : "+jurisdictionKey);
					}
					else if(address.getPostalCode()==null && address.getGeocode()==null){
						jurisdictionMap.put(jurisdictionKey,"NoCode");
						LOGGER.info(Logger.EVENT_UNSPECIFIED, "For jurisdiction (without postal and geocode) : "+jurisdictionKey);
					}

					if(jurisdictionList !=null && !jurisdictionList.contains(jurisdictionKey)){
						continue;

					}
					if(postalcodeList !=null && !postalcodeList.contains(address.getPostalCode())){
						continue;
					}

					setZoneAddress(indata, address,taxType);

					effectiveDates=getEffectiveDate(locationTreatmentData);
					for(LocalDate mDate:effectiveDates){

						final DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						final String scenarioDate1 = mDate.format(dateTimeFormatter1);
						LOGGER.info(Logger.EVENT_UNSPECIFIED,"The effective date for this jurisdiction: "+jurisdictionKey+" is: "+scenarioDate1);
						getIndataInvoice(indata).setINVOICEDATE(scenarioDate1);

						addInvoiceLines(indata, uiModelScenarioDetail,lineTaxCode);

						LOGGER.info(Logger.EVENT_UNSPECIFIED,
								String.format("Calling tax calculation api %s, invoice line count: %s", scenarioCounter,
										getIndataInvoice(indata).getLINE().size()));
						final TaxCalculationResponse taxCalculationResponse = soapClient.sendTaxCalcRequest(
								testRunData.getSoapUri(),
								testRunData.getSoapUser(),
								testRunData.getSoapPassword(),
								indata,
								testRunData.getSoapTimeoutRetryNumber());
						scenarioCounter++;
						if (!taxCalculationResponse.getOUTDATA().getREQUESTSTATUS().isISSUCCESS())
						{
							LOGGER.error(Logger.EVENT_FAILURE, "Get tax calculation response failed!");
							updateDETErrorResponse(scenarioCounter, outputFile, locationTreatmentData, taxCalculationResponse.getOUTDATA().getREQUESTSTATUS());
						}
						else
						{
							LOGGER.info(Logger.EVENT_UNSPECIFIED, "Comparing scenario rate to extract rate for location.");
							List<TestCase> returnedResult = compareScenarioAndExtract(taxCalculationResponse, uiModelScenarioDetail, testRunData.getProductCategoryName(), locationTreatmentData, mDate, scenarioCounter);
							testCases.addAll(returnedResult);
							appendResultToFile(returnedResult, outputFile);
						}
			}
		}
			}
		}

	}


	private void updateDETErrorResponse(final int scenarioCounter, final File outputFile, final LocationTreatmentData locationTreatmentData,
								 final OutdataRequestStatusType requestStatus)
	{
		TestCase errorResult = new TestCase();
		errorResult.setJurisdiction(locationTreatmentData.getJurisdictionNKey());
		errorResult.setScenarioExecuted(scenarioCounter);
		final TestAddress testAddress = new TestAddress();
		testAddress.setCity(locationTreatmentData.getAddress().getCity());
		testAddress.setCountry(locationTreatmentData.getAddress().getCountry());
		testAddress.setDistrict(locationTreatmentData.getAddress().getDistrict());
		testAddress.setGeocode(locationTreatmentData.getAddress().getGeocode());
		testAddress.setPostalCode(locationTreatmentData.getAddress().getPostalCode());
		testAddress.setCounty(locationTreatmentData.getAddress().getCounty());
		testAddress.setProvince(locationTreatmentData.getAddress().getProvince());
		testAddress.setState(locationTreatmentData.getAddress().getState());
		errorResult.setAddress(testAddress);
		if (requestStatus.getERROR() != null && requestStatus.getERROR().size() > 0)
			errorResult.setMessage(requestStatus.getERROR().get(0).getCODE()+" - "+requestStatus.getERROR().get(0).getDESCRIPTION());
		errorResult.setTestResult(DETISSUE);
		List<TestCase> errorList= new ArrayList<>();
		errorList.add(errorResult);
		appendResultToFile(errorList, outputFile);
	}


	private void appendResultToFile(List<TestCase> testCases, File outputFile) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		OutputStream os = null;
		for(TestCase testCase:testCases) {
			try {
				String writeResultData = gson.toJson(testCase);
				os = new FileOutputStream(outputFile, true);
				os.write(writeResultData.getBytes(), 0, writeResultData.length());
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					try {
						os.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

	}

	public Set<LocalDate> getEffectiveDate(LocationTreatmentData locationTreatmentData){
		Set<LocalDate> mEffectiveDate = new TreeSet<>();

		if (locationTreatmentData.getProductJurisdictionData().isEmpty()) {
			for (final ProductAuthorityData productAuthorityData : locationTreatmentData.getProductAuthorityData()) {
				for (final AuthorityData authorityData : productAuthorityData.getAuthorityData()) {
					for (final TreatmentData authorityTreatmentData : authorityData.getAuthorityTreatmentData()) {
						mEffectiveDate.add(authorityTreatmentData.getFromDate().toLocalDate());
					}
				}
			}
		}
		else{
			for(ProductJurisdictionData productJurisdictionData:locationTreatmentData.getProductJurisdictionData()){
				for(JurisdictionData jurisdictionData: productJurisdictionData.getJurisdictionData()){
					for(TreatmentData treatmentData: jurisdictionData.getJurisdictionTreatmentData()){
						mEffectiveDate.add(treatmentData.getFromDate().toLocalDate());
					}
				}
			}
		}
		return mEffectiveDate;
	}
	/**
	 * Compare the model scenario results to the products and rates in the extract.
	 *
	 * @param taxCalculationResponse The results from the model scenario calculation.
	 * @param modelScenarioDetail The details of the model scenario.
	 * @param locationTreatmentData The rates by authority and product from the extract.
	 * @param effectiveDate The effective date used for the calculation.
	 *
	 * @return Return a set of test cases, one for each product.
	 */
	private List<TestCase> compareScenarioAndExtract(final TaxCalculationResponse taxCalculationResponse,
													 final UiModelScenarioDetail modelScenarioDetail,
													 final String productCategoryName,
													 final LocationTreatmentData locationTreatmentData,
													 final LocalDate effectiveDate,
													 final int scenarioCounter)
	{
		final Set<String> lineGrossAmounts1 = new HashSet<>();

		for (final OutdataLineType line: taxCalculationResponse.getOUTDATA().getINVOICE().get(0).getLINE())
		{
			lineGrossAmounts1.add(line.getGROSSAMOUNT());
		}

		final Map<String, BigDecimal> scenarioProductRateMap1 = buildScenarioProductRateMap1(taxCalculationResponse, modelScenarioDetail.getScenarioLines());
		final Map<String, BigDecimal> extractProductRateMap = buildExtractProductRateMap(locationTreatmentData, productCategoryName,effectiveDate, lineGrossAmounts1);

		final List<TestCase> testCases = new LinkedList<>();

		for (final Map.Entry<String, BigDecimal> scenarioEntry : scenarioProductRateMap1.entrySet())
		{
			for (final Map.Entry<String, BigDecimal> extractEntry : extractProductRateMap.entrySet())
			{
				if (extractEntry.getKey().equals(scenarioEntry.getKey()))
				{
					final TestCase testCase = new TestCase();

					buildTestCase(extractEntry, testCase, scenarioEntry, effectiveDate, locationTreatmentData,scenarioCounter);
					if (testCase.getTestResult().equals(FAILED))
					{
						isFailedForRuleQualiier(taxCalculationResponse, modelScenarioDetail.getScenarioLines(), scenarioEntry, testCase);
					}
					testCases.add(testCase);

					break;
				}
			}
		}
		return testCases;
	}


	/**
	 * @param taxCalculationResponse
	 * @param lines
	 * @param scenarioEntry
	 * @param testCase
	 */
	public void isFailedForRuleQualiier(final TaxCalculationResponse taxCalculationResponse, final List<UiModelScenarioLine> lines, final Map.Entry<String, BigDecimal> scenarioEntry, TestCase testCase)
	{
		final int splitIndex = scenarioEntry.getKey().indexOf(':');
		final String productCode = scenarioEntry.getKey().substring(0, splitIndex);
		final String grossAmount = scenarioEntry.getKey().substring(splitIndex + 1);
		boolean ruleExistsInRuleQualiier = false;
		for (final UiModelScenarioLine scenarioLine : lines)
		{
			if (!scenarioLine.getProductCode().equals(productCode))
			{
				continue;
			}
			for (final OutdataLineType lineTaxDetail : taxCalculationResponse.getOUTDATA().getINVOICE().get(0).getLINE())
			{
				if (lineTaxDetail.getLINENUMBER().compareTo(BigDecimal.valueOf(scenarioLine.getLineNumber())) == 0
						&& lineTaxDetail.getGROSSAMOUNT().equals(grossAmount))
				{
					List<OutdataTaxType> taxTypeList = lineTaxDetail.getTAX();
					/* Authority having No tax rule not coming in the line item, just appear in the message only.
					   fetch authorty name and rule order from the message
					 */
					List <MessageType> ruleNoTaxMsgText = lineTaxDetail.getMESSAGE().stream().filter(
							m -> m.getCODE().equals(RULE_NO_TAX)).collect(Collectors.toList());
					for (MessageType msgType : ruleNoTaxMsgText)
					{
						final String ruleNoTaxMsg = msgType.getMESSAGETEXT();
						BigDecimal ruleOrder = new BigDecimal(ruleNoTaxMsg.substring(ruleNoTaxMsg.lastIndexOf(":")+2));
						String authority = ruleNoTaxMsg.substring(ruleNoTaxMsg.lastIndexOf(RULE_NO_TAX_AUTHORITY) + 11, ruleNoTaxMsg.lastIndexOf(TAX) + 3);
						if (isRuleExistsInRuleQualiier(lineTaxDetail, ruleOrder, authority))
						{
							ruleExistsInRuleQualiier = true;
							break;
						}

					}

					if (!ruleExistsInRuleQualiier)
					{
						for (final OutdataTaxType lineItem : taxTypeList)
						{
							BigDecimal ruleOrder = lineItem.getRULEORDER();
							String authority = lineItem.getAUTHORITYNAME();
							if (isRuleExistsInRuleQualiier(lineTaxDetail, ruleOrder, authority))
							{
								ruleExistsInRuleQualiier = true;
								break;
							}
						}
					}

				}
			}
		}
		if (ruleExistsInRuleQualiier)
		{
			testCase.setTestResult(RULEQUALIFIER);
		}
	}


	/**
	 * Build the map of products and rates for those products from the scenario results.  This is expecting there to be a line per product in the scenario.
	 *
	 * @param scenarioResult The result of the scenario calculation.
	 * @param lines The line data from the model scenario, which aren't contained in the results.
	 *
	 * @return A map of product to rate key pairs.
	 */
	private Map<String, BigDecimal> buildScenarioProductRateMap(final UiScenarioResult scenarioResult, final List<UiModelScenarioLine> lines)
	{
		final Map<String, BigDecimal> productRateMap = new HashMap<>();
		//final Map<String, String> productRuleOrder= new HashMap<>();

		for (final UiModelScenarioLine scenarioLine : lines)
		{
			for (final UiLineTaxDetail lineTaxDetail : scenarioResult.getInvoiceTaxDetail().getLineTaxDetails())
			{
				if (lineTaxDetail.getLineNumber().compareTo(scenarioLine.getLineNumber()) == 0)
				{
					BigDecimal accumulatedTaxAmount = BigDecimal.valueOf(0);
					String productRuleOrders="";


					for (final UiAuthorityTaxDetail authorityTaxDetail : lineTaxDetail.getAuthorityTaxDetails())
					{

						accumulatedTaxAmount = accumulatedTaxAmount.add(BigDecimal.valueOf(Double.parseDouble(authorityTaxDetail.getTaxAmount())));
						//productRuleOrders= productRuleOrders+ authorityTaxDetail.getRuleOrder();
					}

					productRateMap.put(scenarioLine.getProductCode() + ":" + lineTaxDetail.getGrossAmount(), accumulatedTaxAmount);
					//productRuleOrder.put(scenarioLine.getProductCode() + ":" + lineTaxDetail.getGrossAmount(),productRuleOrders);

					break;
				}
			}
		}
		//LOGGER.info(Logger.EVENT_UNSPECIFIED, "rule order :  "+productRuleOrder);
		return productRateMap;
	}

	private Map<String, BigDecimal> buildScenarioProductRateMap1(final TaxCalculationResponse taxCalculationResponse, final List<UiModelScenarioLine> lines)
	{
		final Map<String, BigDecimal> productRateMap = new HashMap<>();
		//final Map<String, String> productRuleOrder= new HashMap<>();

		for (final UiModelScenarioLine scenarioLine : lines)
		{
			for (final OutdataLineType lineTaxDetail: taxCalculationResponse.getOUTDATA().getINVOICE().get(0).getLINE())
			{
				if (lineTaxDetail.getLINENUMBER().compareTo(BigDecimal.valueOf(scenarioLine.getLineNumber())) == 0)
				{
					BigDecimal accumulatedTaxAmount = new BigDecimal(lineTaxDetail.getTOTALTAXAMOUNT());
					String productRuleOrders="";


//					for (final UiAuthorityTaxDetail authorityTaxDetail : lineTaxDetail.getAuthorityTaxDetails())
//					{
//
//						accumulatedTaxAmount = accumulatedTaxAmount.add(BigDecimal.valueOf(Double.parseDouble(authorityTaxDetail.getTaxAmount())));
//						//productRuleOrders= productRuleOrders+ authorityTaxDetail.getRuleOrder();
//					}

					productRateMap.put(scenarioLine.getProductCode() + ":" + lineTaxDetail.getGROSSAMOUNT(), accumulatedTaxAmount);
					//productRuleOrder.put(scenarioLine.getProductCode() + ":" + lineTaxDetail.getGrossAmount(),productRuleOrders);

					break;
				}
			}
		}
		//LOGGER.info(Logger.EVENT_UNSPECIFIED, "rule order :  "+productRuleOrder);
		return productRateMap;
	}


	/**
	 * Check whether the rule order exists in rule qualifier
	 *
	 * @param lineTaxDetail
	 * @param ruleOrder
	 * @param authority
	 *
	 * @return
	 */
	private boolean isRuleExistsInRuleQualiier(final OutdataLineType lineTaxDetail,
											   final BigDecimal ruleOrder,
											   String authority)
	{
		if (srcRuleQualifierDao.ruleOrderExistsInSrcRule(ruleOrder, authority) == 0)
		{
			// Get admin authority name from the response if any and use it to check rule qualifier
			// Cascading rule used for admin authority, get authority name from the message
			final Optional<MessageType> adminAuthMsgText = lineTaxDetail.getMESSAGE().stream().filter(
					m -> m.getMESSAGETEXT().contains(CASCADING_RULE_WAS_FOUND)).findFirst();
			if (adminAuthMsgText.isPresent())
			{
				final String msg = adminAuthMsgText.get().getMESSAGETEXT();
				authority = msg.substring(msg.lastIndexOf(ADMIN_AUTHORITY) + 10, msg.lastIndexOf(TAX) + 3);
			}
		}
		if (srcRuleQualifierDao.ruleOrderExistsInRuleQualifier(ruleOrder,authority) > 0)
		{
			return true;
		}
		return false;
	}


	/**
	 * Build the map of products and rates for those products in the extract.
	 *
	 * @param locationTreatmentData The location treatment data containing the products, authorities, and rates from the extract for a given jurisdiction.
	 * @param effective_Date The effective date of the calculation.
	 * @param lineGrossAmounts The list of gross amount from the scenario line used to determine the correct tier rates.
	 *
	 * @return A map of product to rate key pairs.
	 */
	private Map<String, BigDecimal> buildExtractProductRateMap(final LocationTreatmentData locationTreatmentData,
															   final String productCategoryName,
															   final LocalDate effective_Date,
															   final Set<String> lineGrossAmounts)
	{
		final Map<String, BigDecimal> scenarioProductRateMap = new HashMap<>();

		if (locationTreatmentData.getProductJurisdictionData().isEmpty())
		{
			for (final ProductAuthorityData productAuthorityData : locationTreatmentData.getProductAuthorityData())
			{
				if(productCategoryName!=null && !productAuthorityData.getProduct().getProductCategory().contains(productCategoryName)){
					continue;
				}
				for (final String lineGrossAmount : lineGrossAmounts)
				{
					BigDecimal accumulatedTaxAmount = new BigDecimal(0);

					for (final AuthorityData authorityData : productAuthorityData.getAuthorityData())
					{
						for (final TreatmentData authorityTreatmentData : authorityData.getAuthorityTreatmentData())
						{

							final LocalDate fromDate =  (authorityTreatmentData.getFromDate()!=null) ? authorityTreatmentData.getFromDate().toLocalDate() : null;
							final LocalDate toDate = (authorityTreatmentData.getToDate()!=null) ? authorityTreatmentData.getToDate().toLocalDate() : null;


							if (((effective_Date.isAfter(fromDate)) || (effective_Date.isEqual(fromDate))) && (null == toDate || effective_Date.isEqual(toDate) || (effective_Date.isBefore(toDate))))
							{
								accumulatedTaxAmount = accumulatedTaxAmount.add(calculateAccumulatedTaxAmount(authorityTreatmentData.getTreatments(), lineGrossAmount));
							}

						}
					}

					scenarioProductRateMap.put(productAuthorityData.getProduct().getName() + ":" + lineGrossAmount, accumulatedTaxAmount);
				}
			}
		}
		else
		{
			for (final ProductJurisdictionData productJurisdictionData : locationTreatmentData.getProductJurisdictionData())
			{
				if(productCategoryName!=null && !productJurisdictionData.getProduct().getProductCategory().contains(productCategoryName)){
					continue;
				}
				for (final String lineGrossAmount : lineGrossAmounts)
				{
					BigDecimal accumulatedTaxAmount = new BigDecimal(0);

					for (final JurisdictionData jurisdictionData : productJurisdictionData.getJurisdictionData())
					{
						for (final TreatmentData treatmentData : jurisdictionData.getJurisdictionTreatmentData())
						{
							final LocalDate fromDate = (treatmentData.getFromDate()!=null) ? treatmentData.getFromDate().toLocalDate() : null;
							final LocalDate toDate = (treatmentData.getToDate()!=null) ? treatmentData.getToDate().toLocalDate() : null;

							if (((effective_Date.isAfter(fromDate)) || (effective_Date.isEqual(fromDate))) && (null == toDate || effective_Date.isEqual(toDate) || (effective_Date.isBefore(toDate))))
							{
								accumulatedTaxAmount = accumulatedTaxAmount.add(calculateAccumulatedTaxAmount(treatmentData.getTreatments(), lineGrossAmount));

							}

						}
					}

					scenarioProductRateMap.put(productJurisdictionData.getProduct().getName() + ":" + lineGrossAmount, accumulatedTaxAmount);
				}
			}
		}

		return scenarioProductRateMap;
	}


	/**
	 * For the list of treatments, add the rates up for each treatment based on the gross amount for tier calculation.
	 *
	 * @param treatments The list of treatments to add up.
	 * @param scenarioGrossAmount The gross amount to take into account for tiers.
	 *
	 * @return Returns a single accumulated rate value.
	 */
	private BigDecimal calculateAccumulatedTaxAmount(final List<Treatment> treatments, final String scenarioGrossAmount)
	{
		BigDecimal accumulatedTaxAmount = new BigDecimal(0);
		BigDecimal grossAmount = BigDecimal.valueOf(Double.parseDouble(scenarioGrossAmount));

		for (final Treatment treatment : treatments)
		{
			if (treatment.getBasisPercent() !=null){
				grossAmount= grossAmount.multiply(treatment.getBasisPercent());
			}
			if (null != treatment.getRate())
			{

				accumulatedTaxAmount = accumulatedTaxAmount.add(calculateTaxAmountFromExtract(treatment.getRate(),grossAmount));
			}
			else
			{
				BigDecimal tierAmount= BigDecimal.valueOf(0);
				for (final Tier tier : treatment.getTierList())
				{
					if(treatment.getSplitType().equalsIgnoreCase("G")) {
						if (tier.getHighValue() == null) {
							if (IS_GREATER_THAN_EQUAL_TO.contains(grossAmount.compareTo(tier.getLowValue()))) {
								accumulatedTaxAmount = accumulatedTaxAmount.add(calculateTaxAmountFromExtract(tier.getRate(), grossAmount));
								break;
							}
						} else if (IS_GREATER_THAN_EQUAL_TO.contains(grossAmount.compareTo(tier.getLowValue())) && IS_LESS_THAN_EQUAL_TO.contains(grossAmount.compareTo(tier.getHighValue()))) {
							accumulatedTaxAmount = accumulatedTaxAmount.add(calculateTaxAmountFromExtract(tier.getRate(), grossAmount));
							break;
						}
					}
					else {
						if(tier.getHighValue() !=null) {
							if(IS_GREATER_THAN_EQUAL_TO.contains(grossAmount.compareTo(tier.getHighValue())) ) {
								tierAmount = tier.getHighValue().subtract(tier.getLowValue());
								accumulatedTaxAmount = accumulatedTaxAmount.add(calculateTaxAmountFromExtract(tier.getRate(),tierAmount));
							}
							else if(IS_GREATER_THAN_EQUAL_TO.contains(grossAmount.compareTo(tier.getLowValue())) && IS_LESS_THAN_EQUAL_TO.contains(grossAmount.compareTo(tier.getHighValue()))){
								tierAmount = grossAmount.subtract(tier.getLowValue());
								accumulatedTaxAmount = accumulatedTaxAmount.add(calculateTaxAmountFromExtract(tier.getRate(),tierAmount));
							}
						}
						else if(tier.getHighValue() ==null && IS_GREATER_THAN_EQUAL_TO.contains(grossAmount.compareTo(tier.getLowValue()))) {
							tierAmount = grossAmount.subtract(tier.getLowValue());
							accumulatedTaxAmount = accumulatedTaxAmount.add(calculateTaxAmountFromExtract(tier.getRate(),tierAmount));
						}
					}

				}
			}
		}

		return accumulatedTaxAmount;
	}

	private BigDecimal calculateTaxAmountFromExtract(BigDecimal rate, BigDecimal scenarioGrossAmount) {
		BigDecimal taxAmount = scenarioGrossAmount.multiply(rate);
//		if(taxAmount.scale()==1)
//			return taxAmount;
//		else
//			return taxAmount.setScale(0, RoundingMode.UP);
		return taxAmount;
	}


	/**
	 * Compose the test case result for a specific product.
	 *
	 * @param extractEntry The data product and rate from the extract.
	 * @param testCase The test case to populate.
	 * @param scenarioEntry The product and rate data from the scenario.
	 * @param effectiveDate The effective date used for the calculation and the treatment assignment.
	 * @param locationTreatmentData The location treatment data accumulated from the extract and holds the address and products to apply to the test case.
	 */
	private void buildTestCase(final Map.Entry<String, BigDecimal> extractEntry,
							   final TestCase testCase,
							   final Map.Entry<String, BigDecimal> scenarioEntry,
							   final LocalDate effectiveDate,
							   final LocationTreatmentData locationTreatmentData,
							   final int scenarioCounter)
	{
		final BigDecimal modelScenarioTaxAmount = scenarioEntry.getValue().setScale(2, RoundingMode.HALF_UP);
		final BigDecimal accumulatedTaxAmount = extractEntry.getValue().setScale(2, RoundingMode.HALF_UP);
		final int splitIndex = extractEntry.getKey().indexOf(':');
		final String productCode = extractEntry.getKey().substring(0, splitIndex);
		final String grossAmount = extractEntry.getKey().substring(splitIndex + 1);

		// Handle grouping by authority (use ProductAuthorityData) or by tax type (use ProductJurisdictionData).
		if (locationTreatmentData.getProductJurisdictionData().isEmpty())
		{
			for (final ProductAuthorityData productAuthorityData : locationTreatmentData.getProductAuthorityData())
			{
				if (productAuthorityData.getProduct().getName().equals(productCode))
				{
					testCase.setProductCategoryName(productAuthorityData.getProduct().getProductCategory());
					break;
				}
			}
		}
		else if (locationTreatmentData.getProductAuthorityData().isEmpty())
		{
			for (final ProductJurisdictionData productJurisdictionData : locationTreatmentData.getProductJurisdictionData())
			{
				if (productJurisdictionData.getProduct().getName().equals(productCode))
				{
					testCase.setProductCategoryName(productJurisdictionData.getProduct().getProductCategory());
					break;
				}
			}
		}

		testCase.setProductCode(productCode);
		testCase.setEffectiveDate(effectiveDate.toString());
		testCase.setModelScenarioTaxAmount(scenarioEntry.getValue().toString());
		testCase.setExtractTaxAmount(extractEntry.getValue().toString());
		testCase.setGrossAmount(grossAmount);
		testCase.setJurisdiction(locationTreatmentData.getJurisdictionNKey());
		testCase.setScenarioExecuted(scenarioCounter);

		final TestAddress testAddress = new TestAddress();

		testAddress.setCity(locationTreatmentData.getAddress().getCity());
		testAddress.setCountry(locationTreatmentData.getAddress().getCountry());
		testAddress.setDistrict(locationTreatmentData.getAddress().getDistrict());
		testAddress.setGeocode(locationTreatmentData.getAddress().getGeocode());
		testAddress.setPostalCode(locationTreatmentData.getAddress().getPostalCode());
		testAddress.setCounty(locationTreatmentData.getAddress().getCounty());
		testAddress.setProvince(locationTreatmentData.getAddress().getProvince());
		testAddress.setState(locationTreatmentData.getAddress().getState());

		testCase.setAddress(testAddress);
		if (null == accumulatedTaxAmount)
		{
			testCase.setTestResult(FAILED);
			testCase.setMessage("Accumulated rate has been set to null somehow, investigate.");
		}
		else if (null == modelScenarioTaxAmount)
		{
			testCase.setTestResult(FAILED);
			testCase.setMessage("Model Scenario failed, scenario messages: ");
		}
		else {
			double difference = modelScenarioTaxAmount.subtract(accumulatedTaxAmount).doubleValue();
			if (difference <= 0.01 && difference >= -0.01) {
				testCase.setTestResult(PASSED);
				testCase.setMessage("Tax amount is: " + modelScenarioTaxAmount + " for Gross Amount: " + grossAmount);
			} else {
				testCase.setTestResult(FAILED);
				testCase.setMessage("The tax amouunt do not match Scenario tax amount: " + modelScenarioTaxAmount + " Accumulated Tax amount: " + accumulatedTaxAmount);
			}
		}
	}
}
