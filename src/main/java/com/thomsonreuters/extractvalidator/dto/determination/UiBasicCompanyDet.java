package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * This POJO holds the ID and the name of a data transfer object representing the most basic portion of a company.
 *
 * @author Jositha Parampil
 */
@Data
public class UiBasicCompanyDet
{
	/**
	 * Indicates if the company is active;
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean active;

	/**
	 * Holds the company ID.
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long companyId;

	/**
	 * Holds the company Name.
	 */
	@NotNull(message = "Company Name is Required.")
	@Size(min = 1, max = 100, message = "Minimum and Maximum length of CompanyName is 1 and 100 respectively.")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String companyName;

	/**
	 * Indicates if the company type;
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String type;

	/**
	 * Holds the Company UUID.
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String companyUuid;

	/**
	 * Indicates if the user has Access to the company;
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean hasAccess;
}
