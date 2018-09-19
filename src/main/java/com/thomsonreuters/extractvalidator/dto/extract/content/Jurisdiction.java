package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import com.thomsonreuters.extractvalidator.util.ActivityCode;


/**
 * Used in the CE REST service to send a jurisdiction along with how it has changed.
 *
 * @author Eli Laudi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Jurisdiction
{
	/**
	 * Numeric hash key associated with a jurisdiction
	 */
	private String jurisdictionKey;

	/**
	 * Contains the desired action for the authority (I - insert, U - update, D - delete)
	 */
	private ActivityCode changeType;
}
