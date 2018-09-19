package com.thomsonreuters.extractvalidator.dto.extract.content;


import java.util.List;

import lombok.Data;


/**
 * AuthorityData Description.
 *
 * @author Matt Godsey
 */
@Data
public final class AuthorityData
{
	private String authorityName;
	private List<TreatmentData> authorityTreatmentData;
}
