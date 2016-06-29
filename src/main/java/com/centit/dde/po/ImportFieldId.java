package com.centit.dde.po;

/**
 * FAddressBook entity.
 *
 * @author codefan@hotmail.com
 */

public class ImportFieldId implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long columnNo;

    private Long importId;

    // Constructors

    /**
     * default constructor
     */
    public ImportFieldId() {
    }

    /**
     * full constructor
     */
    public ImportFieldId(Long columnNo, Long importId) {

        this.columnNo = columnNo;
        this.importId = importId;
    }

    public Long getColumnNo() {
        return this.columnNo;
    }

    public void setColumnNo(Long columnNo) {
        this.columnNo = columnNo;
    }

    public Long getImportId() {
        return this.importId;
    }

    public void setImportId(Long importId) {
        this.importId = importId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof ImportFieldId))
            return false;

        ImportFieldId castOther = (ImportFieldId) other;
        boolean ret = true;

        ret = ret
                && (this.getColumnNo() == castOther.getColumnNo() || (this.getColumnNo() != null
                && castOther.getColumnNo() != null && this.getColumnNo().equals(castOther.getColumnNo())));

        ret = ret
                && (this.getImportId() == castOther.getImportId() || (this.getImportId() != null
                && castOther.getImportId() != null && this.getImportId().equals(castOther.getImportId())));

        return ret;
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (this.getColumnNo() == null ? 0 : this.getColumnNo().hashCode());

        result = 37 * result + (this.getImportId() == null ? 0 : this.getImportId().hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "ImportFieldId [columnNo=" + columnNo + ", importId=" + importId + "]";
    }

}