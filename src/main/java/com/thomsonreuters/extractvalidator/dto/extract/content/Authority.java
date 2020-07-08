package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;


import com.thomsonreuters.extractvalidator.util.ActivityCode;
import lombok.Data;


/**
 * An authority is an entity over one or more jurisdictions that can dictate a tax scheme.
 *
 * @author Eli Laudi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Authority
{
	/**
	 * Numeric hash key associated with the authority.
	 */
	private String authorityKey;

	/**
	 * Name associated with the authority.
	 */
	private String authorityName;

	/**
	 * Type associated with the authority.
	 */
	private String authorityType;

	/**
	 * The registration mask associated with the authority.
	 */
	private String registrationMask;

	/**
	 * Contains the desired action for the authority (I - insert, U - update, D - delete).
	 */
	private ActivityCode changeType;
}
