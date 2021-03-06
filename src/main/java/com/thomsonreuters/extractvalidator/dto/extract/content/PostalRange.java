package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * A data entity class encapsulating a postal range defined by its +4 beginning and end points.
 *
 * @author Eli Laudi
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostalRange
{
	/**
	 * Start of the Zip Code range.
	 */
	private String begin;

	/**
	 * End of the Zip Code range.
	 */
	private String end;
}
