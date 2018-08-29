package com.thomsonreuters.extractvalidator.determination.entities;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;


/**
 * Authority Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_AUTHORITIES")
public final class Authority
{
	private Long authorityId;

	/**
	 * The ID of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private ProductGroup productGroup;

	/**
	 * The name of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String name;

	/**
	 * The UUID (Univernally Unique IDentifier) of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String uuid;

	/**
	 * The invoice description of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String invoiceDescription;

	/**
	 * The description of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String description;

	/**
	 * The merchant ID of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long merchantId;

	/**
	 * The region code of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String regionCode;

	/**
	 * The registration mask of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String registrationMask;

	/**
	 * The simple registration mask of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String simpleRegistrationMask;

	/**
	 * The admin zone level of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private ZoneLevel adminZoneLevel;

	/**
	 * The effective zone level of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private ZoneLevel effectiveZoneLevel;

	/**
	 * The authority type ID of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private AuthorityType authorityType;

	/**
	 * The location code of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String locationCode;

	/**
	 * The distance sales threshold of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal distanceSalesThreshold;

	/**
	 * The boolean value representing whether or not the entity is a template.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean isTemplate;

	/**
	 * The boolean value representing whether or not the entity is a custom authority.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private boolean isCustomAuthority;

	/**
	 * The ERP tax code for this entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String erpTaxCode;

	/**
	 * The content type of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String contentType;

	/**
	 * The unit of measure code of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String unitOfMeasureCode;

	/**
	 * The official name of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String officialName;

	/**
	 * The authority category of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String authorityCategory;

	private List<Rule> rules;

	private List<Rate> rates;


	/**
	 * Constructs a new {@code Authority} object as a determination domain model object.
	 */
	public Authority()
	{
	}


	@Id
	@Column(name = "AUTHORITY_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getAuthorityId()
	{
		return authorityId;
	}


	public void setAuthorityId(final Long authorityId)
	{
		this.authorityId = authorityId;
	}


	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	@JoinColumn(name = "PRODUCT_GROUP_ID", nullable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public ProductGroup getProductGroup()
	{
		return productGroup;
	}


	public void setProductGroup(final ProductGroup productGroupId)
	{
		productGroup = productGroupId;
	}


	@Column(name = "NAME", nullable = false, length = 100)
	public String getName()
	{
		return name;
	}


	public void setName(final String name)
	{
		this.name = name;
	}


	@Column(name = "UUID", nullable = false, length = 36)
	public String getUuid()
	{
		return uuid;
	}


	public void setUuid(final String uuid)
	{
		this.uuid = uuid;
	}


	@Column(name = "INVOICE_DESCRIPTION", length = 100)
	public String getInvoiceDescription()
	{
		return invoiceDescription;
	}


	public void setInvoiceDescription(final String invoiceDescription)
	{
		this.invoiceDescription = invoiceDescription;
	}


	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription()
	{
		return description;
	}


	public void setDescription(final String description)
	{
		this.description = description;
	}


	@Column(name = "MERCHANT_ID", nullable = false, precision = 10, scale = 0)
	public Long getMerchantId()
	{
		return merchantId;
	}


	public void setMerchantId(final Long merchantId)
	{
		this.merchantId = merchantId;
	}


	@Column(name = "REGION_CODE", length = 50)
	public String getRegionCode()
	{
		return regionCode;
	}


	public void setRegionCode(final String regionCode)
	{
		this.regionCode = regionCode;
	}


	@Column(name = "REGISTRATION_MASK", length = 100)
	public String getRegistrationMask()
	{
		return registrationMask;
	}


	public void setRegistrationMask(final String registrationMask)
	{
		this.registrationMask = registrationMask;
	}


	@Column(name = "SIMPLE_REGISTRATION_MASK", length = 100)
	public String getSimpleRegistrationMask()
	{
		return simpleRegistrationMask;
	}


	public void setSimpleRegistrationMask(final String simpleRegistrationMask)
	{
		this.simpleRegistrationMask = simpleRegistrationMask;
	}


	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	@JoinColumn(name = "ADMIN_ZONE_LEVEL_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public ZoneLevel getAdminZoneLevel()
	{
		return adminZoneLevel;
	}


	public void setAdminZoneLevel(final ZoneLevel adminZoneLevel)
	{
		this.adminZoneLevel = adminZoneLevel;
	}


	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	@JoinColumn(name = "EFFECTIVE_ZONE_LEVEL_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public ZoneLevel getEffectiveZoneLevel()
	{
		return effectiveZoneLevel;
	}


	public void setEffectiveZoneLevel(final ZoneLevel effectiveZoneLevel)
	{
		this.effectiveZoneLevel = effectiveZoneLevel;
	}


	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	@JoinColumn(name = "AUTHORITY_TYPE_ID", nullable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public AuthorityType getAuthorityType()
	{
		return authorityType;
	}


	public void setAuthorityType(final AuthorityType authorityTypeId)
	{
		authorityType = authorityTypeId;
	}


	@Column(name = "LOCATION_CODE", length = 50)
	public String getLocationCode()
	{
		return locationCode;
	}


	public void setLocationCode(final String locationCode)
	{
		this.locationCode = locationCode;
	}


	@Column(name = "DISTANCE_SALES_THRESHOLD", precision = 31, scale = 5)
	public BigDecimal getDistanceSalesThreshold()
	{
		return distanceSalesThreshold;
	}


	public void setDistanceSalesThreshold(final BigDecimal distanceSalesThreshold)
	{
		this.distanceSalesThreshold = distanceSalesThreshold;
	}


	@Column(name = "IS_TEMPLATE", length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getIsTemplate()
	{
		return isTemplate;
	}


	public void setIsTemplate(final Boolean isTemplate)
	{
		this.isTemplate = isTemplate;
	}


	@Column(name = "IS_CUSTOM_AUTHORITY", nullable = false, length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public boolean isCustomAuthority()
	{
		return isCustomAuthority;
	}


	public void setCustomAuthority(final boolean isCustomAuthority)
	{
		this.isCustomAuthority = isCustomAuthority;
	}


	@Column(name = "ERP_TAX_CODE", length = 200)
	public String getErpTaxCode()
	{
		return erpTaxCode;
	}


	public void setErpTaxCode(final String erpTaxCode)
	{
		this.erpTaxCode = erpTaxCode;
	}


	@Column(name = "CONTENT_TYPE", nullable = false, length = 50)
	public String getContentType()
	{
		return contentType;
	}


	public void setContentType(final String contentType)
	{
		this.contentType = contentType;
	}


	@Column(name = "UNIT_OF_MEASURE_CODE", length = 25)
	public String getUnitOfMeasureCode()
	{
		return unitOfMeasureCode;
	}


	public void setUnitOfMeasureCode(final String unitOfMeasureCode)
	{
		this.unitOfMeasureCode = unitOfMeasureCode;
	}


	@Column(name = "OFFICIAL_NAME", length = 100)
	public String getOfficialName()
	{
		return officialName;
	}


	public void setOfficialName(final String officialName)
	{
		this.officialName = officialName;
	}


	@Column(name = "AUTHORITY_CATEGORY", length = 100)
	public String getAuthorityCategory()
	{
		return authorityCategory;
	}


	public void setAuthorityCategory(final String authorityCategory)
	{
		this.authorityCategory = authorityCategory;
	}


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "AUTHORITY_ID", updatable = false)
	public List<Rule> getRules()
	{
		return rules;
	}


	public void setRules(final List<Rule> rules)
	{
		this.rules = rules;
	}


	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "AUTHORITY_ID", updatable = false)
	public List<Rate> getRates()
	{
		return rates;
	}


	public void setRates(final List<Rate> rates)
	{
		this.rates = rates;
	}
}
