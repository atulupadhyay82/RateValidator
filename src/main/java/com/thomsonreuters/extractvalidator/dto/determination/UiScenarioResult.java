package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * Holds the scenario results.
 *
 * @author NagarajDiveela
 */
@Data
public final class UiScenarioResult extends UiBasicModelScenario
{
	/**
	 * Holds the currency object.
	 */
	private UiCurrency currency;

	/**
	 * Holds invoice tax detail.
	 */
	private UiInvoiceTaxDetail invoiceTaxDetail;

	/**
	 * Holds input xml.
	 */
	private String inputXml;

	/**
	 * Holds output xml.
	 */
	private String outputXml;
}
