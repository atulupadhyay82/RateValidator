package com.thomsonreuters.extractvalidator.util;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.thomsonreuters.extractvalidator.dto.determination.UiBasicCompany;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompany;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioCalcs;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioEstInfo;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioExemptions;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioLine;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioLineDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioLocation;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioOverrides;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioReports;
import com.thomsonreuters.extractvalidator.dto.determination.UiModelScenarioTaxType;
import com.thomsonreuters.extractvalidator.dto.determination.UiOperatingLicenses;
import com.thomsonreuters.extractvalidator.dto.extract.content.Address;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;
import com.thomsonreuters.extractvalidator.dto.extract.content.Product;


/**
 * ModelScenarioUtil Description.
 *
 * @author Matt Godsey
 */
public final class ModelScenarioUtil
{
	private ModelScenarioUtil()
	{
		// Private to avoid class instatiation.
	}


	/**
	 * Build a basic model scenario setting the required data structures to be deemed valid by the Determination REST endpoint.
	 *
	 * @param company The company to use.
	 * @param contentExtract The extract to use for building lines.
	 * @param effectiveDate The effective date to use.
	 *
	 * @return A model scenario with default values, and lines built for each product in the content extract.
	 */
	public static UiModelScenarioDetail buildNewModelScenario(final UiCompany company, final ContentExtract contentExtract, final LocalDateTime effectiveDate)
	{
		final UiModelScenarioDetail uiModelScenarioDetail = new UiModelScenarioDetail();
		final String scenarioName = "Validator-Test-Scenario";
		final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		final String scenarioDate = effectiveDate.format(dateTimeFormatter);

		// TODO: Change effective date to content extract effective date?
		uiModelScenarioDetail.setEffectiveDate(scenarioDate);
		uiModelScenarioDetail.setScenarioName(scenarioName);
		uiModelScenarioDetail.setApplyCredit("N");
		uiModelScenarioDetail.setAuditSpecification("NO_AUDIT");
		uiModelScenarioDetail.setCalculationType("F");

		final UiBasicCompany uiBasicCompany = new UiBasicCompany();

		uiBasicCompany.setCompanyName(company.getCompanyName());
		uiBasicCompany.setCompanyUuid(company.getCompanyUuid());

		uiModelScenarioDetail.setCompany(uiBasicCompany);
		uiModelScenarioDetail.setCurrencyCode("USD");
		uiModelScenarioDetail.setDocumentNumber("ExtractValidationTest");
		uiModelScenarioDetail.setPointOfTitleTransfer("I");
		uiModelScenarioDetail.setRoleName("S");
		uiModelScenarioDetail.setTransactionType("GS");
		uiModelScenarioDetail.setStateExemption(new UiModelScenarioExemptions());
		uiModelScenarioDetail.setProvinceExemption(new UiModelScenarioExemptions());
		uiModelScenarioDetail.setDistrictExemption(new UiModelScenarioExemptions());
		uiModelScenarioDetail.setCountryExemption(new UiModelScenarioExemptions());
		uiModelScenarioDetail.setCountyExemption(new UiModelScenarioExemptions());
		uiModelScenarioDetail.setCityExemption(new UiModelScenarioExemptions());
		uiModelScenarioDetail.setPostalCodeExemption(new UiModelScenarioExemptions());
		uiModelScenarioDetail.setGeoCodeExemption(new UiModelScenarioExemptions());
		uiModelScenarioDetail.setCustomDocumentFields(new LinkedList<>());
		uiModelScenarioDetail.setEstbBuyer(new UiModelScenarioEstInfo());
		uiModelScenarioDetail.setEstbMiddleman(new UiModelScenarioEstInfo());
		uiModelScenarioDetail.setEstbSeller(new UiModelScenarioEstInfo());
		uiModelScenarioDetail.setModelScenarioCalcs(new UiModelScenarioCalcs());
		uiModelScenarioDetail.setModelScenarioOverrides(new UiModelScenarioOverrides());
		uiModelScenarioDetail.setModelScenarioReports(new UiModelScenarioReports());
		uiModelScenarioDetail.setModelScenarioTaxType(new UiModelScenarioTaxType());
		uiModelScenarioDetail.setScenarioRegistrations(new LinkedList<>());

		final UiOperatingLicenses operatingLicenses = new UiOperatingLicenses();

		operatingLicenses.setBuyerOperatingLicenses(new LinkedList<>());
		operatingLicenses.setSellerOperatingLicenses(new LinkedList<>());
		operatingLicenses.setAssumeBuyerFullyLicensed(false);
		operatingLicenses.setAssumeSellerFullyLicensed(false);

		uiModelScenarioDetail.setOperatingLicenses(operatingLicenses);
		uiModelScenarioDetail.setQualifiers(new LinkedList<>());

		uiModelScenarioDetail.setScenarioLines(buildScenarioLinesByProduct(contentExtract));

		return uiModelScenarioDetail;
	}


	/**
	 * Build the set of locations to be used in the model scenario based on the address from the extract.<p />
	 *
	 * DEVELOPERS NOTE: Currently only modeling a sales tax transaction with ship from and ship to in the same location. Also only US at the moment.
	 *
	 * @param address The address to use.
	 *
	 * @return A set of model scenario locations.
	 */
	public static List<UiModelScenarioLocation> buildLocations(final Address address)
	{
		final List<UiModelScenarioLocation> locations = new LinkedList<>();
		final List<String> locationTypes = Arrays.asList("SHIP_TO", "SHIP_FROM", "SELLER_PRIMARY", "BUYER_PRIMARY", "ORDER_ACCEPTANCE", "ORDER_ORIGIN", "SUPPLY", "BILL_TO", "MIDDLEMAN");

		for (final String locationType : locationTypes)
		{
			final UiModelScenarioLocation location = new UiModelScenarioLocation();

			location.setLocationType(locationType);

			// TODO: Copy country lookup from CM to get 2 char codes here for country name.
			location.setCountry("US");

			if (locationType.equals("SHIP_FROM") || locationType.equals("SHIP_TO"))
			{
				location.setProvince(address.getProvince());
				location.setState(address.getState());
				location.setDistrict(address.getDistrict());
				location.setCounty(address.getCounty());
				location.setCity(address.getCity());
				location.setPostalCode(address.getPostalCode());
				location.setGeoCode(address.getGeocode());
			}

			locations.add(location);
		}

		return locations;
	}


	/**
	 * Build a list of model scenario lines for all products in the extract. Using a default gross amount of 1000.
	 *
	 * @param contentExtract The content extract data.
	 *
	 * @return The list of lines to use in the model scenario.
	 */
	private static List<UiModelScenarioLine> buildScenarioLinesByProduct(final ContentExtract contentExtract)
	{
		final List<UiModelScenarioLine> scenarioLines = new LinkedList<>();

		Long lineNumberCounter = 1L;

		for (final Product product : contentExtract.getProducts())
		{
			final UiModelScenarioLine line = new UiModelScenarioLine();

			line.setDelete(false);
			line.setGrossAmount(new BigDecimal(1000));
			line.setLineNumber(lineNumberCounter++);
			line.setQuantity(new BigDecimal(1));
			line.setProductCode(product.getName());

			final UiModelScenarioLineDetail lineDetail = new UiModelScenarioLineDetail();

			lineDetail.setApplyNetQuantity("N");
			lineDetail.setPointOfTitleTransfer("I");
			lineDetail.setTransactionType("GS");
			lineDetail.setScenarioLineCustomFields(new LinkedList<>());
			lineDetail.setScenarioLineLicenses(new LinkedList<>());
			lineDetail.setScenarioLineLocations(new LinkedList<>());
			lineDetail.setScenarioLineQualifiers(new LinkedList<>());
			lineDetail.setScenarioLineRegistrations(new LinkedList<>());

			line.setScenarioLinesDetails(lineDetail);

			scenarioLines.add(line);
		}

		return scenarioLines;
	}
}
