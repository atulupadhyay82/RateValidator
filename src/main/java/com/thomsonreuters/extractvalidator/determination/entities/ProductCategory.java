package com.thomsonreuters.extractvalidator.determination.entities;


import javax.persistence.Column;
import javax.persistence.Id;


/**
 * ProductCategory Description.
 *
 * @author Matt Godsey
 */
public final class ProductCategory
{
	private Long productCategoryId;

	/**
	 * References the product group ID for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _productGroupId;

	/**
	 * References the name for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _name;

	/**
	 * References the description for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _description;

	/**
	 * References the nature of transaction code for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _notc;

	/**
	 * References the merchant ID for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _merchantId;

	/**
	 * References the product code for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _prodcode;


	/**
	 * Constructs a new {@code ProductCategory}.
	 */
	public ProductCategory()
	{
	}


	@Id
	@Column(name = "PRODUCT_CATEGORY_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getProductCategoryId()
	{
		return productCategoryId;
	}


	public void setProductCategoryId(final Long productCategoryId)
	{
		this.productCategoryId = productCategoryId;
	}


	@Column(name = "PRODUCT_GROUP_ID", nullable = false, precision = 10, scale = 0)
	public Long getProductGroupId()
	{
		return _productGroupId;
	}


	public void setProductGroupId(final Long productGroupId)
	{
		_productGroupId = productGroupId;
	}


	@Column(name = "NAME", nullable = false, length = 100)
	public String getName()
	{
		return _name;
	}


	public void setName(final String name)
	{
		_name = name;
	}


	@Column(name = "DESCRIPTION", length = 250)
	public String getDescription()
	{
		return _description;
	}


	public void setDescription(final String description)
	{
		_description = description;
	}


	@Column(name = "NOTC", length = 20)
	public String getNotc()
	{
		return _notc;
	}


	public void setNotc(final String notc)
	{
		_notc = notc;
	}


	@Column(name = "MERCHANT_ID", nullable = false, precision = 10, scale = 0)
	public Long getMerchantId()
	{
		return _merchantId;
	}


	public void setMerchantId(final Long merchantId)
	{
		_merchantId = merchantId;
	}


	@Column(name = "PRODCODE", length = 50)
	public String getProdcode()
	{
		return _prodcode;
	}


	public void setProdcode(final String prodcode)
	{
		_prodcode = prodcode;
	}
}
