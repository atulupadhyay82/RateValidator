package com.thomsonreuters.extractvalidator.dto.determination;


import java.util.List;

import javax.validation.Valid;


/**
 * This POJO is the a data transfer object representing a company with Permission.
 *
 * @author Jositha Parampil
 */
public class UiAuthorizedCompanyDetails extends UiBasicCompanyDet
{
	/**
	 * DTO {@link UiCompanyRelatedGroup } to hold the properties for the customer group assigned for the company.
	 */
	private UiCompanyRelatedGroup _customerGroup;

	/**
	 * DTO {@link UiTransportationType } to hold the default transportation type for the company.
	 */
	@Valid
	private UiTransportationType defaultTransportationType;

	/**
	 * DTO {@link UiCompanyRelatedGroup} to hold the properties for the transeditor group assigned for the company.
	 */
	private UiCompanyRelatedGroup transEditorGroup;

	/**
	 * Holds the external ID for the company.
	 */
	private String externalId;

	/**
	 * Holds the info if the logged in user has edit permission for this company.
	 */
	private Boolean _hasEditPermission;

	/**
	 * DTO {@link UiBasicCompany } to hold the ID and Name of the INTL content data provider for the company.
	 */
	private UiBasicCompany _intlContentDataProvider;

	/**
	 * DTO {@link UiBasicCompany } to hold the ID and Name of the  INTL custom data provider for the company.
	 */
	private UiBasicCompany _intlCustomDataProvider;

	/**
	 * DTO {@link UiBasicCompany } to hold the ID and Name of the INTL established merchant.
	 */
	private UiBasicCompany _intlEstablishedMerchant;

	/**
	 * Holds the basic parent company Info {@link UiBasicCompany } for the company.
	 */
	private UiBasicCompany _parentCompany;

	/**
	 * DTO {@link UiCompanyRelatedGroup } to hold the properties for the allocation group assigned for the company.
	 */
	private UiCompanyRelatedGroup _allocationGroup;

	/**
	 * DTO {@link UiOperatingLicenseOptions } to hold the operating license options for the company.
	 */
	@Valid
	private UiOperatingLicenseOptions operatingLicenseOptions;

	/**
	 * DTO {@link UiCompanyRelatedGroup } to hold the properties for the product cross reference group assigned for the company.
	 */
	private UiCompanyRelatedGroup _productCrossReferenceGroup;

	/**
	 * DTO {@link UiCompanyRelatedGroup } to hold the properties for the product qualifier group assigned for the company.
	 */
	private UiCompanyRelatedGroup _productQualifierGroup;

	/**
	 * DTO {@link UiCompanyRelatedGroup } to hold the the registration group Info for the company.
	 */
	private UiCompanyRelatedGroup _registrationGroup;

	/**
	 * DTO {@link UiCompanyRelatedGroup } to hold the properties for the tax code qualifier group assigned for the company.
	 */
	private UiCompanyRelatedGroup _taxCodeQualifierGroup;

	/**
	 * DTO {@link UiCompanyRelatedGroup } to hold the properties for the vendor group assigned for the company.
	 */
	private UiCompanyRelatedGroup _vendorGroup;

	/**
	 * DTO {@link UiBasicCompany } to hold the ID and name of the  for the US content data provider for the company.
	 */
	private UiBasicCompany _usContentDataProvider;

	/**
	 * DTO {@link UiBasicCompany } to hold the ID and name of the  for the US custom data provider for the company.
	 */
	private UiBasicCompany _usCustomDataProvider;

	/**
	 * DTO {@link UiBasicCompany } to hold the ID and name of the  for the US established merchant.
	 */
	private UiBasicCompany _usEstablishedMerchant;

	/**
	 * Holds the info if the company is VCT enabled.
	 */
	private boolean _vctEnabled;

	/**
	 * Holds the info for excluded tax types.
	 */
	private List<Long> _excludedTaxTypes;

	/**
	 * Holds the vendor charged tax group.
	 */
	private UiVendorChargeTaxGroup _vctGroup;


	/**
	 * Retrieves the customer group info for a given company.
	 *
	 * @return UiCompanyRelatedGroup The group-identifier and the basic information of the company that owns/manages
	 * the given customer group.
	 */
	public UiCompanyRelatedGroup getCustomerGroup()
	{
		return _customerGroup;
	}


	/**
	 * Sets the customer group info for a given company.
	 *
	 * @param customerGroup The customer group info for the company.
	 */
	public void setCustomerGroup(final UiCompanyRelatedGroup customerGroup)
	{
		_customerGroup = customerGroup;
	}


	/**
	 * Retrieves the default transportation type for the company.
	 *
	 * @return {@link UiTransportationType} The default transportation type for the company.
	 */
	public UiTransportationType getDefaultTransportationType()
	{
		return defaultTransportationType;
	}


	/**
	 * Sets the default transportation type for the company.
	 *
	 * @param defaultTransportationType The default transportation type for the company.
	 */
	public void setDefaultTransportationType(final UiTransportationType defaultTransportationType)
	{
		this.defaultTransportationType = defaultTransportationType;
	}


	/**
	 * Retrieves the transeditor group.
	 *
	 * @return {@link UiCompanyRelatedGroup} The group-identifier and the basic information of the company that owns/manages
	 * the given transeditor group.
	 */
	public UiCompanyRelatedGroup getTransEditorGroup()
	{
		return transEditorGroup;
	}


	/**
	 * Sets the transeditor group.
	 *
	 * @param transEditorGroup The transeditor group for the company.
	 */
	public void setTransEditorGroup(final UiCompanyRelatedGroup transEditorGroup)
	{
		this.transEditorGroup = transEditorGroup;
	}


	/**
	 * Retrieves the external ID of the company.
	 *
	 * @return The external ID of the company.
	 */
	public String getExternalId()
	{
		return externalId;
	}


	/**
	 * Sets the external ID of the company.
	 *
	 * @param externalId The external ID of the company.
	 */
	public void setExternalId(final String externalId)
	{
		this.externalId = externalId;
	}


	/**
	 * Retrieves if the logged in user has edit permission for this company.
	 *
	 * @return boolean true if the logged in user has edit permission for this company.
	 */
	public Boolean getHasEditPermission()
	{
		return _hasEditPermission;
	}


	/**
	 * Sets true if the logged in user has edit permission for this company.
	 *
	 * @param hasEditPermission Holds the info if the logged in user has edit permission for this company.
	 */
	public void setHasEditPermission(final Boolean hasEditPermission)
	{
		_hasEditPermission = hasEditPermission;
	}


	/**
	 * Retrieves the INTL content data provider info for a given company.
	 *
	 * @return UiBasicCompany with Company ID and Name of the INTL content data provider populated.
	 */
	public UiBasicCompany getIntlContentDataProvider()
	{
		return _intlContentDataProvider;
	}


	/**
	 * Sets the INTL content data provider info for a given company.
	 *
	 * @param intlContentDataProvider The INTL content data provider info for the company.
	 */
	public void setIntlContentDataProvider(final UiBasicCompany intlContentDataProvider)
	{
		_intlContentDataProvider = intlContentDataProvider;
	}


	/**
	 * Retrieves the INTL custom data provider info for a given company.
	 *
	 * @return UiBasicCompany with company ID and Name of the INTL custom data provider populated.
	 */
	public UiBasicCompany getIntlCustomDataProvider()
	{
		return _intlCustomDataProvider;
	}


	/**
	 * Sets the INTL custom data provider info for a given company.
	 *
	 * @param intlCustomDataProvider The INTL custom data provider info for the company.
	 */
	public void setIntlCustomDataProvider(final UiBasicCompany intlCustomDataProvider)
	{
		_intlCustomDataProvider = intlCustomDataProvider;
	}


	/**
	 * Retrieves the INTL established merchant info for a given company.
	 *
	 * @return UiBasicCompany with company ID and Name of the INTL established merchant populated.
	 */
	public UiBasicCompany getIntlEstablishedMerchant()
	{
		return _intlEstablishedMerchant;
	}


	/**
	 * Sets the INTL established merchant info for a given company.
	 *
	 * @param intlEstablishedMerchant The INTL established merchant info for the company.
	 */
	public void setIntlEstablishedMerchant(final UiBasicCompany intlEstablishedMerchant)
	{
		_intlEstablishedMerchant = intlEstablishedMerchant;
	}


	/**
	 * Retrieves the parent company info for a given company.
	 *
	 * @return UiBasicCompany with company ID and Name populated.
	 */
	public UiBasicCompany getParentCompany()
	{
		return _parentCompany;
	}


	/**
	 * Sets the parent company info for a given company.
	 *
	 * @param parentCompany The basic parent company info for the company.
	 */
	public void setParentCompany(final UiBasicCompany parentCompany)
	{
		_parentCompany = parentCompany;
	}


	/**
	 * Retrieves the allocation group info for a given company.
	 *
	 * @return UiCompanyRelatedGroup The group-identifier and the basic information of the company that owns/manages
	 * the given allocation group.
	 */
	public UiCompanyRelatedGroup getAllocationGroup()
	{
		return _allocationGroup;
	}


	/**
	 * Sets the allocation group info for a given company.
	 *
	 * @param allocationGroup The allocation group info for the company.
	 */
	public void setAllocationGroup(final UiCompanyRelatedGroup allocationGroup)
	{
		_allocationGroup = allocationGroup;
	}


	/**
	 * Retrieves the operating license options for the company.
	 *
	 * @return The operating license options for the company.
	 */
	public UiOperatingLicenseOptions getOperatingLicenseOptions()
	{
		return operatingLicenseOptions;
	}


	/**
	 * Sets the operating license options for the company.
	 *
	 * @param operatingLicenseOptions The operating license options for the company.
	 */
	public void setOperatingLicenseOptions(final UiOperatingLicenseOptions operatingLicenseOptions)
	{
		this.operatingLicenseOptions = operatingLicenseOptions;
	}


	/**
	 * Retrieves the product cross reference group info for a given company.
	 *
	 * @return UiCompanyRelatedGroup The group-identifier and the basic information of the company that owns/manages
	 * the given product cross reference group.
	 */
	public UiCompanyRelatedGroup getProductCrossReferenceGroup()
	{
		return _productCrossReferenceGroup;
	}


	/**
	 * Sets the product cross reference group info for a given company.
	 *
	 * @param productCrossReferenceGroup The product cross reference group info for the company.
	 */
	public void setProductCrossReferenceGroup(final UiCompanyRelatedGroup productCrossReferenceGroup)
	{
		_productCrossReferenceGroup = productCrossReferenceGroup;
	}


	/**
	 * Retrieves the product qualifier group info for a given company.
	 *
	 * @return UiCompanyRelatedGroup The group-identifier and the basic information of the company that owns/manages the
	 * given product qualifier group.
	 */
	public UiCompanyRelatedGroup getProductQualifierGroup()
	{
		return _productQualifierGroup;
	}


	/**
	 * Sets the product qualifier group info for a given company.
	 *
	 * @param productQualifierGroup The product qualifier group info for the company.
	 */
	public void setProductQualifierGroup(final UiCompanyRelatedGroup productQualifierGroup)
	{
		_productQualifierGroup = productQualifierGroup;
	}


	/**
	 * Retrieves the registration group info for a given company.
	 *
	 * @return UiCompanyRelatedGroup The group-identifier and the basic information of the company that owns/manages
	 * the given registration group.
	 */
	public UiCompanyRelatedGroup getRegistrationGroup()
	{
		return _registrationGroup;
	}


	/**
	 * Sets the registration group info for a given company.
	 *
	 * @param registrationGroup The registration group info for the company.
	 */
	public void setRegistrationGroup(final UiCompanyRelatedGroup registrationGroup)
	{
		_registrationGroup = registrationGroup;
	}


	/**
	 * Retrieves the tax code qualifier group info for a given company.
	 *
	 * @return UiCompanyRelatedGroup The group-identifier and the basic information of the company that owns/manages
	 * the given tax code qualifier group.
	 */
	public UiCompanyRelatedGroup getTaxCodeQualifierGroup()
	{
		return _taxCodeQualifierGroup;
	}


	/**
	 * Sets the tax code qualifier group info for a given company.
	 *
	 * @param taxCodeQualifierGroup The basic tax code qualifier group info for the company.
	 */
	public void setTaxCodeQualifierGroup(final UiCompanyRelatedGroup taxCodeQualifierGroup)
	{
		_taxCodeQualifierGroup = taxCodeQualifierGroup;
	}


	/**
	 * Returns the vendor group info for a given company.
	 *
	 * @return The vendor group info for the company.
	 */
	public UiCompanyRelatedGroup getVendorGroup()
	{
		return _vendorGroup;
	}


	/**
	 * Sets the vendor group info for the given company.
	 *
	 * @param vendorGroup The vendor group info for the company.
	 */
	public void setVendorGroup(final UiCompanyRelatedGroup vendorGroup)
	{
		_vendorGroup = vendorGroup;
	}


	/**
	 * Retrieves the US content data provider info for a given company.
	 *
	 * @return UiBasicCompany with company ID and name of the US content data provider populated.
	 */
	public UiBasicCompany getUsContentDataProvider()
	{
		return _usContentDataProvider;
	}


	/**
	 * Sets the US content data provider info for a given company.
	 *
	 * @param usContentDataProvider The US content data provider info for the company.
	 */
	public void setUsContentDataProvider(final UiBasicCompany usContentDataProvider)
	{
		_usContentDataProvider = usContentDataProvider;
	}


	/**
	 * Retrieves the US custom data provider info for a given company.
	 *
	 * @return UiBasicCompany with company ID and Name of the US custom data provider populated.
	 */
	public UiBasicCompany getUsCustomDataProvider()
	{
		return _usCustomDataProvider;
	}


	/**
	 * Sets the US custom data provider info for a given company.
	 *
	 * @param usCustomDataProvider The US custom data provider info for the company.
	 */
	public void setUsCustomDataProvider(final UiBasicCompany usCustomDataProvider)
	{
		_usCustomDataProvider = usCustomDataProvider;
	}


	/**
	 * Retrieves US established merchant info for a given company.
	 *
	 * @return UiBasicCompany with company ID and Name of the US established merchant populated.
	 */
	public UiBasicCompany getUsEstablishedMerchant()
	{
		return _usEstablishedMerchant;
	}


	/**
	 * Sets the US established merchant info for a given company.
	 *
	 * @param usEstablishedMerchant The US established merchant info for the company.
	 */
	public void setUsEstablishedMerchant(final UiBasicCompany usEstablishedMerchant)
	{
		_usEstablishedMerchant = usEstablishedMerchant;
	}


	/**
	 * Retrieves if the company is VCT enabled.
	 *
	 * @return boolean true if the company is VCT enabled.
	 */
	public boolean isVctEnabled()
	{
		return _vctEnabled;
	}


	/**
	 * Sets true if the company is VCT enabled
	 *
	 * @param vctEnabled Holds the info if the company is VCT enabled.
	 */
	public void setVctEnabled(final boolean vctEnabled)
	{
		_vctEnabled = vctEnabled;
	}


	/**
	 * Retrieves the vct group.
	 *
	 * @return The vct group.
	 */
	public UiVendorChargeTaxGroup getVctGroup()
	{
		return _vctGroup;
	}


	/**
	 * Sets the vct group.
	 *
	 * @param vctGroup The vct group.
	 */
	public void setVctGroup(final UiVendorChargeTaxGroup vctGroup)
	{
		_vctGroup = vctGroup;
	}


	/**
	 * Returns excluded tax types.
	 *
	 * @return The list of excluded tax types.
	 */
	public List<Long> getExcludedTaxTypes()
	{
		return _excludedTaxTypes;
	}


	/**
	 * Sets the excluded tax types.
	 *
	 * @param excludedTaxTypes The excluded tax types.
	 */
	public void setExcludedTaxTypes(final List<Long> excludedTaxTypes)
	{
		_excludedTaxTypes = excludedTaxTypes;
	}
}
