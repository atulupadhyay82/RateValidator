package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * This class holds the properties of Vendor Charge Tax Group.
 *
 * @author Rakesh Kumar
 */
@Data
public class UiVendorChargeTaxGroup
{
	/**
	 * The vendor charged tax group ID.
	 */
	private long id;

	/**
	 * The vendor charged tax group uuid.
	 */
	private String uuid;

	/**
	 * The vendor charged tax group name.
	 */
	private String name;

	/**
	 * The vendor charged tax group description.
	 */
	private String description;

	/**
	 * Holds the basic company information.
	 */
	private UiBasicCompany company;

	/**
	 * The {@link UiEntityAudit}.
	 */
	private UiEntityAudit entityAudit;
}
