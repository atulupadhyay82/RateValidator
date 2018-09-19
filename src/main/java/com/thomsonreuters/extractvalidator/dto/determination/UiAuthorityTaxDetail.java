package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * Holds the authority tax results.
 *
 * @author NagarajDiveela
 */
@Data
public final class UiAuthorityTaxDetail extends UiTaxDetail
{
	/**
	 * Holds zone level.
	 */
	private String zoneLevel;

	/**
	 * Holds zone name.
	 */
	private String zoneName;

	/**
	 * Holds authority name.
	 */
	private String authorityName;

	/**
	 * Holds invoice description.
	 */
	private String invoiceDescription;

	/**
	 * Holds tax type.
	 */
	private String taxType;

	/**
	 * Holds tax direction.
	 */
	private String taxDirection;

	/**
	 * Holds fee object.
	 */
	private UiFee flatFee;

	/**
	 * Holds tax rate.
	 */
	private String taxRate;

	/**
	 * Holds tax amount.
	 */
	private String taxAmount;

	/**
	 * Holds fee amount.
	 */
	private String feeAmount;

	/**
	 * Holds rule order.
	 */
	private String ruleOrder;

	/**
	 * Holds jurisdiction text.
	 */
	private String jurisdictionText;

	/**
	 * Holds tax treatment.
	 */
	private String taxTreatment;

	/**
	 * Holds inclusive tax.
	 */
	private String inclusiveTax;

	/**
	 * Holds rate code.
	 */
	private String rateCode;
}
