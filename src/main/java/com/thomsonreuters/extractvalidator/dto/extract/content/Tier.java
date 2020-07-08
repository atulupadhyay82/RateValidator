package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;



/**
 * Describes treatment tier information for a given treatment as it will be sent by the CE REST service.
 *
 * @author Eli Laudi
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Tier
{
	/**
	 * Numeric hash key associated with the parent treatment.
	 */
	private Long treatmentKey;

	/**
	 * Order of the tier.
	 */
	private Long order;

	/**
	 * Lowest value this tier applies to.
	 */
	private BigDecimal lowValue;

	/**
	 * Highest value this tier applies to.
	 */
	private BigDecimal highValue;

	/**
	 * Rate associated with the Tier.
	 */
	private BigDecimal rate;

	/**
	 * Fee associated with the Tier.
	 */
	private BigDecimal fee;
}
