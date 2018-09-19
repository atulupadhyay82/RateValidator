package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the scenario and scenario line qualifiers information.
 *
 * @author Tejesh KethamReddy
 */
@Data
public final class UiModelScenarioQualifier
{
	/**
	 * Holds {@link UiEntityAudit} for audit information.
	 */
	private UiEntityAudit entityAudit;

	/**
	 * A boolean to be set to remove the scenario qualifier.
	 */
	private boolean isDelete;

	/**
	 * Holds the qualifier ID.
	 */
	private Long qualifierId;

	/**
	 * Holds the list of qualifiers, also known as End Uses.
	 */
	@Size(min = 1, max = 100, message = "Minimum and Maximum length of Qualifier/EndUse is 1 and 50 respectively.")
	private String qualifier;
}
