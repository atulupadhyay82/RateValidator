package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;

/**
 * A data transfer object representing company related groups.
 *
 * @author Neal Schultz
 */
@Data
public final class UiCompanyRelatedGroup
{
	/**
	 * Holds the group Name.
	 */
	private String groupName;

	/**
	 * DTO to hold the ID and Name of the  for the Group Merchant Related to the Company.
	 */
	private UiBasicCompany groupMerchant;
}
