package com.thomsonreuters.extractvalidator.determination;


import java.util.List;

import lombok.Data;

import com.thomsonreuters.extractvalidator.determination.entities.Authority;
import com.thomsonreuters.extractvalidator.determination.entities.AuthorityType;
import com.thomsonreuters.extractvalidator.determination.entities.Location;
import com.thomsonreuters.extractvalidator.determination.entities.Merchant;
import com.thomsonreuters.extractvalidator.determination.entities.ProductCrossReferenceGroup;
import com.thomsonreuters.extractvalidator.determination.entities.ProductGroup;
import com.thomsonreuters.extractvalidator.determination.entities.Zone;


/**
 * CompanyConfig Description.
 *
 * @author Matt Godsey
 */
@Data
public final class CompanyConfig
{
	private String companyName;
	private Merchant merchant;
	private List<Location> locations;
	private List<Zone> establishedJurisdictions;
	private List<Authority> establishedAuthorities;
	private List<AuthorityType> establishedAuthorityTypes;
	private ProductGroup usProductGroup;
	private ProductGroup intlProductGroup;
	private ProductCrossReferenceGroup productCrossReferenceGroup;
	private List<Authority> allAuthorities;


	public CompanyConfig(final String companyName)
	{
		this.companyName = companyName;
	}
}
