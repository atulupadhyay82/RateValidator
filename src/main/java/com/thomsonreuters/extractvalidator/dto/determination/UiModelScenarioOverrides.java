package com.thomsonreuters.extractvalidator.dto.determination;


import javax.validation.Valid;

import lombok.Data;


/**
 * Holds the model scenario overrides information.
 *
 * @author Bala Yaddanapudi
 */
@Data
public final class UiModelScenarioOverrides
{
	/**
	 * Refers to the 'noTaxAll' attribute that applies to all the authority types.
	 */
	private Boolean noTaxAll;

	/**
	 * Holds the model scenario overrides information for authority type 'city'
	 */
	@Valid
	private UiModelScenarioOverrideDetail cityOverride;

	/**
	 * Holds the model scenario overrides information for authority type 'country'
	 */
	@Valid
	private UiModelScenarioOverrideDetail countryOverride;

	/**
	 * Holds the model scenario overrides information for authority type 'county'
	 */
	@Valid
	private UiModelScenarioOverrideDetail countyOverride;

	/**
	 * Holds the model scenario overrides information for authority type 'district'
	 */
	@Valid
	private UiModelScenarioOverrideDetail districtOverride;

	/**
	 * Holds the model scenario overrides information for authority type 'geo code'
	 */
	@Valid
	private UiModelScenarioOverrideDetail geoCodeOverride;

	/**
	 * Holds the model scenario overrides information for authority type 'postal code'
	 */
	@Valid
	private UiModelScenarioOverrideDetail postalCodeOverride;

	/**
	 * Holds the model scenario overrides information for authority type 'province'
	 */
	@Valid
	private UiModelScenarioOverrideDetail provinceOverride;

	/**
	 * Holds the model scenario overrides information for authority type 'state'
	 */
	@Valid
	private UiModelScenarioOverrideDetail stateOverride;
}
