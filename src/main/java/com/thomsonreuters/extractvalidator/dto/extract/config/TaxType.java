package com.thomsonreuters.extractvalidator.dto.extract.config;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * TaxType DTO.
 *
 * @author Eli Laudi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TaxType
{
	/**
	 * database ID for the extract region tax type
	 */
	private Long extractRegionTaxTypeId;

	/**
	 * database ID for the extract region associated with this tax type
	 */
	private Long extractRegionId;

	/**
	 * indicates the tax type for the tax
	 */
	private String taxType;
}
