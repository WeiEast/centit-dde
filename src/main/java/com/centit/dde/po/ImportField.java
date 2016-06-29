package com.centit.dde.po;

/**
 * create by scaffold
 *
 * @author codefan@hotmail.com
 */

public class ImportField implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private ImportFieldId cid;

    private String sourceFieldName;

    private String destFieldName;

    private String destFieldType;

    private String isPk;

    private String destFieldDefault;

    private String isNull;

    // Constructors

    /**
     * default constructor
     */
    public ImportField() {
    }

    /**
     * minimal constructor
     */
    public ImportField(ImportFieldId id

            , String sourceFieldName, String destFieldName) {
        this.cid = id;

        this.sourceFieldName = sourceFieldName;
        this.destFieldName = destFieldName;
    }

    /**
     * full constructor
     */
    public ImportField(ImportFieldId id

            , String sourceFieldName, String destFieldName, String destFieldType, String isPk, String destFieldDefault,
                       String isNull) {
        this.cid = id;

        this.sourceFieldName = sourceFieldName;
        this.destFieldName = destFieldName;
        this.destFieldType = destFieldType;
        this.isPk = isPk;
        this.destFieldDefault = destFieldDefault;
        this.isNull = isNull;
    }

    public ImportFieldId getCid() {
        return this.cid;
    }

    public void setCid(ImportFieldId id) {
        this.cid = id;
    }

    public Long getColumnNo() {
        if (this.cid == null)
            this.cid = new ImportFieldId();
        return this.cid.getColumnNo();
    }

    public void setColumnNo(Long columnNo) {
        if (this.cid == null)
            this.cid = new ImportFieldId();
        this.cid.setColumnNo(columnNo);
    }

    public Long getImportId() {
        if (this.cid == null)
            this.cid = new ImportFieldId();
        return this.cid.getImportId();
    }

    public void setImportId(Long importId) {
        if (this.cid == null)
            this.cid = new ImportFieldId();
        this.cid.setImportId(importId);
    }

    // Property accessors

    public String getSourceFieldName() {
        return this.sourceFieldName;
    }

    public void setSourceFieldName(String sourceFieldName) {
        this.sourceFieldName = sourceFieldName;
    }

    public String getDestFieldName() {
        return this.destFieldName;
    }

    public void setDestFieldName(String destFieldName) {
        this.destFieldName = destFieldName;
    }

    public String getDestFieldType() {
        return this.destFieldType;
    }

    public void setDestFieldType(String destFieldType) {
        this.destFieldType = destFieldType;
    }

    public boolean isKey() {
        return "1".equals(this.isPk);
    }

    public String getIsPk() {
        return this.isPk;
    }

    public void setIsPk(String isPk) {
        this.isPk = isPk;
    }

    public String getDestFieldDefault() {
        return this.destFieldDefault;
    }

    public void setDestFieldDefault(String destFieldDefault) {
        this.destFieldDefault = destFieldDefault;
    }

    public String getIsNull() {
        return this.isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public void copy(ImportField other) {

        this.setColumnNo(other.getColumnNo());
        this.setImportId(other.getImportId());

        this.sourceFieldName = other.getSourceFieldName();
        this.destFieldName = other.getDestFieldName();
        this.destFieldType = other.getDestFieldType();
        this.isPk = other.getIsPk();
        this.destFieldDefault = other.getDestFieldDefault();
        this.isNull = other.getIsNull();

    }

    public void copyNotNullProperty(ImportField other) {

        if (other.getColumnNo() != null)
            this.setColumnNo(other.getColumnNo());
        if (other.getImportId() != null)
            this.setImportId(other.getImportId());

        if (other.getSourceFieldName() != null)
            this.sourceFieldName = other.getSourceFieldName();
        if (other.getDestFieldName() != null)
            this.destFieldName = other.getDestFieldName();
        if (other.getDestFieldType() != null)
            this.destFieldType = other.getDestFieldType();
        if (other.getIsPk() != null)
            this.isPk = other.getIsPk();
        if (other.getDestFieldDefault() != null)
            this.destFieldDefault = other.getDestFieldDefault();
        if (other.getIsNull() != null)
            this.isNull = other.getIsNull();

    }

    public void clearProperties() {

        this.sourceFieldName = null;
        this.destFieldName = null;
        this.destFieldType = null;
        this.isPk = null;
        this.destFieldDefault = null;
        this.isNull = null;

    }

    @Override
    public String toString() {
        return "ImportField [cid=" + cid + ", sourceFieldName=" + sourceFieldName + ", destFieldName=" + destFieldName
                + ", destFieldType=" + destFieldType + ", isPk=" + isPk + ", destFieldDefault=" + destFieldDefault
                + ", isNull=" + isNull + "]";
    }

}