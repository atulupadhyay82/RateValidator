package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import javax.validation.Valid;

import lombok.Data;


/**
 * Holds the invoice tax results.
 *
 * @author NagarajDiveela
 */
@Data
public final class UiInvoiceTaxDetail extends UiTaxDetail
{
	/**
	 * Holds bill of lading.
	 */
	@Valid
	private UiBillOfLading billOfLading;

	/**
	 * Holds line tax details.
	 */
	private List<UiLineTaxDetail> lineTaxDetails;
}
