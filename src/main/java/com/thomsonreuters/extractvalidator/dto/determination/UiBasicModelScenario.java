package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario basic information.
 *
 * @author Velayoudame Mouttoucomarasamy
 */
@Data
public class UiBasicModelScenario
{
	/**
	 * Holds the scenario ID for the model scenario.
	 */
	private Long scenarioId;

	/**
	 * Holds the scenario name for the model scenario.
	 */
	@NotNull(message = "Scenario Name is Required.")
	@Size(min = 1, max = 100, message = "Minimum and Maximum length of ScenarioName is 1 and 100 respectively.")
	private String scenarioName;
}
