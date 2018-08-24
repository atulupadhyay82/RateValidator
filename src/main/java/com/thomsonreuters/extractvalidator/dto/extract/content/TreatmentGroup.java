package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.thomsonreuters.extractvalidator.util.ActivityCode;
import com.thomsonreuters.extractvalidator.util.IClientChangeType;


/**
 * Encapsulates the data for a treatment group as it will be communicated by the CE REST service.
 *
 * @author Eli Laudi
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TreatmentGroup implements IClientChangeType
{
	/**
	 * Numeric hash key associated with treatment group.
	 */
	private String key;

	/**
	 * Contains the desired action for the authority (I - insert, U - update, D - delete).
	 */
	private ActivityCode changeType;
}
