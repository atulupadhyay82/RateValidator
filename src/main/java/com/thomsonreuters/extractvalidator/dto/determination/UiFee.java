package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * Holds the fee information.
 *
 * @author NagarajDiveela
 */
@Data
public final class UiFee
{
	/**
	 * Holds the fee amount.
	 */
	private String amount;

	/**
	 * Holds the unit code.
	 */
	private String unitOfMeasureCode;
}
