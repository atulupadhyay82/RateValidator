package com.thomsonreuters.extractvalidator.determination.entities;


import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;


/**
 * Rule Description.
 *
 * @author Matt Godsey
 */
@Entity
@Table(name = "TB_RULES")
public final class Rule
{
	private Long ruleId;

	private Long authorityId;

	/**
	 * The rule order of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal _ruleOrder;

	/**
	 * The 'active' value of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean _active;

	/**
	 * The ProductCategory ID of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private ProductCategory _productCategory;


	/**
	 * The merchant ID of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _merchantId;

	/**
	 * The tax code of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _code;

	/**
	 * The exempt reason code of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _exemptReasonCode;

	/**
	 * The calculation method of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _calculationMethod;

	/**
	 * The invoice description of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _invoiceDescription;

	/**
	 * The 'isExempt' boolean value of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean _exempt;

	/**
	 * The 'deleted' value of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean _deleted;

	/**
	 * The rate code of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _rateCode;

	/**
	 * The basis percent of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal _basisPercent;

	/**
	 * The rule comment of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _ruleComment;

	/**
	 * The tax type of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _taxType;

	/**
	 * The boolean value 'isLocal'.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean _isLocal;

	/**
	 * The local authority type ID of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Long _localAuthorityTypeId;

	/**
	 * The input recovery amount of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal _inputRecoveryAmount;

	/**
	 * The input recovery percent of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private BigDecimal _inputRecoveryPercent;

	/**
	 * The unit of measure of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _unitOfMeasure;

	/**
	 * The boolean 'noTax' value of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private Boolean _noTax;

	/**
	 * The tax treatment of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private String _taxTreatment;

	/**
	 * The 'isDependentProduct' boolean value of the entity.
	 *
	 * @serial
	 * @since 5.6.0.0
	 */
	private boolean _dependentProduct;

	/**
	 * References the allocated charge flag for this entity.
	 *
	 * @serial
	 * @since 5.7.0.0
	 */
	private Boolean _allocatedCharge;


	/**
	 * Constructs a new {@code Rule}.
	 */
	public Rule()
	{
	}


	@Id
	@Column(name = "RULE_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getRuleId()
	{
		return ruleId;
	}


	public void setRuleId(final Long ruleId)
	{
		this.ruleId = ruleId;
	}


	@Column(name = "AUTHORITY_ID", nullable = false, precision = 10, scale = 0)
	public Long getAuthorityId()
	{
		return authorityId;
	}


	public void setAuthorityId(final Long authorityId)
	{
		this.authorityId = authorityId;
	}


	@Column(name = "RULE_ORDER", nullable = false, precision = 31, scale = 10)
	public BigDecimal getRuleOrder()
	{
		return _ruleOrder;
	}


	public void setRuleOrder(final BigDecimal ruleOrder)
	{
		_ruleOrder = ruleOrder;
	}


	@Column(name = "ACTIVE", length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getActive()
	{
		return _active;
	}


	public void setActive(final Boolean active)
	{
		_active = active;
	}


	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH})
	@JoinColumn(name = "PRODUCT_CATEGORY_ID")
	@NotFound(action = NotFoundAction.IGNORE)
	public ProductCategory getProductCategory()
	{
		return _productCategory;
	}


	public void setProductCategory(final ProductCategory productCategoryId)
	{
		_productCategory = productCategoryId;
	}


	@Column(name = "MERCHANT_ID", nullable = false)
	public Long getMerchantId()
	{
		return _merchantId;
	}


	public void setMerchantId(final Long merchantId)
	{
		_merchantId = merchantId;
	}


	@Column(name = "CODE", length = 50)
	public String getCode()
	{
		return _code;
	}


	public void setCode(final String code)
	{
		_code = code;
	}


	@Column(name = "EXEMPT_REASON_CODE", length = 50)
	public String getExemptReasonCode()
	{
		return _exemptReasonCode;
	}


	public void setExemptReasonCode(final String exemptReasonCode)
	{
		_exemptReasonCode = exemptReasonCode;
	}


	@Column(name = "CALCULATION_METHOD", length = 50)
	public String getCalculationMethod()
	{
		return _calculationMethod;
	}


	public void setCalculationMethod(final String calculationMethod)
	{
		_calculationMethod = calculationMethod;
	}


	@Column(name = "INVOICE_DESCRIPTION", length = 100)
	public String getInvoiceDescription()
	{
		return _invoiceDescription;
	}


	public void setInvoiceDescription(final String invoiceDescription)
	{
		_invoiceDescription = invoiceDescription;
	}


	@Column(name = "EXEMPT", length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getExempt()
	{
		return _exempt;
	}


	public void setExempt(final Boolean exempt)
	{
		_exempt = exempt;
	}


	@Column(name = "DELETED", length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getDeleted()
	{
		return _deleted;
	}


	public void setDeleted(final Boolean deleted)
	{
		_deleted = deleted;
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


	@Column(name = "BASIS_PERCENT", precision = 31, scale = 10)
	public BigDecimal getBasisPercent()
	{
		return _basisPercent;
	}


	public void setBasisPercent(final BigDecimal basisPercent)
	{
		_basisPercent = basisPercent;
	}


	@Column(name = "RULE_COMMENT", length = 2000)
	public String getRuleComment()
	{
		return _ruleComment;
	}


	public void setRuleComment(final String ruleComment)
	{
		_ruleComment = ruleComment;
	}


	@Column(name = "TAX_TYPE", length = 100)
	public String getTaxType()
	{
		return _taxType;
	}


	public void setTaxType(final String taxType)
	{
		_taxType = taxType;
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


	@Column(name = "LOCAL_AUTHORITY_TYPE_ID", precision = 10, scale = 0)
	public Long getLocalAuthorityTypeId()
	{
		return _localAuthorityTypeId;
	}


	public void setLocalAuthorityTypeId(final Long localAuthorityTypeId)
	{
		_localAuthorityTypeId = localAuthorityTypeId;
	}


	@Column(name = "INPUT_RECOVERY_AMOUNT", precision = 31, scale = 5)
	public BigDecimal getInputRecoveryAmount()
	{
		return _inputRecoveryAmount;
	}


	public void setInputRecoveryAmount(final BigDecimal inputRecoveryAmount)
	{
		_inputRecoveryAmount = inputRecoveryAmount;
	}


	@Column(name = "INPUT_RECOVERY_PERCENT", precision = 31, scale = 10)
	public BigDecimal getInputRecoveryPercent()
	{
		return _inputRecoveryPercent;
	}


	public void setInputRecoveryPercent(final BigDecimal inputRecoveryPercent)
	{
		_inputRecoveryPercent = inputRecoveryPercent;
	}


	@Column(name = "UNIT_OF_MEASURE", length = 100)
	public String getUnitOfMeasure()
	{
		return _unitOfMeasure;
	}


	public void setUnitOfMeasure(final String unitOfMeasure)
	{
		_unitOfMeasure = unitOfMeasure;
	}


	@Column(name = "NO_TAX", length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getNoTax()
	{
		return _noTax;
	}


	public void setNoTax(final Boolean noTax)
	{
		_noTax = noTax;
	}


	@Column(name = "TAX_TREATMENT", length = 100)
	public String getTaxTreatment()
	{
		return _taxTreatment;
	}


	public void setTaxTreatment(final String taxTreatment)
	{
		_taxTreatment = taxTreatment;
	}


	@Column(name = "IS_DEPENDENT_PRODUCT", nullable = false, length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public boolean isDependentProduct()
	{
		return _dependentProduct;
	}


	public void setDependentProduct(final boolean isDependentProduct)
	{
		_dependentProduct = isDependentProduct;
	}


	@Column(name = "ALLOCATED_CHARGE", length = 1)
	@Type(type = "org.hibernate.type.YesNoType")
	public Boolean getAllocatedCharge()
	{
		return _allocatedCharge;
	}


	public void setAllocatedCharge(final Boolean allocatedCharge)
	{
		_allocatedCharge = allocatedCharge;
	}
}
