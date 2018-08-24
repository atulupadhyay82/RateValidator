package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;


/**
 * A data transfer object representing a company.
 *
 * @author Neal Schultz
 */
@Data
public final class UiCompany
{
	/**
	 * The Company id.
	 */
	private Long companyId;

	/**
	 * The Company UUID.
	 */
	private String companyUuid;

	/**
	 * The Company Name.
	 */
	private String companyName;
}
