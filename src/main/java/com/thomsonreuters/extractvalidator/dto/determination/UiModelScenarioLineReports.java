package com.thomsonreuters.extractvalidator.dto.determination;


import java.math.BigDecimal;

import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the reports information of the model scenario line specific for line level.
 *
 * @author KarthickMoorthy
 */
@Data
public final class UiModelScenarioLineReports extends UiModelScenarioBasicReports
{
	/**
	 * Holds the mass.
	 */
	private BigDecimal mass;

	/**
	 * Holds the supplementary unit.
	 */
	@Size(max = 5, message = "Maximum Size allowed for supplementary unit is 5.")
	private String supplementaryUnit;
}

