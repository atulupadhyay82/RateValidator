package com.thomsonreuters.extractvalidator.determination.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * AuthorityType Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_AUTHORITY_TYPES")
public final class AuthorityType
{
	private Long authorityTypeId;

	/**
	 * References the merchant ID associated with the authority type entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _merchantId;

	/**
	 * References the description of the authority type entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _description;

	/**
	 * References the name of the authority type entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _name;


	public AuthorityType()
	{
	}


	@Id
	@Column(name = "AUTHORITY_TYPE_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getAuthorityTypeId()
	{
		return authorityTypeId;
	}


	public void setAuthorityTypeId(final Long authorityTypeId)
	{
		this.authorityTypeId = authorityTypeId;
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


	@Column(name = "NAME", nullable = false, length = 100)
	public String getName()
	{
		return _name;
	}


	public void setName(final String name)
	{
		_name = name;
	}


	@Column(name = "DESCRIPTION", length = 1000)
	public String getDescription()
	{
		return _description;
	}


	public void setDescription(final String description)
	{
		_description = description;
	}
}
