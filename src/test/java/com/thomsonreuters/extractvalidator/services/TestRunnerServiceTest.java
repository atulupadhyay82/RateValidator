package com.thomsonreuters.extractvalidator.services;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.thomsonreuters.extractvalidator.dto.RunResults;
import com.thomsonreuters.extractvalidator.dto.TestCase;
import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.dto.content.ClientZone;
import com.thomsonreuters.extractvalidator.dto.determination.UiAuthorityTaxDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompany;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompanyList;
import com.thomsonreuters.extractvalidator.dto.determination.UiInvoiceTaxDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiLineTaxDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiScenarioResult;
import com.thomsonreuters.extractvalidator.dto.extract.content.Address;
import com.thomsonreuters.extractvalidator.dto.extract.content.Authority;
import com.thomsonreuters.extractvalidator.dto.extract.content.AuthorityTreatmentMapping;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;
import com.thomsonreuters.extractvalidator.dto.extract.content.EffectiveDate;
import com.thomsonreuters.extractvalidator.dto.extract.content.Jurisdiction;
import com.thomsonreuters.extractvalidator.dto.extract.content.JurisdictionAuthority;
import com.thomsonreuters.extractvalidator.dto.extract.content.Location;
import com.thomsonreuters.extractvalidator.dto.extract.content.Product;
import com.thomsonreuters.extractvalidator.dto.extract.content.Tier;
import com.thomsonreuters.extractvalidator.dto.extract.content.Treatment;
import com.thomsonreuters.extractvalidator.dto.extract.content.TreatmentGroup;
import com.thomsonreuters.extractvalidator.dto.extract.content.TreatmentGroupTreatment;
import com.thomsonreuters.extractvalidator.util.ExternalRestClient;
import com.thomsonreuters.extractvalidator.util.GroupingRule;
import com.thomsonreuters.extractvalidator.util.LoadMethod;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * Unit tests for the test runner service class. Also tests the ModelScenarioUtil, and LocationTreatmentBuilder.
 *
 * @author Matt Godsey
 */
public class TestRunnerServiceTest
{
	/**
	 * Class under test.
	 */
	private TestRunnerService testRunnerService;

	/**
	 * Mock client that manages all rest calls for the test runner.
	 */
	@Mock
	private ExternalRestClient externalRestClient;


	/**
	 * Setup the test runner service.
	 */
	@Before
	public void setUp()
	{
		initMocks(this);

		testRunnerService = new TestRunnerService(externalRestClient);
	}


	/**
	 * Verifies the functionality of {@link TestRunnerService#staticRunResults(TestRun)}.
	 */
	@Test
	public void staticRunResults()
	{
		final TestRun testRunData = prepareTestRunData();
		final UiModelScenarioDetail modelScenario = new UiModelScenarioDetail();

		modelScenario.setScenarioId(10L);

		final UiScenarioResult result = prepareScenarioResult("100", "10");

		Mockito.doReturn(prepareCountryList()).when(externalRestClient).getCountries(testRunData);
		Mockito.doReturn(prepareUiCompanyList("111", "Test01", 1L)).when(externalRestClient).findCompanies(testRunData);
		Mockito.doReturn(prepareContentExtract()).when(externalRestClient).findContentExtract(testRunData, true);
		Mockito.doReturn(modelScenario).when(externalRestClient).createModelScenario(eq(testRunData), eq("1"), any(UiModelScenarioDetail.class));
		Mockito.doReturn(result).when(externalRestClient).runModelScenario(testRunData, "10", "1");

		// Based on one address, one gross amount and one product, expect 1 model scenario run with one line. So we should have one test case output.
		final RunResults runResults = testRunnerService.staticRunResults(testRunData);

		Assert.assertThat(runResults.getTestCases().size(), is(1));

		final TestCase testCase = runResults.getTestCases().get(0);

		Assert.assertThat(testCase.getAccumulatedRate(), is("10.00"));
		Assert.assertThat(testCase.getGrossAmount(), is("100"));
		Assert.assertThat(testCase.getEffectiveDate(), is("2018-09-15T00:00"));
		Assert.assertThat(testCase.getModelScenarioRate(), is("10.0"));
		Assert.assertThat(testCase.getProductCategoryName(), is("General Goods"));
		Assert.assertThat(testCase.getProductCode(), is("GG"));
		Assert.assertThat(testCase.getTestResult(), is("PASSED"));
	}


	/**
	 * Prepare a content extract to use in tests.
	 *
	 * @return A populated content extract.
	 */
	private ContentExtract prepareContentExtract()
	{
		final ContentExtract contentExtract = new ContentExtract();
		final LocalDateTime effectiveDate = LocalDateTime.of(2018, 9, 15, 00, 00);

		contentExtract.setAddresses(new LinkedList<>());
		contentExtract.setLocations(new LinkedList<>());
		contentExtract.setAuthorities(new LinkedList<>());
		contentExtract.setJurisdictions(new LinkedList<>());
		contentExtract.setJurisdictionAuthorities(new LinkedList<>());
		contentExtract.setAuthorityTreatmentMappings(new LinkedList<>());
		contentExtract.setTreatments(new LinkedList<>());
		contentExtract.setTreatmentGroups(new LinkedList<>());
		contentExtract.setTreatmentGroupTreatments(new LinkedList<>());
		contentExtract.setProducts(new LinkedList<>());

		contentExtract.setCompanyName("Test01");
		contentExtract.setContentVersion(99L);
		contentExtract.setExtractDate(effectiveDate);
		contentExtract.setExtractName("Config01");
		contentExtract.setGroupingRule(GroupingRule.AUTHORITY);
		contentExtract.setLoadMethod(LoadMethod.FULL);

		final Location location = new Location();
		final Address address = new Address();

		location.setAddressKey(123L);
		location.setName("Test Store");

		address.setAddressKey("123");
		address.setCountry("UNITED STATES");
		address.setState("WASHINGTON");
		address.setCity("SEATTLE");
		address.setCounty("KING");
		address.setPostalCode("98035");
		address.setJurisdictionKey("456");

		contentExtract.getLocations().add(location);
		contentExtract.getAddresses().add(address);

		final Jurisdiction jurisdiction = new Jurisdiction();

		jurisdiction.setJurisdictionKey("456");

		final Authority authority = new Authority();

		authority.setAuthorityKey("789");
		authority.setAuthorityName("TestAuth");

		final JurisdictionAuthority jurisdictionAuthority = new JurisdictionAuthority();

		jurisdictionAuthority.setAuthorityKey("789");
		jurisdictionAuthority.setJurisdictionKey("456");

		contentExtract.getJurisdictions().add(jurisdiction);
		contentExtract.getAuthorities().add(authority);
		contentExtract.getJurisdictionAuthorities().add(jurisdictionAuthority);

		final Product product = new Product();

		product.setDescription("General Goods");
		product.setEffectiveDate(new EffectiveDate(LocalDateTime.of(2018, 9, 01, 00, 00), LocalDateTime.of(2018, 10, 01, 00, 00)));
		product.setName("GG");
		product.setProductCategory("General Goods");
		product.setProductKey("741");
		product.setProductCategoryKey(741L);

		contentExtract.getProducts().add(product);

		final AuthorityTreatmentMapping authorityTreatmentMapping1 = new AuthorityTreatmentMapping();
		final AuthorityTreatmentMapping authorityTreatmentMapping2 = new AuthorityTreatmentMapping();

		authorityTreatmentMapping1.setAuthorityKey("789");
		authorityTreatmentMapping1.setEffectiveDate(new EffectiveDate(LocalDateTime.of(2018, 9, 01, 00, 00), LocalDateTime.of(2018, 10, 01, 00, 00)));
		authorityTreatmentMapping1.setProductKey("741");
		authorityTreatmentMapping1.setTreatmentKey("654");

		authorityTreatmentMapping2.setAuthorityKey("789");
		authorityTreatmentMapping2.setEffectiveDate(new EffectiveDate(LocalDateTime.of(2018, 9, 01, 00, 00), LocalDateTime.of(2018, 10, 01, 00, 00)));
		authorityTreatmentMapping2.setProductKey("741");
		authorityTreatmentMapping2.setTreatmentKey("321");

		contentExtract.getAuthorityTreatmentMappings().add(authorityTreatmentMapping1);
		contentExtract.getAuthorityTreatmentMappings().add(authorityTreatmentMapping2);

		final TreatmentGroup treatmentGroup = new TreatmentGroup();

		treatmentGroup.setKey("987");

		final TreatmentGroupTreatment treatmentGroupTreatment1 = new TreatmentGroupTreatment();

		treatmentGroupTreatment1.setTreatmentGroupKey("987");
		treatmentGroupTreatment1.setTreatmentKey("654");

		final TreatmentGroupTreatment treatmentGroupTreatment2 = new TreatmentGroupTreatment();

		treatmentGroupTreatment2.setTreatmentGroupKey("987");
		treatmentGroupTreatment2.setTreatmentKey("321");

		final Treatment treatmentBasic = new Treatment();

		treatmentBasic.setTreatmentKey("654");
		treatmentBasic.setCalculationMethod("Tax On Gross Amount");
		treatmentBasic.setDisplayName("Test Treatment");
		treatmentBasic.setRate(BigDecimal.valueOf(.1));

		final Treatment treatmentTier = new Treatment();

		treatmentTier.setTreatmentKey("321");
		treatmentTier.setCalculationMethod("Tax On Gross Amount");
		treatmentTier.setDisplayName("Test Treatment Tier");
		treatmentTier.setTierList(new LinkedList<>());

		final Tier tier1 = new Tier();

		tier1.setHighValue(BigDecimal.valueOf(1000));
		tier1.setLowValue(BigDecimal.valueOf(100));
		tier1.setRate(BigDecimal.valueOf(9));
		tier1.setTreatmentKey(321L);

		final Tier tier2 = new Tier();

		tier2.setHighValue(BigDecimal.valueOf(100));
		tier2.setLowValue(BigDecimal.valueOf(0));
		tier2.setRate(BigDecimal.valueOf(11));
		tier2.setTreatmentKey(321L);

		treatmentTier.getTierList().add(tier1);
		treatmentTier.getTierList().add(tier2);

		contentExtract.getTreatmentGroups().add(treatmentGroup);
		contentExtract.getTreatmentGroupTreatments().add(treatmentGroupTreatment1);
		contentExtract.getTreatmentGroupTreatments().add(treatmentGroupTreatment2);
		contentExtract.getTreatments().add(treatmentBasic);
		contentExtract.getTreatments().add(treatmentTier);

		return contentExtract;
	}


	/**
	 * Prepare the scenario result for use in tests.
	 *
	 * @param grossAmount The gross amount used in the model scenario.
	 * @param expectedRate The rate expected from the model scenario.
	 *
	 * @return A populated scenario result.
	 */
	private UiScenarioResult prepareScenarioResult(final String grossAmount, final String expectedRate)
	{
		final UiScenarioResult result = new UiScenarioResult();
		final UiInvoiceTaxDetail taxDetail = new UiInvoiceTaxDetail();
		final UiLineTaxDetail lineTaxDetail = new UiLineTaxDetail();
		final UiAuthorityTaxDetail uiAuthorityTaxDetail = new UiAuthorityTaxDetail();

		uiAuthorityTaxDetail.setAuthorityName("TestAuth");
		uiAuthorityTaxDetail.setTaxRate(expectedRate);
		uiAuthorityTaxDetail.setTaxType("SA");
		uiAuthorityTaxDetail.setGrossAmount(grossAmount);

		lineTaxDetail.setAuthorityTaxDetails(Collections.singletonList(uiAuthorityTaxDetail));
		lineTaxDetail.setLineNumber(1L);
		lineTaxDetail.setGrossAmount(grossAmount);

		taxDetail.setLineTaxDetails(Collections.singletonList(lineTaxDetail));

		result.setInvoiceTaxDetail(taxDetail);
		result.setScenarioId(10L);
		result.setScenarioName("mod1");

		return result;
	}


	/**
	 * Prepare the company list that will be returned from Determination.
	 *
	 * @param uuid The company uuid to use.
	 * @param name The name to use.
	 * @param id The Determination ID to use.
	 *
	 * @return A populated list of companies from Determination.
	 */
	private UiCompanyList prepareUiCompanyList(final String uuid, final String name, final Long id)
	{
		final UiCompanyList uiCompanyList = new UiCompanyList();
		final LinkedList<UiCompany> uiCompanies = new LinkedList<>();
		final UiCompany uiCompany = new UiCompany();

		uiCompany.setCompanyId(id);
		uiCompany.setCompanyName(name);
		uiCompany.setCompanyUuid(uuid);

		uiCompanies.add(uiCompany);
		uiCompanyList.setCompanies(uiCompanies);

		return uiCompanyList;
	}


	/**
	 * Prepare the list of countries expected from Determination.
	 *
	 * @return A list of country zones.
	 */
	private List<ClientZone> prepareCountryList()
	{
		final List<ClientZone> clientZoneList = new LinkedList<>();
		final ClientZone clientZone = new ClientZone();

		clientZone.setName("UNITED STATES");
		clientZone.setChar2Code("US");
		clientZone.setChar3Code("USA");

		clientZoneList.add(clientZone);

		return clientZoneList;
	}


	/**
	 * Configure the test run data.
	 *
	 * @return A populated test run entity.
	 */
	private TestRun prepareTestRunData()
	{
		final TestRun testRun = new TestRun("Test01",
											"Config01",
											"detHost",
											"extractHost",
											"123",
											"user1",
											"password",
											"mod1",
											false);

		testRun.setTestRunNumber("1");
		testRun.setLineGrossAmounts(Collections.singletonList("100"));
		testRun.setEffectiveDate("2018-09-15");

		return testRun;
	}
}