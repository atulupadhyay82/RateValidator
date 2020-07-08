package com.thomsonreuters.extractvalidator.dto.extract.content;


import lombok.Data;

import java.util.List;


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
