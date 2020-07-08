package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;

import java.util.List;


/**
 * Holds the base tax detail information.
 *
 * @author NagarajDiveela
 */
@Data
public class UiTaxDetail
{
	/**
	 * Holds messages.
	 */

	private List<UiMessage> messages;

	/**
	 * Holds total tax amount.
	 */
	private String totalTaxAmount;

	/**
	 * Holds total fee amount.
	 */
	private String totalFeeAmount;

	/**
	 * Holds withholding taxes.
	 */
	private String withholdingTaxes;

	/**
	 * Holds effective tax rate.
	 */
	private String effectiveTaxRate;

	/**
	 * Holds total amount.
	 */
	private String totalAmount;

	/**
	 * Holds gross amount.
	 */
	private String grossAmount;

	/**
	 * Holds gross minus withholding.
	 */
	private String grossMinusWithholding;

	/**
	 * Holds the basis percent.
	 */
	private String basisPercent;

	/**
	 * Holds exempt amount.
	 */
	private String exemptAmount;
}
