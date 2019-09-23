package com.thomsonreuters.extractvalidator.util;


import java.util.LinkedList;
import java.util.List;

import com.thomsonreuters.extractvalidator.dto.extract.content.Address;
import com.thomsonreuters.extractvalidator.dto.extract.content.Authority;
import com.thomsonreuters.extractvalidator.dto.extract.content.AuthorityData;
import com.thomsonreuters.extractvalidator.dto.extract.content.AuthorityTreatmentMapping;
import com.thomsonreuters.extractvalidator.dto.extract.content.ContentExtract;
import com.thomsonreuters.extractvalidator.dto.extract.content.JurisdictionAuthority;
import com.thomsonreuters.extractvalidator.dto.extract.content.JurisdictionData;
import com.thomsonreuters.extractvalidator.dto.extract.content.JurisdictionTreatmentMapping;
import com.thomsonreuters.extractvalidator.dto.extract.content.LocationTreatmentData;
import com.thomsonreuters.extractvalidator.dto.extract.content.Product;
import com.thomsonreuters.extractvalidator.dto.extract.content.ProductAuthorityData;
import com.thomsonreuters.extractvalidator.dto.extract.content.ProductJurisdictionData;
import com.thomsonreuters.extractvalidator.dto.extract.content.Treatment;
import com.thomsonreuters.extractvalidator.dto.extract.content.TreatmentData;
import com.thomsonreuters.extractvalidator.dto.extract.content.TreatmentGroupTreatment;


/**
 * Utility class to help reconnecting the flattened data from the content extract into a form that can be used to comparisons with model scenario.
 *
 * @author Matt Godsey
 */
public final class LocationTreatmentBuilder
{
	/**
	 * Constant to use for sales tax type.
	 */
	private static final String SALES_TAX_TYPE = "SA";


	/**
	 * Private constructor to avoid instatiation.
	 */
	private LocationTreatmentBuilder()
	{
		// Private to avoid instatiation.
	}


	/**
	 * Rebuild the extract data into a relational hierarchy using the jurisdiction key as a filter. We only want the data associated with the given jurisdiction.
	 *
	 * @param address The address to use for filtering on.
	 * @param contentExtract The extract to pull the data from.
	 *
	 * @return A set of data that relates only to the given jurisdiction key.
	 */
	public static LocationTreatmentData buildLocationTreatmentData(final Address address, final ContentExtract contentExtract)
	{
		final LocationTreatmentData locationTreatmentData = new LocationTreatmentData();
		final Long jurisdictionNKey = Long.parseLong(address.getJurisdictionKey());

		locationTreatmentData.setJurisdictionNKey(jurisdictionNKey);
		locationTreatmentData.setProductAuthorityData(new LinkedList<>());
		locationTreatmentData.setProductJurisdictionData(new LinkedList<>());
		locationTreatmentData.setAddress(address);

		// Process by grouping rule, but extract data doesn't update after grouping is changed until staging is run. The extract will show the new grouping
		// selection, but the data will not have been updated. So base decision on JurisdictionAuthorities which relates to grouping by Authority, or Jurisdiction
		// Treatment Mapping which relates to grouping by tax type.
		if (null == contentExtract.getJurisdictionTreatmentMappings())
		{
			buildLocationTreatmentDataByAuthority(locationTreatmentData, contentExtract);
		}
		else if (null == contentExtract.getJurisdictionAuthorities())
		{
			buildLocationTreatmentsByJurisdiction(locationTreatmentData, contentExtract);
		}

		return locationTreatmentData;
	}


	/**
	 * Build the location treatment data based on the Authority grouping rule.
	 *
	 * @param locationTreatmentData The treatment data to populate.
	 * @param contentExtract The content extract to build the data from.
	 */
	private static void buildLocationTreatmentDataByAuthority(final LocationTreatmentData locationTreatmentData, final ContentExtract contentExtract)
	{
		final List<Authority> authorities = new LinkedList<>();

		// Find all the authorities for the specific jurisdiction used for this model scenario run.

	if ( null != contentExtract.getJurisdictionAuthorities() ) {
		for (final JurisdictionAuthority jurisdictionAuthority : contentExtract.getJurisdictionAuthorities()) {
			if (Long.parseLong(jurisdictionAuthority.getJurisdictionKey()) == locationTreatmentData.getJurisdictionNKey()) {
				for (final Authority authority : contentExtract.getAuthorities()) {
					if (authority.getAuthorityKey().equals(jurisdictionAuthority.getAuthorityKey())) {
						authorities.add(authority);
					}
				}
			}
		}
	}

	if ( null != contentExtract.getProducts()) {
		// For each product gather authority data, authority treatment mappings and treatments. Use the set of authorities gathered above.
		for (final Product product : contentExtract.getProducts()) {
			final ProductAuthorityData productAuthorityData = new ProductAuthorityData();

			productAuthorityData.setProduct(product);
			productAuthorityData.setAuthorityData(new LinkedList<>());

			for (final Authority authority : authorities) {
				final AuthorityData authorityData = new AuthorityData();

				authorityData.setAuthorityName(authority.getAuthorityName());
				authorityData.setAuthorityTreatmentData(new LinkedList<>());

				for (final AuthorityTreatmentMapping authorityTreatmentMapping : contentExtract.getAuthorityTreatmentMappings()) {
					if (authorityTreatmentMapping.getAuthorityKey().equals(authority.getAuthorityKey())
							&& authorityTreatmentMapping.getProductKey().equals(product.getProductCategoryKey().toString())) {
						final List<Treatment> treatments = new LinkedList<>();
						final TreatmentData authorityTreatmentData = new TreatmentData();

						authorityTreatmentData.setFromDate(authorityTreatmentMapping.getEffectiveDate().getFrom());
						authorityTreatmentData.setToDate(authorityTreatmentMapping.getEffectiveDate().getTo());
						extractTreatmentsFromAuthority(contentExtract, treatments, authorityTreatmentMapping);
						authorityTreatmentData.setTreatments(treatments);

						authorityData.getAuthorityTreatmentData().add(authorityTreatmentData);

						break;
					}
				}

				productAuthorityData.getAuthorityData().add(authorityData);
			}

			locationTreatmentData.getProductAuthorityData().add(productAuthorityData);
		}
	}
	}


	/**
	 * Build the location treatment data by grouping rules AuthorityType and TaxType. These types do not include authorities in the content extract and marry
	 * everything together using JurisdictionTreatmentMappings.
	 *
	 * @param locationTreatmentData The treatment data to populate.
	 * @param contentExtract The content extract to build the data from.
	 */
	private static void buildLocationTreatmentsByJurisdiction(final LocationTreatmentData locationTreatmentData, final ContentExtract contentExtract)
	{
		final List<JurisdictionTreatmentMapping> jurisdictionTreatmentMappings = new LinkedList<>();

		// Find all the authorities for the specific jurisdiction used for this model scenario run.
		for (final JurisdictionTreatmentMapping jurisdictionTreatmentMapping : contentExtract.getJurisdictionTreatmentMappings())
		{
			if (Long.parseLong(jurisdictionTreatmentMapping.getJurisdictionKey()) == locationTreatmentData.getJurisdictionNKey())
			{
				jurisdictionTreatmentMappings.add(jurisdictionTreatmentMapping);
			}
		}

		// For each product gather authority data, authority treatment mappings and treatments. Use the set of authorities gathered above.
		for (final Product product : contentExtract.getProducts())
		{
			final ProductJurisdictionData productJurisdictionData = new ProductJurisdictionData();

			productJurisdictionData.setProduct(product);
			productJurisdictionData.setJurisdictionData(new LinkedList<>());

			for (final JurisdictionTreatmentMapping jurisdictionTreatmentMapping : jurisdictionTreatmentMappings)
			{
				for (final TreatmentGroupTreatment treatmentGroupTreatment : contentExtract.getTreatmentGroupTreatments())
				{
					if (jurisdictionTreatmentMapping.getProductCategoryKey().equals(product.getProductCategoryKey().toString())
						&& jurisdictionTreatmentMapping.getTreatmentGroupKey().equals(treatmentGroupTreatment.getTreatmentGroupKey())
						&& jurisdictionTreatmentMapping.getTaxType().equals(SALES_TAX_TYPE))
					{
						final JurisdictionData jurisdictionData = new JurisdictionData();

						jurisdictionData.setJurisdictionKey(Long.parseLong(jurisdictionTreatmentMapping.getJurisdictionKey()));
						jurisdictionData.setJurisdictionTreatmentData(new LinkedList<>());

						final List<Treatment> treatments = new LinkedList<>();
						final TreatmentData treatmentData = new TreatmentData();

						treatmentData.setFromDate(jurisdictionTreatmentMapping.getEffectiveDate().getFrom());
						treatmentData.setToDate(jurisdictionTreatmentMapping.getEffectiveDate().getTo());
						extractTreatmentsFromJurisdiction(contentExtract, treatments, treatmentGroupTreatment);
						treatmentData.setTreatments(treatments);

						jurisdictionData.getJurisdictionTreatmentData().add(treatmentData);

						productJurisdictionData.getJurisdictionData().add(jurisdictionData);
						break;
					}
				}
			}

			locationTreatmentData.getProductJurisdictionData().add(productJurisdictionData);
		}
	}


	/**
	 * Collect the treatments from the extract for the given authority treatment mapping.
	 *
	 * @param contentExtract The content extract data set.
	 * @param treatments The list of all treatments.
	 * @param authorityTreatmentMapping The authority treatment mapping to use to gather the treatments by.
	 */
	private static void extractTreatmentsFromAuthority(final ContentExtract contentExtract,
													   final List<Treatment> treatments,
													   final AuthorityTreatmentMapping authorityTreatmentMapping)
	{
		for (final Treatment treatment : contentExtract.getTreatments())
		{
			if (treatment.getTreatmentKey().equals(authorityTreatmentMapping.getTreatmentKey()))
			{
				treatments.add(treatment);
				break;
			}
		}
	}


	/**
	 * Collect the treatments from the extract for the given treatment group treatment mapping which is based on jurisdiction mapping.
	 *
	 * @param contentExtract The content extract data set.
	 * @param treatments The list of all treatments.
	 * @param treatmentGroupTreatment The treatment group treatment mapping to use to gather the treatments by.
	 */
	private static void extractTreatmentsFromJurisdiction(final ContentExtract contentExtract,
														  final List<Treatment> treatments,
														  final TreatmentGroupTreatment treatmentGroupTreatment)
	{
		for (final Treatment treatment : contentExtract.getTreatments())
		{
			if (treatment.getTreatmentKey().equals(treatmentGroupTreatment.getTreatmentKey()))
			{
				treatments.add(treatment);
				break;
			}
		}
	}
}
