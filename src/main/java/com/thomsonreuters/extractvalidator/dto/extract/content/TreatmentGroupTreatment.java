package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import com.thomsonreuters.extractvalidator.util.ActivityCode;
import com.thomsonreuters.extractvalidator.util.IClientChangeType;


/**
 * Encapsulates the treatment group treatment data as it will be communicated by the CE REST service.
 *
 * @author Eli Laudi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TreatmentGroupTreatment implements IClientChangeType
{
	/**
	 * Numeric hash key associated with the treatment group treatment
	 */
	private String key;

	/**
	 * Numeric hash key associated with a treatment group
	 */
	private String treatmentGroupKey;

	/**
	 * List of numeric hash keys associated with a treatment
	 */
	private String treatmentKey;

	/**
	 * Contains the desired action for the authority (I - insert, U - update, D - delete)
	 */
	private ActivityCode changeType;
}
