package com.centit.dde.po;


/**
 * FAddressBook entity.
 *
 * @author codefan@hotmail.com
 */

public class ExportFieldId implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long exportId;

    private Long columnNo;

    // Constructors

    /**
     * default constructor
     */
    public ExportFieldId() {
    }

    /**
     * full constructor
     */
    public ExportFieldId(Long exportId, Long columnNo) {

        this.exportId = exportId;
        this.columnNo = columnNo;
    }


    public Long getExportId() {
        return this.exportId;
    }

    public void setExportId(Long exportId) {
        this.exportId = exportId;
    }

    public Long getColumnNo() {
        return this.columnNo;
    }

    public void setColumnNo(Long columnNo) {
        this.columnNo = columnNo;
    }


    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof ExportFieldId))
            return false;

        ExportFieldId castOther = (ExportFieldId) other;
        boolean ret = true;

        ret = ret && (this.getExportId() == castOther.getExportId() ||
                (this.getExportId() != null && castOther.getExportId() != null
                        && this.getExportId().equals(castOther.getExportId())));

        ret = ret && (this.getColumnNo() == castOther.getColumnNo() ||
                (this.getColumnNo() != null && castOther.getColumnNo() != null
                        && this.getColumnNo().equals(castOther.getColumnNo())));

        return ret;
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result +
                (this.getExportId() == null ? 0 : this.getExportId().hashCode());

        result = 37 * result +
                (this.getColumnNo() == null ? 0 : this.getColumnNo().hashCode());

        return result;
    }
}