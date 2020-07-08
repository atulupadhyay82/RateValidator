package com.thomsonreuters.extractvalidator.dto.extract.content;


import lombok.Data;

import java.util.List;




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
