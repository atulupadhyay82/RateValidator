package com.thomsonreuters.extractvalidator.dto.extract.content;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import com.thomsonreuters.extractvalidator.util.ActivityCode;


/**
 * Describes a extract product as it will be communicated in the CE REST service.
 *
 * @author Eli Laudi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Product
{
	/**
	 * Name of the product
	 */
	private String name;

	/**
	 * Numeric hash key associated with the product
	 */
	private String productKey;

	/**
	 * The product category name of the associated product category
	 */
	private String productCategory;

	/**
	 * The product category hash key associated with the product
	 */
	private Long productCategoryKey;

	/**
	 * The description from the associated product category
	 */
	private String description;

	/**
	 * Range of dates that the product is effective within
	 */
	private EffectiveDate effectiveDate;

	/**
	 * Contains the action for the authority (I - insert, U - update, D - delete)
	 */
	private ActivityCode changeType;
}
