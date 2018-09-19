package com.thomsonreuters.extractvalidator.dto.extract.content;


import java.util.List;

import lombok.Data;


/**
 * ProductData Description.
 *
 * @author Matt Godsey
 */
@Data
public final class ProductAuthorityData
{
	private Product product;
	private List<AuthorityData> authorityData;
}
