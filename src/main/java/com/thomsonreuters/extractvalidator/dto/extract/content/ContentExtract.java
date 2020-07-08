package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.thomsonreuters.extractvalidator.util.GroupingRule;
import com.thomsonreuters.extractvalidator.util.LoadMethod;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


/**
 * ContentExtract Description.
 *
 * @author Matt Godsey
 */
@Data
public final class ContentExtract
{
	/**
	 * Date of the extract pull
	 */
	private LocalDateTime extractDate;

	/**
	 * Name of the company associated with the extract
	 */
	private String companyName;

	/**
	 * Name of the extract
	 */
	private String extractName;

	/**
	 * The latest content version this extract contains data for
	 */
	private Long contentVersion;

	/**
	 * The grouping rule assigned in the extract configuration
	 */
	private GroupingRule groupingRule;

	/**
	 * The load method given by the extract client
	 */
	private LoadMethod loadMethod;

	/**
	 * List of all store locations as defined in the extract
	 */
	private List<Location> locations;

	/**
	 * List of all addresses as defined in the extract
	 */
	private List<Address> addresses;

	/**
	 * List of all Products as defined in the extract
	 */
	private List<Product> products;

	/**
	 * List of all Authorities as defined in the extract
	 */
	private List<Authority> authorities;

	/**
	 * List of all Jurisdictions as defined in the extract
	 */
	private List<Jurisdiction> jurisdictions;

	/**
	 * List of all JurisdictionAuthorities as defined in the extract
	 */
	private List<JurisdictionAuthority> jurisdictionAuthorities;

	/**
	 * List of all Treatments as defined in the extract
	 */
	private List<Treatment> treatments;

	/**
	 * List of all TreatmentMappingsByJurisdiction as defined in the extract
	 */
	private List<JurisdictionTreatmentMapping> jurisdictionTreatmentMappings;

	/**
	 * List of all TreatmentMappingsByAuthority as defined in the extract
	 */
	private List<AuthorityTreatmentMapping> authorityTreatmentMappings;

	/**
	 * List of all TreatmentGroup as defined in the extract
	 */
	private List<TreatmentGroup> treatmentGroups;

	/**
	 * List of all TreatmentGroupTreatment as defined in the extract
	 */
	private List<TreatmentGroupTreatment> treatmentGroupTreatments;
}
