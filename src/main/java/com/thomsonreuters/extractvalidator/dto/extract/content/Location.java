package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.thomsonreuters.extractvalidator.util.ActivityCode;


/**
 * A Location entity is used by the CE REST service to communicate a store location along with keys
 * to associated store information.
 *
 * @author Eli Laudi
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Location
{
	/**
	 * Numeric hash key associated with the stores
	 */
	private String locationKey;
	/**
	 * Name of the stores
	 */
	private String name;
	/**
	 * Information on the stores
	 */
	private String notes;
	/**
	 * Numeric hash key of the associated address
	 */
	private Long addressKey;
	/**
	 * Contains the action for the authority (I - insert, U - update, D - delete)
	 */
	private ActivityCode changeType;
}
