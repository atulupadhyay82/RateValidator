package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.thomsonreuters.extractvalidator.util.ActivityCode;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Encapsulates the treatment data information along with associated tiers if applicable as it will be communicated by the CE REST service.
 *
 * @author Eli Laudi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Treatment
{
	/**
	 * Name of the treatment
	 */
	private String displayName;

	/**
	 * Numeric hash key associated with the treatment
	 */
	private String treatmentKey;

	/**
	 * Rate associated with the treatment (Null if treatment has tiers)
	 */
	private BigDecimal rate;

	/**
	 * Fee associated with the treatment (Null if treatment has tiers)
	 */
	private BigDecimal fee;

	/**
	 * Denotes the methodology of sorting tiers
	 */
	private String splitType;

	/**
	 * Denotes the methodology of sorting tiers
	 */
	private String splitAmountType;

	/**
	 * The calculation method for the associated rule
	 */
	private String calculationMethod;

	/**
	 * List of Tier to be persisted
	 */
	private List<Tier> tierList = new ArrayList<>();

	/**
	 * Contains the desired action for the authority (I - insert, U - update, D - delete)
	 */
	private ActivityCode changeType;

	/**
	 * Basic percent associated with the treatment
	 */
	private BigDecimal basisPercent;
}
