package com.thomsonreuters.extractvalidator.determination.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * ProductCrossReference Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_PRODUCT_CROSS_REF")
public final class ProductCrossReference
{
	private Long productCrossReferenceId;

	/**
	 * References the product cross reference group ID for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _productCrossRefGroupId;

	/**
	 * References the product category ID for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _productCategoryId;

	/**
	 * References the source product code for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _sourceProductCode;

	/**
	 * References the input recovery type for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _inputRecoveryType;

	/**
	 * References the output recovery type for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _outputRecoveryType;


	/**
	 * Constructs a new {@code ProductCrossReference}.
	 */
	public ProductCrossReference()
	{
	}


	@Id
	@Column(name = "PRODUCT_CROSS_REF_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getProductCrossReferenceId()
	{
		return productCrossReferenceId;
	}


	public void setProductCrossReferenceId(final Long productCrossReferenceId)
	{
		this.productCrossReferenceId = productCrossReferenceId;
	}


	@Column(name = "PRODUCT_CROSS_REF_GROUP_ID", nullable = false, precision = 10, scale = 0)
	public Long getProductCrossRefGroupId()
	{
		return _productCrossRefGroupId;
	}


	public void setProductCrossRefGroupId(final Long productCrossRefGroupId)
	{
		_productCrossRefGroupId = productCrossRefGroupId;
	}


	@Column(name = "PRODUCT_CATEGORY_ID", nullable = false, precision = 10, scale = 0)
	public Long getProductCategoryId()
	{
		return _productCategoryId;
	}


	public void setProductCategoryId(final Long productCategoryId)
	{
		_productCategoryId = productCategoryId;
	}


	@Column(name = "SOURCE_PRODUCT_CODE", nullable = false, length = 100)
	public String getSourceProductCode()
	{
		return _sourceProductCode;
	}


	public void setSourceProductCode(final String sourceProductCode)
	{
		_sourceProductCode = sourceProductCode;
	}


	@Column(name = "INPUT_RECOVERY_TYPE", length = 2)
	public String getInputRecoveryType()
	{
		return _inputRecoveryType;
	}


	public void setInputRecoveryType(final String inputRecoveryType)
	{
		_inputRecoveryType = inputRecoveryType;
	}


	@Column(name = "OUTPUT_RECOVERY_TYPE", length = 2)
	public String getOutputRecoveryType()
	{
		return _outputRecoveryType;
	}


	public void setOutputRecoveryType(final String outputRecoveryType)
	{
		_outputRecoveryType = outputRecoveryType;
	}
}
