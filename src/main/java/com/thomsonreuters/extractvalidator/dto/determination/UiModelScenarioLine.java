package com.thomsonreuters.extractvalidator.dto.determination;


import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import lombok.Data;


/**
 * Holds the model scenario line detail information.
 *
 * @author Jositha Parampil
 */
@Data
public final class UiModelScenarioLine
{
	/**
	 * Holds the model scenario line commodity code.
	 */
	@Size(max = 50, message = "Maximum length of commodity code allowed is 50.")
	private String commodityCode;

	/**
	 * Holds the model scenario line gross amount.
	 */
	private BigDecimal grossAmount;

	/**
	 * Holds the model scenario line description.
	 */
	@Size(max = 200, message = "Maximum length of line description allowed is 200.")
	private String lineDescription;

	/**
	 * Holds the model scenario line number.
	 */
	private Long lineNumber;

	/**
	 * Holds the model scenario line product code.
	 */
	@Size(max = 100, message = "Maximum length of product code allowed is 100.")
	private String productCode;

	/**
	 * Holds the model scenario line quantity.
	 */
	private BigDecimal quantity;

	/**
	 * Holds the model scenario line ID.
	 */
	private Long scenarioLineId;

	/**
	 * Holds the model scenario line tax amount.
	 */
	private BigDecimal taxAmount;

	/**
	 * Holds the model scenario line total amount.
	 */
	private BigDecimal total;

	/**
	 * Holds the model scenario part number value.
	 */
	@Size(max = 20, message = "Maximum length of part number allowed is 20.")
	private String partNumber;

	/**
	 * Holds the model scenario unit of quantity  code value.
	 */
	@Size(max = 25, message = "Maximum length of quantity unit of code allowed is 25.")
	private String quantityUnitOfCode;

	/**
	 * A boolean to be set to remove the scenario line.
	 */
	private boolean isDelete;

	/**
	 * Holds the list of scenario line detail information of the model scenario line.
	 */
	@Valid
	private UiModelScenarioLineDetail scenarioLinesDetails;

	/**
	 * Holds {@link UiEntityAudit} for audit information.
	 */
	private UiEntityAudit entityAudit;
}
