package com.thomsonreuters.extractvalidator.determination.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Merchant Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_MERCHANTS")
public final class Merchant
{
	private Long merchantId;

	/**
	 * References the ID of the company providing custom International (INTL) product exceptions.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long intlCustomDataProviderId;

	/**
	 * References the ID of the data provider for International (INTL) established authority configuration.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long intlEstablishedMerchantId;

	/**
	 * References the ID of the International (INTL) product group associated with the company.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long intlProductGroupId;

	/**
	 * References the ID of the product cross-reference group associated with the company.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long productCrossReferenceGroupId;

	/**
	 * References the ID of the product qualifier group owner associated with the company.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long productQualifierGroupOwnerId;

	/**
	 * References the ID of the company providing custom US product exceptions.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long usCustomDataProviderId;

	/**
	 * References the ID of the data provider for US established authority configuration.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long usEstablishedMerchantId;
	/**
	 * References the ID of the US product group associated with the company.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long usProductGroupId;


	/**
	 * References unique identifier.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String externalToken;

	/**
	 * References the name of the company.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String name;

	/**
	 * References the name of the product qualifier group associated with the company.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String productQualifierGroupName;

	/**
	 * Constructs a new {@code Merchant}.
	 */
	public Merchant()
	{
	}


	@Id
	@Column(name = "MERCHANT_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getMerchantId()
	{
		return merchantId;
	}


	public void setMerchantId(final Long merchantId)
	{
		this.merchantId = merchantId;
	}


	@Column(name = "NAME", unique = true, nullable = false, length = 100)
	public String getName()
	{
		return name;
	}


	public void setName(final String name)
	{
		this.name = name;
	}


	@Column(name = "EXTERNAL_TOKEN", nullable = false, length = 36)
	public String getExternalToken()
	{
		return externalToken;
	}


	public void setExternalToken(final String externalToken)
	{
		this.externalToken = externalToken;
	}


	@Column(name = "PRODUCT_QUAL_GROUP_OWNER_ID", precision = 10, scale = 0)
	public Long getProductQualifierGroupOwnerId()
	{
		return productQualifierGroupOwnerId;
	}


	public void setProductQualifierGroupOwnerId(final Long productQualifierGroupOwnerId)
	{
		this.productQualifierGroupOwnerId = productQualifierGroupOwnerId;
	}


	@Column(name = "PRODUCT_QUAL_GROUP_NAME", length = 100)
	public String getProductQualifierGroupName()
	{
		return productQualifierGroupName;
	}


	public void setProductQualifierGroupName(final String productQualifierGroupName)
	{
		this.productQualifierGroupName = productQualifierGroupName;
	}


	@Column(name = "PRODUCT_CROSS_REF_GROUP_ID", precision = 10, scale = 0)
	public Long getProductCrossReferenceGroupId()
	{
		return productCrossReferenceGroupId;
	}


	public void setProductCrossReferenceGroupId(final Long productCrossReferenceGroupId)
	{
		this.productCrossReferenceGroupId = productCrossReferenceGroupId;
	}


	@Column(name = "US_ESTABLISHED_MERCHANT_ID", precision = 10, scale = 0)
	public Long getUsEstablishedMerchantId()
	{
		return (this.usEstablishedMerchantId == null) ? getMerchantId() : usEstablishedMerchantId;
	}


	public void setUsEstablishedMerchantId(final Long usEstablishedMerchantId)
	{
		this.usEstablishedMerchantId = usEstablishedMerchantId;
	}


	@Column(name = "INTL_ESTABLISHED_MERCHANT_ID", precision = 10, scale = 0)
	public Long getIntlEstablishedMerchantId()
	{
		return (this.intlEstablishedMerchantId == null) ? getMerchantId() : intlEstablishedMerchantId;
	}


	public void setIntlEstablishedMerchantId(final Long intlEstablishedMerchantId)
	{
		this.intlEstablishedMerchantId = intlEstablishedMerchantId;
	}


	@Column(name = "US_CUSTOM_DATA_PROVIDER_ID", precision = 10, scale = 0)
	public Long getUsCustomDataProviderId()
	{
		return usCustomDataProviderId;
	}


	public void setUsCustomDataProviderId(final Long usCustomDataProviderId)
	{
		this.usCustomDataProviderId = usCustomDataProviderId;
	}


	@Column(name = "INTL_CUSTOM_DATA_PROVIDER_ID", precision = 10, scale = 0)
	public Long getIntlCustomDataProviderId()
	{
		return intlCustomDataProviderId;
	}


	public void setIntlCustomDataProviderId(final Long intlCustomDataProviderId)
	{
		this.intlCustomDataProviderId = intlCustomDataProviderId;
	}


	@Column(name = "US_PRODUCT_GROUP_ID", precision = 10, scale = 0)
	public Long getUsProductGroupId()
	{
		return usProductGroupId;
	}


	public void setUsProductGroupId(final Long usProductGroupId)
	{
		this.usProductGroupId = usProductGroupId;
	}


	@Column(name = "INTL_PRODUCT_GROUP_ID", precision = 10, scale = 0)
	public Long getIntlProductGroupId()
	{
		return intlProductGroupId;
	}


	public void setIntlProductGroupId(final Long intlProductGroupId)
	{
		this.intlProductGroupId = intlProductGroupId;
	}
}
