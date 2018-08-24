package com.thomsonreuters.extractvalidator.dto.extract.config;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Region DTO.
 *
 * @author Eli Laudi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Region
{
	/**
	 * the id of the region
	 */
	private Long extractRegionId;

	/**
	 * the id of the extract this region is associated with
	 */
	private Long extractId;

	/**
	 * the default display name of the region
	 */
	private String displayNameDefault;

	/**
	 * the country this region represents
	 */
	private String country;

	/**
	 * the region states this region is associated with
	 */
	private List<RegionState> regionStates;

	/**
	 * the autority types this region is associated with
	 */
	private List<AuthorityType> authorityTypes;

	/**
	 * the tax types this region is associated with
	 */
	private List<TaxType> taxTypes;

	/**
	 * the disp exceptions this region is associated with
	 */
	private List<DispException> dispExceptions;

	/**
	 * the element values this region is associated with
	 */
	private List<ElementValue> elementValues;


	/**
	 * Constructs a new Region
	 */
	public Region()
	{
		regionStates = new ArrayList<>();
		authorityTypes = new ArrayList<>();
		taxTypes = new ArrayList<>();
		dispExceptions = new ArrayList<>();
		elementValues = new ArrayList<>();
	}
}