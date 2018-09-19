package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * Holds the information related to entity audit fields.
 *
 * @author PankajJ
 */
@Data
public final class UiEntityAudit
{
	/**
	 * Holds created by for a UI DTO object.
	 */
	private String createdBy;

	/**
	 * Holds the email of the created by user for a UI DTO object.
	 */
	private String createdByEmail;

	/**
	 * Holds the phone number of the created by user for a UI DTO object.
	 */
	private String createdByPhone;

	/**
	 * Holds created date for a UI DTO object.
	 */
	private String createdDate;

	/**
	 * Holds modified by for a UI DTO object.
	 */
	private String modifiedBy;

	/**
	 * Holds the email of the modified by user for a UI DTO object.
	 */
	private String modifiedByEmail;

	/**
	 * Holds the phone number of the modified by user for a UI DTO object.
	 */
	private String modifiedByPhone;

	/**
	 * Holds modified date for a UI DTO object.
	 */
	private String modifiedDate;
}
