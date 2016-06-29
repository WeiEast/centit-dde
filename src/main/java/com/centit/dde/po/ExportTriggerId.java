package com.centit.dde.po;


/**
 * FAddressBook entity.
 *
 * @author codefan@hotmail.com
 */

public class ExportTriggerId implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    private Long triggerId;

    private Long exportId;

    // Constructors

    /**
     * default constructor
     */
    public ExportTriggerId() {
    }

    /**
     * full constructor
     */
    public ExportTriggerId(Long triggerId, Long mapinfoId) {

        this.triggerId = triggerId;
        this.exportId = mapinfoId;
    }


    public Long getTriggerId() {
        return this.triggerId;
    }

    public void setTriggerId(Long triggerId) {
        this.triggerId = triggerId;
    }

    public Long getExportId() {
        return this.exportId;
    }

    public void setExportId(Long mapinfoId) {
        this.exportId = mapinfoId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof ExportTriggerId))
            return false;

        ExportTriggerId castOther = (ExportTriggerId) other;
        boolean ret = true;

        ret = ret && (this.getTriggerId() == castOther.getTriggerId() ||
                (this.getTriggerId() != null && castOther.getTriggerId() != null
                        && this.getTriggerId().equals(castOther.getTriggerId())));

        ret = ret && (this.getExportId() == castOther.getExportId() ||
                (this.getExportId() != null && castOther.getExportId() != null
                        && this.getExportId().equals(castOther.getExportId())));

        return ret;
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result +
                (this.getTriggerId() == null ? 0 : this.getTriggerId().hashCode());

        result = 37 * result +
                (this.getExportId() == null ? 0 : this.getExportId().hashCode());

        return result;
    }
}