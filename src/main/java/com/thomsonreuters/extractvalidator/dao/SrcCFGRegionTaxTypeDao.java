package com.thomsonreuters.extractvalidator.dao;

import com.thomsonreuters.extractvalidator.entities.SrcCFGRegionTaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Getting the tax type for the extract.
 *
 * @author Atul Upadhyay
 */
@Repository(SrcCFGRegionTaxTypeDao.BEAN_NAME)
public interface SrcCFGRegionTaxTypeDao extends JpaRepository<SrcCFGRegionTaxType,Long> {

    /**
     * Denotes the bean name for this component
     */
    public static final String BEAN_NAME = "SrcCFGRegionTaxTypeDao";

    /**
     * Retreive taxtype from the database for the extract
     *
     * @param extractName
     *
     * @return
     */
    @Query(value="SELECT TAX_TYPE FROM EX_CFG_REGION_TAX_TYPES WHERE EXTRACT_REGION_ID IN " +
            "(SELECT EXTRACT_REGION_ID FROM EX_CFG_REGIONS WHERE EXTRACT_ID IN" +
            "(SELECT EXTRACT_ID FROM EX_CFG_EXTRACTS WHERE name= :extractName))",
            nativeQuery = true)
    List<String> getTaxTypeForExtract(@Param("extractName") final String extractName);


}
