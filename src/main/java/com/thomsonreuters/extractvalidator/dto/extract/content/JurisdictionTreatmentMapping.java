package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import com.thomsonreuters.extractvalidator.util.ActivityCode;


/**
 * A class to encapsulate jurisdiction treatment mappings to be sent by the CE REST service. Jurisdiction treatment mappings describe the relationship between a
 * jurisdiction, product, and a tax treatment along with the effective dates.
 *
 * @author Eli Laudi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JurisdictionTreatmentMapping
{
	/**
	 * Numeric hash key associated with the JurisdictionTreatmentMapping.
	 */
	private String key;

	/**
	 * Numeric hash key associated with a product.
	 */
	private String productCategoryKey;

	/**
	 * Numeric hash key associated with a jurisdiction.
	 */
	private String jurisdictionKey;

	/**
	 * Tax Type applicable to the treatment.
	 */
	private String taxType;

	/**
	 * The authority type associated with this mapping.
	 */
	private String authorityType;

	/**
	 * Numeric hash key associated with a treatment.
	 */
	private String treatmentGroupKey;

	/**
	 * Range of dates where this treatment mapping is effective.
	 */
	private EffectiveDate effectiveDate;

	/**
	 * Contains the action for the authority (I - insert, U - update, D - delete).
	 */
	private ActivityCode changeType;
}
