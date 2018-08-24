package com.thomsonreuters.extractvalidator.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thomsonreuters.extractvalidator.dto.determination.UiAuthorityDetail;
import com.thomsonreuters.extractvalidator.dto.determination.UiAuthorityList;
import com.thomsonreuters.extractvalidator.dto.determination.UiAuthorityType;
import com.thomsonreuters.extractvalidator.dto.determination.UiAuthorityTypeList;
import com.thomsonreuters.extractvalidator.dto.determination.UiAuthorityTypes;
import com.thomsonreuters.extractvalidator.dto.determination.UiBasicCompany;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompany;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompanyList;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompanyRelatedGroup;
import com.thomsonreuters.extractvalidator.dto.determination.UiCompanyRelatedGroupsHolder;
import com.thomsonreuters.extractvalidator.dto.determination.UiLookup;
import com.thomsonreuters.extractvalidator.dto.determination.UiLookupList;
import com.thomsonreuters.extractvalidator.dto.determination.UiXmlElement;
import com.thomsonreuters.extractvalidator.dto.determination.UiZone;
import com.thomsonreuters.extractvalidator.dto.determination.UiZoneList;


/**
 * Implements helper class for outbound rest service requests to Determination.
 *
 * @author Neal Schultz
 */
@Component(DeterminationRestClientUtil.BEAN_NAME)
public class DeterminationRestClientUtil
{
	/**
	 * Denotes the bean name for this component
	 */
	public static final String BEAN_NAME = "determinationRestClientUtil";

	/**
	 * Rest service client used for outbound rest service calls to Determination
	 */
	private final DeterminationRestClient determinationRestClient;


	/**
	 * Constructs a DeterminationRestClientUtil
	 *
	 * @param determinationRestClient Rest service client used for outbound rest service calls to Determination
	 */
	@Autowired
	public DeterminationRestClientUtil(final DeterminationRestClient determinationRestClient)
	{
		this.determinationRestClient = determinationRestClient;
	}


	/**
	 * Makes rest service call to Determination's findCompanies service.
	 *
	 * @param authorization The authorization info to use for the request.
	 *
	 * @return The result of the rest service call as a map.
	 */
	public Map<String, UiCompany> findCompanies(final String authorization)
	{
		final Map<String, UiCompany> companyMap = new HashMap<>();

		final UiCompanyList uiCompanyList = determinationRestClient.findCompanies(authorization);
		if (uiCompanyList != null && uiCompanyList.getCompanies() != null)
		{
			for (final UiCompany uiCompany : uiCompanyList.getCompanies())
			{
				if (uiCompany != null)
				{
					companyMap.put(uiCompany.getCompanyUuid(), uiCompany);
				}
			}
		}

		return companyMap;
	}


	/**
	 * Makes rest service call to Determination's findCompanyRelatedGroups service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param companyId The company id to pass to the service as a path param.
	 *
	 * @return The result of the rest service call as a map.
	 */
	public Map<String, Collection<UiCompanyRelatedGroup>> findCompanyRelatedGroups(final String authorization, final Long companyId)
	{
		final Map<String, Collection<UiCompanyRelatedGroup>> companyRelatedGroupMap = new HashMap<>();

		final UiCompanyRelatedGroupsHolder uiCompanyRelatedGroupsHolder = determinationRestClient.findCompanyRelatedGroups(authorization, companyId);
		if (uiCompanyRelatedGroupsHolder != null && uiCompanyRelatedGroupsHolder.getProductCrossReferenceGroups() != null)
		{
			for (final UiCompanyRelatedGroup uiCompanyRelatedGroup : uiCompanyRelatedGroupsHolder.getProductCrossReferenceGroups())
			{
				if (uiCompanyRelatedGroup != null)
				{
					final UiBasicCompany uiBasicCompany = uiCompanyRelatedGroup.getGroupMerchant();
					if (uiBasicCompany != null)
					{
						mapCompanyRelatedGroup(companyRelatedGroupMap, uiCompanyRelatedGroup, uiBasicCompany);
					}
				}
			}
		}

		return companyRelatedGroupMap;
	}


	/**
	 * Maps Company Key to UiCompanyRelatedGroup collection.
	 *
	 * @param companyRelatedGroupMap The map to use.
	 * @param uiCompanyRelatedGroup The key to map.
	 * @param uiBasicCompany The value to map.
	 */
	private void mapCompanyRelatedGroup(final Map<String, Collection<UiCompanyRelatedGroup>> companyRelatedGroupMap,
										final UiCompanyRelatedGroup uiCompanyRelatedGroup,
										final UiBasicCompany uiBasicCompany)
	{
		final Collection<UiCompanyRelatedGroup> uiCompanyRelatedGroupCollection = companyRelatedGroupMap.get(uiBasicCompany.getCompanyUuid());
		if (uiCompanyRelatedGroupCollection == null)
		{
			final List<UiCompanyRelatedGroup> newUiCompanyRelatedGroupList = new ArrayList<>();
			newUiCompanyRelatedGroupList.add(uiCompanyRelatedGroup);
			companyRelatedGroupMap.put(uiBasicCompany.getCompanyUuid(), newUiCompanyRelatedGroupList);
		}
		else
		{
			uiCompanyRelatedGroupCollection.add(uiCompanyRelatedGroup);
		}
	}


	/**
	 * Makes rest service call to Determination's findCountries service.
	 *
	 * @param authorization The authorization info to use for the request.
	 *
	 * @return The result of the rest service call as a map.
	 */
	public Map<String, UiZone> findCountries(final String authorization)
	{
		final Map<String, UiZone> zoneMap = new HashMap<>();

		final UiZoneList uiZoneList = determinationRestClient.findCountries(authorization);
		if (uiZoneList != null && uiZoneList.getZones() != null)
		{
			for (final UiZone uiZone : uiZoneList.getZones())
			{
				if (uiZone != null)
				{
					zoneMap.put(uiZone.getName(), uiZone);
				}
			}
		}

		return zoneMap;
	}


	/**
	 * Makes rest service call to Determination's findAllAuthorities service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param companyId The company id to pass to the service as a query parameter.
	 * @param zoneId The zone id to pass to the service as a query parameter.
	 *
	 * @return The result of the rest service call as a map.
	 */
	public Map<String, UiAuthorityDetail> findAllAuthorities(final String authorization, final Long companyId, final Long zoneId)
	{
		final Map<String, UiAuthorityDetail> authorityMap = new HashMap<>();

		final UiAuthorityList uiAuthorityList = determinationRestClient.findAllAuthorities(authorization, companyId, zoneId);
		if (uiAuthorityList != null && uiAuthorityList.getAuthorities() != null)
		{
			for (final UiAuthorityDetail uiAuthorityDetail : uiAuthorityList.getAuthorities())
			{
				if (uiAuthorityDetail != null)
				{
					authorityMap.put(uiAuthorityDetail.getAuthorityUuid(), uiAuthorityDetail);
				}
			}
		}

		return authorityMap;
	}


	/**
	 * Makes rest service call to Determination's findAuthorityTypes service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param companyId The company id to pass to the service as a query parameter.
	 *
	 * @return The result of the rest service call, as a map.
	 */
	public Map<String, UiAuthorityType> findAuthorityTypes(final String authorization, final Long companyId)
	{
		final Map<String, UiAuthorityType> authorityTypeMap = new HashMap<>();

		final UiAuthorityTypeList uiAuthorityTypeList = determinationRestClient.findAuthorityTypes(authorization, companyId);
		if (uiAuthorityTypeList != null && uiAuthorityTypeList.getAuthorityTypeList() != null)
		{
			for (final UiAuthorityTypes uiAuthorityTypes : uiAuthorityTypeList.getAuthorityTypeList())
			{
				if (uiAuthorityTypes != null && uiAuthorityTypes.getAuthorityTypes() != null)
				{
					mapAuthorityType(authorityTypeMap, uiAuthorityTypes);
				}
			}
		}

		return authorityTypeMap;
	}


	/**
	 * Maps List of Authority Types by name.
	 *
	 * @param authorityTypeMap The map to use.
	 * @param uiAuthorityTypes The Authority Type to map.
	 */
	private void mapAuthorityType(final Map<String, UiAuthorityType> authorityTypeMap, final UiAuthorityTypes uiAuthorityTypes)
	{
		for (final UiAuthorityType uiAuthorityType : uiAuthorityTypes.getAuthorityTypes())
		{
			if (uiAuthorityType != null)
			{
				authorityTypeMap.put(uiAuthorityType.getName(), uiAuthorityType);
			}
		}
	}


	/**
	 * Makes rest service call to Determination's findLookup service passing it codeGroup INTL_TAX_TYPE.
	 *
	 * @param authorization The authorization info to use for the request.
	 *
	 * @return The result of the rest service call, as a map.
	 */
	public Map<String, UiLookup> findTaxTypesInternational(final String authorization)
	{
		return findTaxTypes(authorization, "INTL_TAX_TYPE");
	}


	/**
	 * Makes rest service call to Determination's findLookup service passing it codeGroup US_TAX_TYPE.
	 *
	 * @param authorization The authorization info to use for the request.
	 *
	 * @return The result of the rest service call, as a map.
	 */
	public Map<String, UiLookup> findTaxTypesUnitedStates(final String authorization)
	{
		return findTaxTypes(authorization, "US_TAX_TYPE");
	}


	/**
	 * Makes rest service call to Determination's findLookup service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param codeGroup The code group to lookup. Passed to the service as a query parameter.
	 *
	 * @return The result of the rest service call, as a map.
	 */
	private Map<String, UiLookup> findTaxTypes(final String authorization, final String codeGroup)
	{
		final Map<String, UiLookup> taxTypeMap = new HashMap<>();

		final UiLookupList uiLookupList = determinationRestClient.findLookup(authorization, codeGroup);
		if (uiLookupList != null && uiLookupList.getLookups() != null)
		{
			for (final UiLookup uiLookup : uiLookupList.getLookups())
			{
				if (uiLookup != null)
				{
					taxTypeMap.put(uiLookup.getCode(), uiLookup);
				}
			}
		}

		return taxTypeMap;
	}


	/**
	 * Makes rest service call to Determination's findXmlElements service.
	 *
	 * @param authorization The authorization info to use for the request.
	 * @param companyId The company id to pass to the service as a path param.
	 *
	 * @return The result of the rest service call, as a map.
	 */
	public Map<String, UiXmlElement> findXmlElements(final String authorization, final Long companyId)
	{
		final Map<String, UiXmlElement> xmlElementMap = new HashMap<>();

		final UiXmlElement[] xmlElementArray = determinationRestClient.findXmlElements(authorization, companyId);
		if (xmlElementArray != null)
		{
			for (final UiXmlElement xmlElement : xmlElementArray)
			{
				if (xmlElement != null && xmlElement.getElement() != null)
				{
					xmlElementMap.put(xmlElement.getElement(), xmlElement);
				}
			}
		}

		return xmlElementMap;
	}
}
