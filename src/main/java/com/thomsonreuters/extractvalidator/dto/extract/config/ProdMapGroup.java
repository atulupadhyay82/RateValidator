package com.thomsonreuters.extractvalidator.dto.extract.config;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * ProdMapGroup DTO.
 *
 * @author Eli Laudi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProdMapGroup
{
	/**
	 * the id of this prod map group
	 */
	private Long extractProdMapGroupId;

	/**
	 * the id of the extract associated with thie prod map group
	 */
	private Long extractId;

	/**
	 * the name of the product map group
	 */
	private String name;

	/**
	 * the company key of the product map group
	 */
	private String companyKey;
}
