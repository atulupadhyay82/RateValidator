package com.thomsonreuters.extractvalidator.dto.extract.content;


import java.util.List;

import lombok.Data;


/**
 * ProductJurisdictionData Description.
 *
 * @author Matt Godsey
 */
@Data
public final class ProductJurisdictionData
{
	private Product product;
	private List<JurisdictionData> jurisdictionData;
}
