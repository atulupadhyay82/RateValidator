package com.thomsonreuters.extractvalidator.dto.extract.config;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * RegionState DTO.
 *
 * @author Eli Laudi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RegionState
{
	/**
	 * database ID for the region state
	 */
	private Long extractRegionStateId;

	/**
	 * id of the region this region state is associated with
	 */
	private Long extractRegionId;

	/**
	 * the state this region state represents
	 */
	private String state;
}
