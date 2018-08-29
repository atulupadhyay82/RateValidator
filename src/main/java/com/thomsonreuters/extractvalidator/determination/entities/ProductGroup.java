package com.thomsonreuters.extractvalidator.determination.entities;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * ProductGroup Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_PRODUCT_GROUPS")
public final class ProductGroup
{
	private Long productGroupId;

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
	 * References the content type for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _contentType;


	private List<ProductCategory> productCategories;


	public ProductGroup()
	{
	}


	@Id
	@Column(name = "PRODUCT_GROUP_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getProductGroupId()
	{
		return productGroupId;
	}


	public void setProductGroupId(final Long productGroupId)
	{
		this.productGroupId = productGroupId;
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


	@Column(name = "DESCRIPTION", length = 200)
	public String getDescription()
	{
		return _description;
	}


	public void setDescription(final String description)
	{
		_description = description;
	}


	@Column(name = "CONTENT_TYPE", length = 50)
	public String getContentType()
	{
		return _contentType;
	}


	public void setContentType(final String contentType)
	{
		_contentType = contentType;
	}


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "PRODUCT_GROUP_ID", updatable = false)
	public List<ProductCategory> getProductCategories()
	{
		return productCategories;
	}


	public void setProductCategories(final List<ProductCategory> productCategories)
	{
		this.productCategories = productCategories;
	}
}
