package com.thomsonreuters.extractvalidator.dto.determination;


import java.math.BigDecimal;

import lombok.Data;


/**
 * Holds the model scenario overrides information for a given authority type.
 *
 * @author Bala Yaddanapudi
 */
@Data
public final class UiModelScenarioOverrideDetail
{
	/**
	 * Refers to the 'noTax' attribute for an authority type.
	 */
	private Boolean noTax;

	/**
	 * Refers to the 'rate' attribute for an authority type.
	 */
	private BigDecimal rate;

	/**
	 * Refers to the 'amount' attribute for an authority type.
	 */
	private BigDecimal amount;

	/**
	 * Refers to the 'supply exempt percentage' attribute for an authority type.
	 */
	private BigDecimal supplyExemptPercentage;
}