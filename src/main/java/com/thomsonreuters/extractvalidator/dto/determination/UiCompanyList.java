package com.thomsonreuters.extractvalidator.dto.determination;


import lombok.Data;

import java.util.Collection;


/**
 * A data transfer object representing a company list.
 *
 * @author Neal Schultz
 */
@Data
public final class UiCompanyList
{
	/**
	 * The Company List
	 */
	private Collection<UiCompany> companies;
}
