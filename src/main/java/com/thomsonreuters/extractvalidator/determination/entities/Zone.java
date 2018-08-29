package com.thomsonreuters.extractvalidator.determination.entities;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;


/**
 * Zone Description.
 *
 * @author Matt Godsey
 */
public final class Zone
{
	private Long zoneId;

	/**
	 * The name of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _name;

	/**
	 * The parent zone ID of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _parentZoneId;

	/**
	 * The tax parent zone ID of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _taxParentZoneId;

	/**
	 * The merchant ID of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _merchantId;

	/**
	 * The zone level of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private ZoneLevel _zoneLevel;

	/**
	 * The date the entity was admitted into the EU.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Date _euZoneAsOfDate;

	/**
	 * The date the entity was exited out of the EU.
	 *
	 * @serial
	 * @since 4.0.0
	 */
	private Date euExitDate;

	/**
	 * The date the entity was admitted into the GCC.
	 *
	 * @serial
	 * @since 4.0.0
	 */
	private Date gccAsOfDate;

	/**
	 * The date the entity was exited out of the GCC.
	 *
	 * @serial
	 * @since 4.0.0
	 */
	private Date gccExitDate;


	/**
	 * The two character code of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _code2char;

	/**
	 * The three character code of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _code3char;

	/**
	 * The ISO code of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _codeIso;

	/**
	 * The FIPS code of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _codeFips;

	/**
	 * The default zone indicator of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean _defaultZone;

	/**
	 * The minimum range of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _rangeMin;

	/**
	 * The maximum range of an entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _rangeMax;


	/**
	 * Constructs a new instance of the entity representing geographical zone information.
	 */
	public Zone()
	{
	}


	@Id
	@Column(name = "ZONE_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getZoneId()
	{
		return zoneId;
	}


	public void setZoneId(final Long zoneId)
	{
		this.zoneId = zoneId;
	}


	@Column(name = "NAME", nullable = false, length = 50)
	public String getName()
	{
		return _name;
	}


	public void setName(final String name)
	{
		_name = name;
	}


	@Column(name = "PARENT_ZONE_ID", nullable = false, precision = 10, scale = 0)
	public Long getParentZoneId()
	{
		return _parentZoneId;
	}


	public void setParentZoneId(final Long parentZoneId)
	{
		_parentZoneId = parentZoneId;
	}


	@Column(name = "TAX_PARENT_ZONE_ID", precision = 10, scale = 0)
	public Long getTaxParentZoneId()
	{
		return _taxParentZoneId;
	}


	public void setTaxParentZoneId(final Long taxParentZoneId)
	{
		_taxParentZoneId = taxParentZoneId;
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


	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH})
	@JoinColumn(name = "ZONE_LEVEL_ID", nullable = true)
	@NotFound(action = NotFoundAction.IGNORE)
	public ZoneLevel getZoneLevel()
	{
		return _zoneLevel;
	}


	/**
	 * Sets the zone level the entity.
	 *
	 * @param zoneLevel The zone level of the entity.
	 */
	public void setZoneLevel(final ZoneLevel zoneLevel)
	{
		_zoneLevel = zoneLevel;
	}


	@Transient
	public Long getZoneLevelId()
	{
		return getZoneLevel() != null ? getZoneLevel().getZoneLevelId() : null;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "EU_ZONE_AS_OF_DATE", length = 7)
	public Date getEuZoneAsOfDate()
	{
		return _euZoneAsOfDate;
	}


	public void setEuZoneAsOfDate(final Date euZoneAsOfDate)
	{
		_euZoneAsOfDate = euZoneAsOfDate;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "EU_EXIT_DATE", length = 7)
	public Date getEuExitDate()
	{
		return euExitDate;
	}


	public void setEuExitDate(final Date euExitDate)
	{
		this.euExitDate = euExitDate;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "GCC_AS_OF_DATE", length = 7)
	public Date getGccAsOfDate()
	{
		return gccAsOfDate;
	}


	public void setGccAsOfDate(final Date gccAsOfDate)
	{
		this.gccAsOfDate = gccAsOfDate;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "GCC_EXIT_DATE", length = 7)
	public Date getGccExitDate()
	{
		return gccExitDate;
	}


	public void setGccExitDate(final Date gccExitDate)
	{
		this.gccExitDate = gccExitDate;
	}


	@Column(name = "CODE_2CHAR", length = 2)
	public String getCode2char()
	{
		return _code2char;
	}


	public void setCode2char(final String code2char)
	{
		_code2char = code2char;
	}


	@Column(name = "CODE_3CHAR", length = 3)
	public String getCode3char()
	{
		return _code3char;
	}


	public void setCode3char(final String code3char)
	{
		_code3char = code3char;
	}


	@Column(name = "CODE_ISO", length = 3)
	public String getCodeIso()
	{
		return _codeIso;
	}


	public void setCodeIso(final String codeIso)
	{
		_codeIso = codeIso;
	}


	@Column(name = "CODE_FIPS", length = 30)
	public String getCodeFips()
	{
		return _codeFips;
	}


	public void setCodeFips(final String codeFips)
	{
		_codeFips = codeFips;
	}


	@Type(type = "org.hibernate.type.YesNoType")
	@Column(name = "DEFAULT_FLAG", length = 1)
	public Boolean getDefaultZone()
	{
		return _defaultZone;
	}


	public void setDefaultZone(final Boolean defaultFlag)
	{
		_defaultZone = defaultFlag;
	}


	@Column(name = "RANGE_MIN", precision = 10, scale = 0)
	public Long getRangeMin()
	{
		return _rangeMin;
	}


	public void setRangeMin(final Long rangeMin)
	{
		_rangeMin = rangeMin;
	}


	@Column(name = "RANGE_MAX", precision = 10, scale = 0)
	public Long getRangeMax()
	{
		return _rangeMax;
	}


	public void setRangeMax(final Long rangeMax)
	{
		_rangeMax = rangeMax;
	}
}
