package com.thomsonreuters.extractvalidator.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EX_CFG_REGION_TAX_TYPES")
public class SrcCFGRegionTaxType implements Serializable {

    /**
     * UID generated by Intellij
     */
    private static final long serialVersionUID = -1172033446674157988L;
    /**
     * database ID for the extract region tax type
     */
    @Id
    @Column(name = "EXTRACT_REGION_TAX_TYPE_ID")
    private Long extractRegionTaxTypeId;

    /**
     * database ID for the extract region
     */
    @Column(name = "EXTRACT_REGION_ID")
    private Long extractRegionId;

    /**
     * indicates the tax type for the tax
     */
    @Column(name = "TAX_TYPE")
    private String taxType;

    /**
     * date at which the region tax type was added to the database
     */
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    /**
     * date at which the region tax type was last updated in the database
     */
    @Column(name = "LAST_UPDATE_DATE")
    private LocalDateTime lastUpdateDate;

    /**
     * database ID of the user who created the extract
     */
    @Column(name = "CREATED_BY")
    private String createdBy;

    /**
     * database ID of the user who last updated the extract
     */
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;

    /**
     * date at which the extract was synchronized in the database
     */
    @Column(name = "SYNCHRONIZATION_TIMESTAMP")
    private LocalDateTime synchronizationTimestamp;
}
