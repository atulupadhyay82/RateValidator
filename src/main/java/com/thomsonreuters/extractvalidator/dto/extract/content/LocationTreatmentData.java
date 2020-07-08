package com.thomsonreuters.extractvalidator.dto.extract.content;


import lombok.Data;

import java.util.List;


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
