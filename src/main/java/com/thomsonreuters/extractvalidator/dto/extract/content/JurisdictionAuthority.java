package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import com.thomsonreuters.extractvalidator.util.ActivityCode;
import com.thomsonreuters.extractvalidator.util.IClientChangeType;


/**
 * An extract jurisdiction authority links a jurisdiction with an authority.
 *
 * @author Eli Laudi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JurisdictionAuthority implements IClientChangeType
{
	/**
	 * Numeric hash key associated with a jurisdiction.
	 */
	private String jurisdictionKey;

	/**
	 * Numeric hash key associated with an authority.
	 */
	private String authorityKey;

	/**
	 * Contains the desired action for the authority (I - insert, U - update, D - delete).
	 */
	private ActivityCode changeType;
}
