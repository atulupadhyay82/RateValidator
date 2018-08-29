package com.thomsonreuters.extractvalidator.determination.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


/**
 * ProductCrossReferenceGroup Description.
 *
 * @author Matt Godsey
 */
public final class ProductCrossReferenceGroup
{
	private Long productCrossReferenceGroupId;

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
	 * References the merchant ID for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _merchantId;

	private List<ProductCrossReference> productMappings;


	/**
	 * Constructs a new {@code ProductCrossReferenceGroup}.
	 */
	public ProductCrossReferenceGroup()
	{
	}


	@Id
	@Column(name = "PRODUCT_CROSS_REF_GROUP_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getProductCrossReferenceGroupId()
	{
		return productCrossReferenceGroupId;
	}


	public void setProductCrossReferenceGroupId(final Long productCrossReferenceGroupId)
	{
		this.productCrossReferenceGroupId = productCrossReferenceGroupId;
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


	@Column(name = "DESCRIPTION", length = 500)
	public String getDescription()
	{
		return _description;
	}


	public void setDescription(final String description)
	{
		_description = description;
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


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "PRODUCT_CROSS_REF_ID", updatable = false)
	public List<ProductCrossReference> getProductMappings()
	{
		return productMappings;
	}


	public void setProductMappings(final List<ProductCrossReference> productMappings)
	{
		this.productMappings = productMappings;
	}
}
