package com.thomsonreuters.extractvalidator.dto.extract.content;


import java.util.List;

import lombok.Data;


/**
 * JurisdictionData Description.
 *
 * @author Matt Godsey
 */
@Data
public final class JurisdictionData
{
	private long jurisdictionKey;
	private List<TreatmentData> jurisdictionTreatmentData;
}
