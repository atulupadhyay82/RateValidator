package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario detail information.
 *
 * @author Jositha Parampil
 */
@Data
public final class UiModelScenarioDetail extends UiModelScenario
{
	/**
	 * Holds the info on the type of audit to be done for the model scenario.
	 */
	private String auditSpecification;

	/**
	 * Holds the allocation group owner
	 */
	@Size(max = 100, message = "Maximum length of Allocation Group Owner is 100.")
	private String allocationGroupOwner;

	/**
	 * Holds the allocation group name
	 */
	@Size(max = 100, message = "Maximum length of Allocation Group Name is 100.")
	private String allocationGroupName;

	/**
	 * Holds the allocation name
	 */
	@Size(max = 100, message = "Maximum length of Allocation Name is 100.")
	private String allocationName;

	/**
	 * Holds the credit information for the model scenario.
	 */
	@Size(max = 1, message = "Maximum length of credit info allowed is 1.")
	@Pattern(regexp = "[YN]", message = "Apply Credit value is not valid.")
	private String applyCredit;

	/**
	 * Holds the calculation type for the model scenario.
	 */
	@NotNull(message = "Calculation Type is required.")
	@Size(min = 1, max = 1, message = "Calculation Type Code size is 1 character.")
	@Pattern(regexp = "[FRT]", message = "Calculation Type is not valid.")
	private String calculationType;

	/**
	 * Holds the comment for the model scenario.
	 */
	@Size(max = 2000, message = "Maximum length of comment allowed is 2000.")
	private String comment;

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
	 * Holds the currency code for the model scenario.
	 */
	@NotNull(message = "Currency is required.")
	@Size(min = 3, max = 3, message = "Currency Code size is 3 character.")
	private String currencyCode;

	/**
	 * Holds the list of custom document values for the model scenario.
	 */
	@Valid
	private List<UiModelScenarioCustomField> customDocumentFields;

	/**
	 * Holds County level authority exemption details.
	 */
	@Valid
	private UiModelScenarioExemptions countyExemption;

	/**
	 * Holds City level authority exemption details.
	 */
	@Valid
	private UiModelScenarioExemptions cityExemption;

	/**
	 * Holds Country level authority exemption details.
	 */
	@Valid
	private UiModelScenarioExemptions countryExemption;

	/**
	 * Holds District level authority exemption details.
	 */
	@Valid
	private UiModelScenarioExemptions districtExemption;

	/**
	 * Holds the delivery term code for the model scenario.
	 */
	@Size(max = 100, message = "Maximum length of delivery term code allowed is 100.")
	private String deliveryTermCode;

	/**
	 * Holds the document number for the model scenario.
	 */
	@Size(max = 200, message = "Maximum length of document number allowed is 200.")
	private String documentNumber;

	/**
	 * Holds the exchange flag for invoice/scenario level.
	 */
	private Boolean exchange;

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
	 * Holds the filter group name, also known as TransEditor
	 */
	@Size(max = 100, message = "Maximum length of Filter Group Name is 100.")
	private String filterGroupName;

	/**
	 * Holds the filter group owner, also known as TransEditor
	 */
	@Size(max = 200, message = "Maximum length of Filter Group Owner is 200.")
	private String filterGroupOwner;

	/**
	 * Holds Geo Code level authority exemption details.
	 */
	@Valid
	private UiModelScenarioExemptions geoCodeExemption;

	/**
	 * Holds the location set value for the model scenario.
	 */
	private String locationSet;

	/**
	 * Holds the list of locations specified for the model scenario.
	 */
	@Valid
	@Size(max = 9, message = "Maximum location types allowed is 9.")
	private List<UiModelScenarioLocation> locationList;

	/**
	 * Holds the model scenario report information.
	 */
	@Valid
	private UiModelScenarioReports modelScenarioReports;

	/**
	 * Holds the model scenario tax type information.
	 */
	@Valid
	private UiModelScenarioTaxType modelScenarioTaxType;

	/**
	 * Holds the model scenario overrides information.
	 */
	@Valid
	private UiModelScenarioOverrides modelScenarioOverrides;

	/**
	 * Holds the calcs information for the model scenario.
	 */
	@Valid
	private UiModelScenarioCalcs modelScenarioCalcs;

	/**
	 * Holds the operating licenses for the model scenario.
	 */
	@Valid
	private UiOperatingLicenses operatingLicenses;

	/**
	 * Holds the product qualifier group name, also known as Conditional Mapping
	 */
	@Size(max = 100, message = "Maximum length of Product Qualifier Group Name is 100.")
	private String productQualifierGroupName;

	/**
	 * Holds the product qualifier group owner, also known as Conditional Mapping
	 */
	@Size(max = 100, message = "Maximum length of Product Qualifier Group Owner is 100.")
	private String productQualifierGroupOwner;

	/**
	 * Holds Postal Code level authority exemption details.
	 */
	@Valid
	private UiModelScenarioExemptions postalCodeExemption;

	/**
	 * Holds the point of title transfer for the model scenario.
	 */
	@NotNull(message = "Point of Title Transfer is required.")
	@Size(min = 1, max = 1, message = "Maximum length of point of title transfer allowed is 1.")
	@Pattern(regexp = "[DIO]", message = "point of transfer value is not valid.")
	private String pointOfTitleTransfer;

	/**
	 * Holds Province level authority exemption details.
	 */
	@Valid
	private UiModelScenarioExemptions provinceExemption;

	/**
	 * Holds the list of qualifiers, also known as End Uses.
	 */
	@Valid
	private List<UiModelScenarioQualifier> qualifiers;

	/**
	 * Holds the list of scenario line basic information of the model scenario line.
	 */
	@Valid
	private List<UiModelScenarioLine> scenarioLines;

	/**
	 * Holds the model scenario registration information.
	 */
	@Valid
	private List<UiModelScenarioRegistration> scenarioRegistrations;

	/**
	 * Holds the model scenario licenses information.
	 */
	@Valid
	private List<UiModelScenarioLicense> scenarioLicenses;

	/**
	 * Holds State level authority exemption details.
	 */
	@Valid
	private UiModelScenarioExemptions stateExemption;

	/**
	 * Holds the tax code qualifier group name, also known as ERP CODE
	 */
	@Size(max = 100, message = "Maximum length of Tax Code Qualifier Group Name is 100.")
	private String taxCodeQualifierGroupName;

	/**
	 * Holds the tax code qualifier group owner, also known as ERP CODE
	 */
	@Size(max = 100, message = "Maximum length of Tax Code Qualifier Group Owner is 100.")
	private String taxCodeQualifierGroupOwner;

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
	 * Holds the vat group registration number for the model scenario.
	 */
	private String vatGroupRegistrationNumber;
}
