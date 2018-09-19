package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import javax.validation.Valid;

import lombok.Data;


/**
 * Holds the line tax results.
 *
 * @author NagarajDiveela
 */
@Data
public final class UiLineTaxDetail extends UiTaxDetail
{
	/**
	 * Holds authority tax details.
	 */
	private List<UiAuthorityTaxDetail> authorityTaxDetails;

	/**
	 * Holds bill of lading for line.
	 */
	@Valid
	private UiBillOfLading billOfLading;

	/**
	 * Holds rounded gross amount.
	 */
	private String roundedGrossAmount;

	/**
	 * Holds tax plus gross amount.
	 */
	private String taxPlusGross;

	/**
	 * Holds inclusive taxes.
	 */
	private String inclusiveTaxes;

	/**
	 * Holds line number.
	 */
	private Long lineNumber;
}
