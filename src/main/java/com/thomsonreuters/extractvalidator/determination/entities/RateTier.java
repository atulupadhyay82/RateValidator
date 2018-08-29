package com.thomsonreuters.extractvalidator.determination.entities;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


/**
 * RateTier Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_RATE_TIERS")
public final class RateTier
{
	private Long rateTierId;

	/**
	 * References the rate ID for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _rateId;

	/**
	 * References the rate for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal _rate;

	/**
	 * References the flat fee for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal _flatFee;

	/**
	 * References the low amount for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal _amountLow;

	/**
	 * References the high amount for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal _amountHigh;

	/**
	 * References the rate code for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _rateCode;

	/**
	 * References the rate code for this entity.
	 *
	 * @serial
	 * @since Cloud 5.0.0.0
	 */
	private Boolean exempt;


	/**
	 * Constructs a new {@code RateTier}.
	 */
	public RateTier()
	{
	}


	@Id
	@Column(name = "RATE_TIER_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getRateTierId()
	{
		return rateTierId;
	}


	public void setRateTierId(final Long rateTierId)
	{
		this.rateTierId = rateTierId;
	}


	@Column(name = "RATE_ID", nullable = false, precision = 10, scale = 0)
	public Long getRateId()
	{
		return _rateId;
	}


	public void setRateId(final Long rateId)
	{
		_rateId = rateId;
	}


	@Column(name = "RATE", precision = 31, scale = 10)
	public BigDecimal getRate()
	{
		return _rate;
	}


	public void setRate(final BigDecimal rate)
	{
		_rate = rate;
	}


	@Column(name = "FLAT_FEE", precision = 31, scale = 10)
	public BigDecimal getFlatFee()
	{
		return _flatFee;
	}


	public void setFlatFee(final BigDecimal flatFee)
	{
		_flatFee = flatFee;
	}


	@Column(name = "AMOUNT_LOW", nullable = false, precision = 31, scale = 5)
	public BigDecimal getAmountLow()
	{
		return _amountLow;
	}


	public void setAmountLow(final BigDecimal amountLow)
	{
		_amountLow = amountLow;
	}


	@Column(name = "AMOUNT_HIGH", precision = 31, scale = 5)
	public BigDecimal getAmountHigh()
	{
		return _amountHigh;
	}


	public void setAmountHigh(final BigDecimal amountHigh)
	{
		_amountHigh = amountHigh;
	}


	@Column(name = "RATE_CODE", length = 50)
	public String getRateCode()
	{
		return _rateCode;
	}


	public void setRateCode(final String rateCode)
	{
		_rateCode = rateCode;
	}


	@Column(name = "EXEMPT", length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getExempt()
	{
		return exempt;
	}


	public void setExempt(final Boolean exempt)
	{
		this.exempt = exempt;
	}
}
