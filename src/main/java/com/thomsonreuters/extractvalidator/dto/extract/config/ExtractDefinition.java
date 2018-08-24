package com.thomsonreuters.extractvalidator.dto.extract.config;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import com.thomsonreuters.extractvalidator.util.GroupingRule;


/**
 * ExtractDefinition DTO.
 *
 * @author Eli Laudi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExtractDefinition
{
	/**
	 * database ID for the extract
	 */
	private Long extractId;

	/**
	 * key which references the company the extract is for
	 */
	private String companyKey;

	/**
	 * the name of the company the extract is for
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String companyName;

	/**
	 * name of the extract
	 */
	private String name;

	/**
	 * rule to group by (authority, authority type, tax type)
	 */
	private GroupingRule groupingRule;

	/**
	 * boolean (Y or N) to include locations
	 */
	private Boolean includeLocations;

	/**
	 * boolean (Y or N) to include province
	 */
	private Boolean includeProvince;

	/**
	 * boolean (Y or N) to include district
	 */
	private Boolean includeDistrict;

	/**
	 * boolean (Y or N) to include states
	 */
	private Boolean includeState;

	/**
	 * boolean (Y or N) to include county
	 */
	private Boolean includeCounty;

	/**
	 * boolean (Y or N) to include city
	 */
	private Boolean includeCity;

	/**
	 * boolean (Y or N) to include postal
	 */
	private Boolean includePostal;

	/**
	 * boolean (Y or N) to include postal4
	 */
	private Boolean includePostal4;

	/**
	 * boolean (Y or N) to include ranges
	 */
	private Boolean includeRanges;

	/**
	 * boolean (Y or N) to include stores
	 */
	private Boolean includeStores;

	/**
	 * indicates preference for rate of tax
	 */
	private String ratePreference;

	/**
	 * the regions the extract is associated with
	 */
	private List<Region> regions;

	/**
	 * the store companies the extract is associated with
	 */
	private List<StoreCompany> storeCompanies;

	/**
	 * the product mapping groups the extract is associated with
	 */
	private List<ProdMapGroup> prodMapGroups;

	/**
	 * date at which the extract begins
	 */
	private LocalDateTime contentBeginDate;


	/**
	 * Constructs a new ExtractDefinition
	 */
	public ExtractDefinition()
	{
		regions = new ArrayList<>();
		storeCompanies = new ArrayList<>();
		prodMapGroups = new ArrayList<>();
	}
}
