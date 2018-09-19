package com.thomsonreuters.extractvalidator.dto.determination;


import java.math.BigDecimal;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the basic calcs information of the model scenario for Document level and Line level calcs information.
 *
 * @author Jason (Po-sheng Wang)
 */
@Data
public class UiModelScenarioBasicCalcs
{
	/**
	 * Holds the basic percentage for the model scenario.
	 */
	private BigDecimal basisPercentage;

	/**
	 * Holds the business supply information for the model scenario.
	 */
	@Pattern(regexp = "[Y|N]", message = "Business Supply indicator value is not valid.")
	private String businessSupply;

	/**
	 * Holds the simplification for the model scenario.
	 */
	private Boolean isSimplification;

	/**
	 * Holds the movement type for the model scenario.
	 */
	@Size(max = 100, message = "Maximum length of the movement type allowed is 100.")
	private String movementType;

	/**
	 * Holds the movement date for the model scenario.
	 */
	private String movementDate;

	/**
	 * Holds the tax code for the model scenario.
	 */
	@Size(max = 50, message = "Maximum Size allowed for tax code is 50.")
	private String taxCode;
}
