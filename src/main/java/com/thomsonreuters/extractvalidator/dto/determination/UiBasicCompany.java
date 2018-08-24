package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * A data transfer object representing the most basic portion of a company.
 *
 * @author Neal Schultz
 */
@Data
public final class UiBasicCompany
{
	/**
	 * Holds the company Name.
	 */
	private String companyName;

	/**
	 * Holds the Company UUID.
	 */
	private String companyUuid;
}