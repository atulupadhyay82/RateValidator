package com.thomsonreuters.extractvalidator.dto.determination;


import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;

import lombok.Data;


/**
 * Holds the model scenario calcs information specific on line level.
 *
 * @author KarthickMoorthy
 */
@Data
public final class UiModelScenarioLineCalcs extends UiModelScenarioBasicCalcs
{
	/**
	 * Holds the can be allocated flag for the model scenario line.
	 */
	private Boolean canBeAllocated;

	/**
	 * Holds the discount amount for the model scenario line.
	 */
	private BigDecimal discountAmount;

	/**
	 * Holds the input recovery amount for the model scenario line.
	 */
	private BigDecimal inputRecoveryAmount;

	/**
	 * Holds the input recovery type for the model scenario.
	 */
	private String inputRecoveryType;

	/**
	 * Holds the input recovery percent for the model scenario line.
	 */
	@DecimalMax(value = "100", message = "The percentage must not exceed 100%.")
	private BigDecimal inputRecoveryPercent;

	/**
	 * Holds the item value type for the model scenario line.
	 */
	private BigDecimal itemValue;

	/**
	 * Holds the manufacturing flag for the model scenario line.
	 */
	private Boolean manufacturing;
}
