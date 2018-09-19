package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import com.thomsonreuters.extractvalidator.util.ActivityCode;


/**
 * An AuthorityTreatmentMapping describes the relationship between an authority, a product,
 * and a tax treatment, including effective dates.
 *
 * @author Eli Laudi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthorityTreatmentMapping
{
	/**
	 * Numeric hash key associated with the AuthorityTreatmentMapping
	 */
	private String key;

	/**
	 * Numeric hash key associated with a product
	 */
	private String productKey;

	/**
	 * Numeric hash key associated with an authority
	 */
	private String authorityKey;

	/**
	 * Tax type applicable to the treatment
	 */
	private String taxType;

	/**
	 * Numeric hash key associated with a treatment
	 */
	private String treatmentKey;

	/**
	 * Range of dates where this treatment mapping is effective
	 */
	private EffectiveDate effectiveDate;

	/**
	 * Contains the action for the authority (I - insert, U - update, D - delete)
	 */
	private ActivityCode changeType;
}