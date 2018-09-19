package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the reports information of the model scenario specific for document level.
 *
 * @author KarthickMoorthy
 */
@Data
public final class UiModelScenarioReports extends UiModelScenarioBasicReports
{
	/**
	 * Holds the nature of transaction code.
	 */
	@Size(max = 2, message = "Maximum Size allowed for nature of transaction is 2.")
	private String natureOfTransactionCode;

	/**
	 * Holds the statistical procedure.
	 */
	@Size(max = 6, message = "Maximum Size allowed for statistical procedure is 6.")
	private String statisticalProcedure;
}
