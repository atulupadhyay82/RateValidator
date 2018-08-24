package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import lombok.Data;


/**
 * A data transfer object representing the parent of company related groups.
 *
 * @author Neal Schultz
 */
@Data
public final class UiCompanyRelatedGroupsHolder
{
	/**
	 * Holds the product cross reference groups of the company.
	 */
	private List<UiCompanyRelatedGroup> productCrossReferenceGroups;
}
