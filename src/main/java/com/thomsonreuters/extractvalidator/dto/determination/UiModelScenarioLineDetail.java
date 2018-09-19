package com.thomsonreuters.extractvalidator.dto.determination;


import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario line detail information.
 *
 * @author Tejesh KethamReddy
 */
@Data
public final class UiModelScenarioLineDetail
{
	/**
	 * Holds the blended flag.
	 */
	private Boolean blended;

	/**
	 * Holds the exchange flag.
	 */
	private Boolean exchange;

	/**
	 * Holds the operating licenses.
	 */
	@Valid
	private UiOperatingLicenses operatingLicenses;

	/**
	 * Holds the vendor name.
	 */
	@Size(max = 100, message = "Vendor name must not exceed 100 Characters.")
	private String vendorName;

	/**
	 * Holds the vendor number.
	 */
	@Size(max = 100, message = "Vendor number must not exceed 100 Characters.")
	private String vendorNumber;

	/**
	 * Holds the customer name.
	 */
	@Size(max = 100, message = "Customer name must not exceed 100 Characters.")
	private String customerName;

	/**
	 * Holds the customer number.
	 */
	@Size(max = 100, message = "Customer number must not exceed 100 Characters.")
	private String customerNumber;

	/**
	 * Holds the point of title transfer for the model scenario line.
	 */
	@Size(min = 1, max = 1, message = "Maximum length of point of title transfer allowed is 1")
	@Pattern(regexp = "[DIO]", message = "Point of transfer value is not valid.")
	private String pointOfTitleTransfer;

	/**
	 * Holds the transaction type for the model scenario line.
	 */
	@Size(min = 1, max = 2, message = "Transaction Type Code must not exceed 2 Characters.")
	private String transactionType;

	/**
	 * Holds the delivery term code for the model scenario line.
	 */
	@Size(max = 100, message = "Maximum length of delivery term code allowed is 100.")
	private String deliveryTermCode;

	/**
	 * Holds the calcs information for the model scenario line.
	 */
	@Valid
	private UiModelScenarioLineCalcs scenarioLineCalcs;

	/**
	 * Holds the model scenario unit of measure code value.
	 */
	@Size(max = 25, message = "Maximum length of unit of measure code allowed is 25.")
	private String unitOfMeasureCode;

	/**
	 * Holds the vat group registration number for the model scenario line.
	 */
	@Size(max = 50, message = "Maximum length of vat group registration allowed is 50.")
	private String vatGroupRegistrationNumber;

	/**
	 * Holds the locationSet name value for the model scenario.
	 */
	@Size(max = 60, message = "Maximum length of location set allowed is 60.")
	private String locationSetName;

	/**
	 * Holds bill of lading for model scenario line.
	 */
	@Valid
	private UiBillOfLading billOfLading;

	/**
	 * Holds the establishment Buyer Info in model scenario line.
	 */
	private UiModelScenarioEstInfo estbBuyer;

	/**
	 * Holds the establishment Seller info in model scenario line.
	 */
	private UiModelScenarioEstInfo estbSeller;

	/**
	 * Holds the establishment Middleman info in model scenario line.
	 */
	private UiModelScenarioEstInfo estbMiddleman;

	/**
	 * Holds the model scenario line overrides information.
	 */
	@Valid
	private UiModelScenarioOverrides modelScenarioLineOverrides;

	/**
	 * Holds Country level authority exemption details at line level.
	 */
	@Valid
	private UiModelScenarioExemptions countryExemption;

	/**
	 * Holds Province level authority exemption details at line level.
	 */
	@Valid
	private UiModelScenarioExemptions provinceExemption;

	/**
	 * Holds State level authority exemption details at line level.
	 */
	@Valid
	private UiModelScenarioExemptions stateExemption;

	/**
	 * Holds County level authority exemption details at line level.
	 */
	@Valid
	private UiModelScenarioExemptions countyExemption;

	/**
	 * Holds City level authority exemption details at line level.
	 */
	@Valid
	private UiModelScenarioExemptions cityExemption;

	/**
	 * Holds District level authority exemption details at line level.
	 */
	@Valid
	private UiModelScenarioExemptions districtExemption;

	/**
	 * Holds Postal Code level authority exemption details at line level.
	 */
	@Valid
	private UiModelScenarioExemptions postalCodeExemption;

	/**
	 * Holds Geo Code level authority exemption details at line level.
	 */
	@Valid
	private UiModelScenarioExemptions geoCodeExemption;

	/**
	 * Holds the tax type information for the model scenario line.
	 */
	@Valid
	private UiModelScenarioTaxType modelScenarioLineTaxType;

	/**
	 * Holds the reports information for the model scenario line.
	 */
	@Valid
	private UiModelScenarioLineReports modelScenarioLineReports;

	/**
	 * Holds the model scenario line registration information.
	 */
	@Valid
	private List<UiModelScenarioRegistration> scenarioLineRegistrations;

	/**
	 * Holds the list of custom line values for the model scenario.
	 */
	@Valid
	private List<UiModelScenarioCustomField> scenarioLineCustomFields;

	/**
	 * Holds the model scenario licenses information.
	 */
	@Valid
	private List<UiModelScenarioLicense> scenarioLineLicenses;

	/**
	 * Holds the list of locations specified for the model scenario line.
	 */
	@Valid
	@Size(max = 9, message = "Maximum location types allowed is 9.")
	private List<UiModelScenarioLocation> scenarioLineLocations;

	/**
	 * Holds the list of qualifiers, also known as End Uses.
	 */
	@Valid
	private List<UiModelScenarioQualifier> scenarioLineQualifiers;

	/**
	 * Holds the model scenario line net quantity.
	 */
	private BigDecimal netQuantity;

	/**
	 * Holds the model scenario line unit of net quantity code value.
	 */
	@Size(max = 25, message = "Maximum length of net quantity unit of code allowed is 25.")
	private String netQuantityUnitOfCode;

	/**
	 * Holds the flag that indicates whether to use net or gross quantity.
	 */
	@Pattern(regexp = "[YN]", message = "Apply net quantity flag value is not valid. It must be either Y or N.")
	private String applyNetQuantity;
}
