package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the basic reports information of the model scenario for Document level and Line level reports information.
 *
 * @author KarthickMoorthy
 */
@Data
public class UiModelScenarioBasicReports
{
	/**
	 * Holds the country of origin.
	 */
	@Size(max = 100, message = "Maximum Size allowed for country of origin is 100.")
	private String countryOfOrigin;

	/**
	 * Holds the department of consign.
	 */
	@Size(max = 100, message = "Maximum Size allowed for dept of consign is 100.")
	private String deptOfConsign;

	/**
	 * Holds the mode of transport.
	 */
	@Size(max = 100, message = "Maximum Size allowed for mode of transport is 100.")
	private String modeOftransport;

	/**
	 * Holds the port of entry.
	 */
	@Size(max = 100, message = "Maximum Size allowed for port of entry is 100.")
	private String portOfEntry;

	/**
	 * Holds the port of loading.
	 */
	@Size(max = 5, message = "Maximum Size allowed for port of loading is 5.")
	private String portOfLoading;

	/**
	 * Holds the regime.
	 */
	@Size(max = 100, message = "Maximum Size allowed for regime  is 100.")
	private String regime;
}
