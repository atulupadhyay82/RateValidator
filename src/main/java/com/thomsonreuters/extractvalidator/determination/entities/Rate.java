package com.thomsonreuters.extractvalidator.determination.entities;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


/**
 * Rate Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_RATES")
public final class Rate
{
	private Long rateId;

	/**
	 * References the rate code for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _rateCode;

	/**
	 * References the description for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _description;

	/**
	 * References the rate for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal _rate;

	/**
	 * References the minimum tax amount for this entity.
	 *
	 * @serial
	 * @since 5.6.2.0
	 */
	private BigDecimal _minAmount;

	/**
	 * References the flat fee flag for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean _flatFee;

	/**
	 * References the currency ID for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _currencyId;

	/**
	 * References the unit of measure for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _unitOfMeasureCode;

	/**
	 * References the use default quantity flag for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean _useDefaultQty;

	/**
	 * References the merchant ID for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _merchantId;

	/**
	 * References the authority ID for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _authorityId;

	/**
	 * References the split type for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _splitType;

	/**
	 * References the split amount type for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _splitAmountType;

	/**
	 * References the local flag for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean _isLocal;

	private List<RateTier> rateTiers;


	/**
	 * Constructs a new {@code Rate}.
	 */
	public Rate()
	{
	}


	@Id
	@Column(name = "RATE_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getRateId()
	{
		return rateId;
	}


	public void setRateId(final Long rateId)
	{
		this.rateId = rateId;
	}


	@Column(name = "RATE_CODE", nullable = false, length = 50)
	public String getRateCode()
	{
		return _rateCode;
	}


	public void setRateCode(final String rateCode)
	{
		_rateCode = rateCode;
	}


	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription()
	{
		return _description;
	}


	public void setDescription(final String description)
	{
		_description = description;
	}


	@Column(name = "RATE", precision = 31, scale = 10)
	public BigDecimal getRate()
	{
		return _rate;
	}


	@Column(name = "MIN_AMOUNT", precision = 31, scale = 10)
	public BigDecimal getMinAmount()
	{
		return _minAmount;
	}


	public void setMinAmount(final BigDecimal minAmount)
	{
		_minAmount = minAmount;
	}


	public void setRate(final BigDecimal rate)
	{
		_rate = rate;
	}


	@Column(name = "FLAT_FEE", precision = 31, scale = 5)
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public Boolean getFlatFee()
	{
		return _flatFee;
	}


	public void setFlatFee(final Boolean flatFee)
	{
		_flatFee = flatFee;
	}


	@Column(name = "CURRENCY_ID", precision = 10, scale = 0)
	public Long getCurrencyId()
	{
		return _currencyId;
	}


	public void setCurrencyId(final Long currencyId)
	{
		_currencyId = currencyId;
	}


	@Column(name = "UNIT_OF_MEASURE_CODE", length = 25)
	public String getUnitOfMeasureCode()
	{
		return _unitOfMeasureCode;
	}


	public void setUnitOfMeasureCode(final String unitOfMeasureCode)
	{
		_unitOfMeasureCode = unitOfMeasureCode;
	}


	@Column(name = "USE_DEFAULT_QTY", length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getUseDefaultQty()
	{
		return _useDefaultQty;
	}


	public void setUseDefaultQty(final Boolean useDefaultQty)
	{
		_useDefaultQty = useDefaultQty;
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


	@Column(name = "AUTHORITY_ID", nullable = false, precision = 10, scale = 0)
	public Long getAuthorityId()
	{
		return _authorityId;
	}


	public void setAuthorityId(final Long authorityId)
	{
		_authorityId = authorityId;
	}


	@Column(name = "SPLIT_TYPE", length = 2)
	public String getSplitType()
	{
		return _splitType;
	}


	public void setSplitType(final String splitType)
	{
		_splitType = splitType;
	}


	@Column(name = "SPLIT_AMOUNT_TYPE", length = 1)
	public String getSplitAmountType()
	{
		return _splitAmountType;
	}


	public void setSplitAmountType(final String splitAmountType)
	{
		_splitAmountType = splitAmountType;
	}


	@Column(name = "IS_LOCAL", length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getIsLocal()
	{
		return _isLocal;
	}


	public void setIsLocal(final Boolean isLocal)
	{
		_isLocal = isLocal;
	}


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "RATE_ID", updatable = false)
	public List<RateTier> getRateTiers()
	{
		return rateTiers;
	}


	public void setRateTiers(final List<RateTier> rateTiers)
	{
		this.rateTiers = rateTiers;
	}
}
