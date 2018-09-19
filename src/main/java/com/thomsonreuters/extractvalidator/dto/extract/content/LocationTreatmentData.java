package com.thomsonreuters.extractvalidator.dto.extract.content;


import java.util.List;

import lombok.Data;


/**
 * ContentExtractLocationData Description.
 *
 * @author Matt Godsey
 */
@Data
public final class LocationTreatmentData
{
	private Long jurisdictionNKey;
	private List<ProductAuthorityData> productAuthorityData;
	private List<ProductJurisdictionData> productJurisdictionData;
	private Address address;
}
