package com.thomsonreuters.extractvalidator.dto.extract.config;


import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * StoreCompany DTO.
 *
 * @author Eli Laudi
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class StoreCompany
{
	/**
	 * database ID for the store company
	 */
	private Long extractStoreCompanyId;

	/**
	 * database ID for the extract region associated with this store company
	 */
	private Long extractId;

	/**
	 * key referencing the company for the extract
	 */
	private String companyKey;
}
