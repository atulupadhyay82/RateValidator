package com.thomsonreuters.extractvalidator.dao;


import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thomsonreuters.extractvalidator.entities.SrcRuleQualifier;


/**
 * RuleQualifierRepository Description.
 *
 * @author SrinivasanKrishnamurthy
 */
@Repository(SrcRuleQualifierDao.BEAN_NAME)
public interface SrcRuleQualifierDao extends JpaRepository<SrcRuleQualifier,Long>
{
	/**
	 * Denotes the bean name for this component
	 */
	public static final String BEAN_NAME = "SrcRuleQualifierDao";

	/**
	 * Check if the rule order exists in rule qualifier
	 *
	 * @param ruleOrder
	 * @param authority
	 *
	 * @return
	 */
	@Query(value="select count(*) from ex_src_rule_qualifiers where rule_nkey in " +
			   "(select rule_nkey from ex_src_rules where rule_order= :ruleOrder and " +
			   "authority_nkey in (select authority_nkey from ex_src_authorities " +
			   "where name= :authority))",
			   nativeQuery = true)
	int ruleOrderExistsInRuleQualifier(@Param("ruleOrder") final BigDecimal ruleOrder,
									   @Param("authority") final String authority);


	/**
	 * Check if the rule order exists in rule table
	 *
	 * @param ruleOrder
	 * @param authority
	 *
	 * @return
	 */
	@Query(value="select count(*) from ex_src_rules where rule_order= :ruleOrder and " +
				 "authority_nkey in (select authority_nkey from ex_src_authorities " +
				 "where name= :authority)",
			     nativeQuery = true)
	int ruleOrderExistsInSrcRule(@Param("ruleOrder") final BigDecimal ruleOrder,
								 @Param("authority") final String authority);
}
