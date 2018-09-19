package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario information.
 *
 * @author Jositha Parampil
 */
@Data
public class UiModelScenario extends UiBasicModelScenario
{
	/**
	 * Holds bill of lading for model scenario.
	 */
	@Valid
	private UiBillOfLading billOfLading;

	/**
	 * Holds the buyer primary address.
	 */
	private UiBasicAddress buyerPrimary;

	/**
	 * Holds the basic company information.
	 */
	private UiBasicCompany company;

	/**
	 * DTO {@link UiCompanyRelatedGroup } to hold the properties for the customer group assigned for the company.
	 */
	private UiCompanyRelatedGroup customerGroup;

	/**
	 * Holds the effective date for the model scenario.
	 */
	@NotNull(message = "Effective Date is required.")
	private String effectiveDate;

	/**
	 * DTO {@link UiCompanyRelatedGroup } to hold the properties for the product cross reference group assigned for the company.
	 */
	private UiCompanyRelatedGroup productCrossReferenceGroup;

	/**
	 * Holds the role Name for the model scenario.
	 */
	@NotNull(message = "Company Role is required.")
	@Size(min = 1, max = 1, message = "Company Role size is 1 character.")
	@Pattern(regexp = "[BSM]", message = "Company Role is not valid.")
	private String roleName;

	/**
	 * Holds the ship from address.
	 */
	private UiBasicAddress shipFrom;

	/**
	 * Holds the ship to address.
	 */
	private UiBasicAddress shipTo;

	/**
	 * Holds the seller primary address.
	 */
	private UiBasicAddress sellerPrimary;

	/**
	 * Holds the transaction type for the model scenario.
	 */
	@NotNull(message = "Transaction type is required.")
	@Pattern(regexp = "^[A-Z]{2}$", message = "Transaction type is not valid.")
	private String transactionType;

	/**
	 * Holds the audit data for the model scenario.
	 */
	private UiEntityAudit uiEntityAudit;
}
