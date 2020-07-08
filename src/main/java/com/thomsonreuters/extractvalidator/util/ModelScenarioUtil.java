package com.thomsonreuters.extractvalidator.util;


import com.thomsonreuters.extractvalidator.dto.content.ClientZone;
import com.thomsonreuters.extractvalidator.dto.determination.*;
import com.thomsonreuters.extractvalidator.dto.extract.content.Address;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;
import com.thomsonreuters.extractvalidator.dto.extract.content.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Methods to use for building model scenarios.
 *
 * @author Matt Godsey
 */
public final class ModelScenarioUtil
{
	/**
	 * Private constructor to avoid instantiation.
	 */
	private ModelScenarioUtil()
	{
		// Private to avoid class instatiation.
	}


	/**
	 * Build a basic model scenario setting the required data structures to be deemed valid by the Determination REST endpoint.
	 *
	 *
	 * @param contentExtract The extract to use for building lines.
	 * @param lineGrossAmounts A list of gross amount to use for the line, if null use default of 1000.
	 * @param modelScenarioName Name of the model scenario to use.
	 *
	 * @return A model scenario with default values, and lines built for each product in the content extract.
	 */
	public static UiModelScenarioDetail buildNewModelScenario(final String companyName,
															  final String companyUUID,
															  final String productCategoryName,
															  final ContentExtract contentExtract,
															  final List<String> lineGrossAmounts,
															  final String modelScenarioName,
															  final String taxType)
	{
		final UiModelScenarioDetail uiModelScenarioDetail = new UiModelScenarioDetail();

		// Date format expected by Model Scenario endpoint.

		uiModelScenarioDetail.setScenarioName(modelScenarioName);
		uiModelScenarioDetail.setApplyCredit("N");
		uiModelScenarioDetail.setAuditSpecification("NO_AUDIT");
		uiModelScenarioDetail.setCalculationType("F");

		final UiBasicCompany uiBasicCompany = new UiBasicCompany();

		uiBasicCompany.setCompanyName(companyName);
		uiBasicCompany.setCompanyUuid(companyUUID);

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

		final UiModelScenarioEstInfo establishments = new UiModelScenarioEstInfo();

		establishments.setIsShipTo(true);
		establishments.setIsShipFrom(true);

		uiModelScenarioDetail.setEstbBuyer(establishments);
		uiModelScenarioDetail.setEstbMiddleman(new UiModelScenarioEstInfo());
		uiModelScenarioDetail.setEstbSeller(establishments);

		final UiModelScenarioCalcs calcs= new UiModelScenarioCalcs();
		//calcs.setTaxCode("HOLIDAY");
		uiModelScenarioDetail.setModelScenarioCalcs(calcs);
		uiModelScenarioDetail.setModelScenarioOverrides(new UiModelScenarioOverrides());
		uiModelScenarioDetail.setModelScenarioReports(new UiModelScenarioReports());
		uiModelScenarioDetail.setModelScenarioTaxType(new UiModelScenarioTaxType(taxType));
		uiModelScenarioDetail.setScenarioRegistrations(new LinkedList<>());

		final UiOperatingLicenses operatingLicenses = new UiOperatingLicenses();

		operatingLicenses.setBuyerOperatingLicenses(new LinkedList<>());
		operatingLicenses.setSellerOperatingLicenses(new LinkedList<>());
		operatingLicenses.setAssumeBuyerFullyLicensed(false);
		operatingLicenses.setAssumeSellerFullyLicensed(false);

		uiModelScenarioDetail.setOperatingLicenses(operatingLicenses);
		uiModelScenarioDetail.setQualifiers(new LinkedList<>());

		uiModelScenarioDetail.setScenarioLines(buildScenarioLinesByProduct(contentExtract, productCategoryName,lineGrossAmounts));

		return uiModelScenarioDetail;
	}


	/**
	 * Build the set of locations to be used in the model scenario based on the address from the extract.<p />
	 *
	 * DEVELOPERS NOTE: Currently only modeling a sales tax transaction with ship from and ship to in the same location. Also only US at the moment.
	 *
	 * @param address The address to use.
	 * @param countryList List of countries with two character codes.
	 *
	 * @return A set of model scenario locations.
	 */
	public static List<UiModelScenarioLocation> buildLocations(final Address address, final List<ClientZone> countryList, String extractName)
	{
		final List<UiModelScenarioLocation> locations = new LinkedList<>();
		final List<String> locationTypes = Arrays.asList("SHIP_TO", "SHIP_FROM", "SELLER_PRIMARY", "BUYER_PRIMARY", "ORDER_ACCEPTANCE", "ORDER_ORIGIN", "SUPPLY", "BILL_TO", "MIDDLEMAN");

		String country2code = null;

		for (final ClientZone country : countryList)
		{
			if (country.getName().equals(address.getCountry()))
			{
				country2code = country.getChar2Code();
				break;
			}
		}
		String postalRange= address.getGeocode();
		String splitRange[];
		String geocode=null;

		if(postalRange != null){
			splitRange=postalRange.split("-");
			geocode=splitRange[0].trim();
		}
		for (final String locationType : locationTypes)
		{
			final UiModelScenarioLocation location = new UiModelScenarioLocation();

			location.setLocationType(locationType);
			location.setCountry(country2code);

			if (locationType.equals("SHIP_TO") || (locationType.equals("SHIP_FROM") && !extractName.contains("WayfairUAT_01_AL")))
			{
				location.setProvince(address.getProvince());
				location.setState(address.getState());
				location.setDistrict(address.getDistrict());
				location.setCounty(address.getCounty());
				location.setCity(address.getCity());
				location.setPostalCode(address.getPostalCode());
				location.setGeoCode(geocode);
			}

			if(extractName.contains("WayfairUAT_01_AL")){
				if(locationType.equals("SHIP_FROM")){
					location.setState("CALIFORNIA");
					location.setCounty("UNITED STATES");
					location.setPostalCode("95823");
					location.setGeoCode("3000");
				}
			}


			locations.add(location);
		}

		return locations;
	}


	/**
	 * Build a list of model scenario lines for all products in the extract. Using a default gross amount of 1000.
	 *
	 * @param contentExtract The content extract data.
	 * @param lineGrossAmounts A list of gross amounts to use for the line, default to 1000 if null.
	 *
	 * @return The list of lines to use in the model scenario.
	 */
	private static List<UiModelScenarioLine> buildScenarioLinesByProduct(final ContentExtract contentExtract, final String productCategoryName, final List<String> lineGrossAmounts)
	{
		final List<UiModelScenarioLine> scenarioLines = new LinkedList<>();
		final List<BigDecimal> grossAmounts = new LinkedList<>();

		// Pull the gross amounts from the list and create big decimals out of the strings.
		for (final String stringGrossAmount : lineGrossAmounts)
		{
			final BigDecimal doubleValue = BigDecimal.valueOf(Double.parseDouble(stringGrossAmount));
			grossAmounts.add(doubleValue);
		}

		if (grossAmounts.isEmpty())
		{
			grossAmounts.add(BigDecimal.valueOf(1000.00));
		}

		Long lineNumberCounter = 1L;

		if(null != contentExtract.getProducts()) {

			for (final BigDecimal lineGrossAmount : grossAmounts) {
				for (final Product product : contentExtract.getProducts()) {
					if(productCategoryName!=null && !product.getProductCategory().contains(productCategoryName)){
						continue;
					}
						final UiModelScenarioLine line = new UiModelScenarioLine();

						line.setDelete(false);
						line.setGrossAmount(lineGrossAmount);
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
						lineDetail.setScenarioLineCalcs(new UiModelScenarioLineCalcs());

						line.setScenarioLinesDetails(lineDetail);

						scenarioLines.add(line);

				}
			}
		}
		return scenarioLines;
	}
}
