package com.thomsonreuters.extractvalidator.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.util.CompanyConfig;
import com.thomsonreuters.extractvalidator.util.DeterminationRestClient;


/**
 * CompanyConfigService Description.
 *
 * @author Matt Godsey
 */
@Service
public final class CompanyConfigService
{
	private DeterminationRestClient determinationRestClient;


	@Autowired
	public CompanyConfigService(final DeterminationRestClient determinationRestClient)
	{
		this.determinationRestClient = determinationRestClient;
	}


	public CompanyConfig findCompanyConfig(final TestRun testRunData)
	{
		final CompanyConfig companyConfig = new CompanyConfig(testRunData.getTestCompanyName());

		populateLocations(companyConfig);



		return companyConfig;
	}


	private void populateCompanyData(final CompanyConfig companyConfig)
	{

	}


	private void populateLocations(final CompanyConfig companyConfig)
	{

	}


	private void populateEstablishments(final CompanyConfig companyConfig)
	{

	}


	private void populateProductCategories(final CompanyConfig companyConfig)
	{

	}


	private void populateReferenceLists(final CompanyConfig companyConfig)
	{

	}


	private void populateProductMappingGroups(final CompanyConfig companyConfig)
	{

	}
}
