package com.thomsonreuters.extractvalidator.dto.extract.content;


import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * An effective date encapsulates the effective range given by its beginning and end points.
 *
 * @author Eli Laudi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EffectiveDate
{
	/**
	 * Start date of the effective date.
	 */
	private LocalDateTime from;

	/**
	 * End date of the effective date.
	 */
	private LocalDateTime to;


	/**
	 * @param from Start date of the effective date
	 * @param to End date of the effective date
	 */
	public EffectiveDate(final LocalDateTime from, final LocalDateTime to)
	{
		this.from = from;
		this.to = to;
	}
}
