package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario calcs information specific on Document level.
 *
 * @author Jason (Po-sheng Wang)
 */
@Data
public final class UiModelScenarioCalcs extends UiModelScenarioBasicCalcs
{
	/**
	 * Holds the output rounding for the model scenario.
	 */
	private Boolean isOutputRounding;

	/**
	 * Holds the original invoice number for the model scenario.
	 */
	@Size(max = 200, message = "Maximum Size allowed for original invoice number is 200.")
	private String originalInvoiceNumber;
}
