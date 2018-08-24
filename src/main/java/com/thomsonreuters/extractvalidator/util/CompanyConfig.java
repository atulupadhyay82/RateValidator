package com.thomsonreuters.extractvalidator.util;


import java.util.List;

import lombok.Data;

import com.thomsonreuters.extractvalidator.dto.extract.content.Location;


/**
 * CompanyConfig Description.
 *
 * @author Matt Godsey
 */
@Data
public final class CompanyConfig
{
	private String companyName;
	private Long merchantId;
	private List<Location> locations;
	private List<String> establishedJurisdictions;
	//private List<ProductGroup> productGroups;
	//private List<ProductCategories> productCategories;
	//private List<ProductMapGroups> productMapGroups;


	public CompanyConfig(final String companyName)
	{
		this.companyName = companyName;
	}
}
