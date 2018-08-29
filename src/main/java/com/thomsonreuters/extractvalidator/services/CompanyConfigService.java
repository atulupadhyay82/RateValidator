package com.thomsonreuters.extractvalidator.services;


import org.owasp.esapi.ESAPI;
import org.owasp.esapi.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thomsonreuters.extractvalidator.determination.CompanyConfig;
import com.thomsonreuters.extractvalidator.determination.services.DeterminationDataService;
import com.thomsonreuters.extractvalidator.dto.TestRun;
import com.thomsonreuters.extractvalidator.util.ExtractValidatorException;


/**
 * CompanyConfigService Description.
 *
 * @author Matt Godsey
 */
@Service
public final class CompanyConfigService
{
	/**
	 * Logging handle for this class
	 */
	private static final Logger LOGGER = ESAPI.getLogger(CompanyConfigService.class);

	private DeterminationDataService determinationDataService;


	@Autowired
	public CompanyConfigService(final DeterminationDataService determinationDataService)
	{
		this.determinationDataService = determinationDataService;
	}


	public CompanyConfig buildCompanyConfig(final TestRun testRunData) throws ExtractValidatorException
	{
		LOGGER.info(Logger.EVENT_UNSPECIFIED, "Building company config from Determination for company: " + testRunData.getTestCompanyName());

		// Initialize the determination database connection.
		determinationDataService.configureHibernateSession(testRunData);

		// Build the company config.
		final CompanyConfig companyConfig = new CompanyConfig(testRunData.getTestCompanyName());

		companyConfig.setMerchant(determinationDataService.fetchMerchantForTestRun(testRunData));
		companyConfig.setEstablishedAuthorities(determinationDataService.fetchEstablishedAuthoritiesForMerchant(companyConfig.getMerchant()));
		companyConfig.setEstablishedAuthorityTypes(determinationDataService.fetchEstablishedAuthorityTypesForMerchant(companyConfig.getMerchant()));
		companyConfig.setEstablishedJurisdictions(determinationDataService.fetchEstablishedZonesForMerchant(companyConfig.getMerchant()));
		companyConfig.setLocations(determinationDataService.fetchLocationsForMerchant(companyConfig.getMerchant()));
		companyConfig.setUsProductGroup(determinationDataService.fetchUsProductGroupForMerchant(companyConfig.getMerchant()));
		companyConfig.setIntlProductGroup(determinationDataService.fetchIntlProductGroupForMerchant(companyConfig.getMerchant()));
		companyConfig.setProductCrossReferenceGroup(determinationDataService.fetchProductCrossReferenceGroup(companyConfig.getMerchant()));

		return companyConfig;
	}
}
