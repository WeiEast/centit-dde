package com.centit.dde.po;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.IdClass;

/**
 * FAddressBook entity.
 *
 * @author codefan@hotmail.com
 */
@Embeddable
@IdClass(MapInfoTrigger.class)
public class MapInfoTriggerId implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name="TRIGGER_ID")
    @NotBlank(message = "字段不能为空")
    private Long triggerId;

    @Column(name="MAPINFO_ID")
    @NotBlank(message = "字段不能为空")
    private Long mapinfoId;

    // Constructors

    /**
     * default constructor
     */
    public MapInfoTriggerId() {
    }

    /**
     * full constructor
     */
    public MapInfoTriggerId(Long triggerId, Long mapinfoId) {

        this.triggerId = triggerId;
        this.mapinfoId = mapinfoId;
    }


    public Long getTriggerId() {
        return this.triggerId;
    }

    public void setTriggerId(Long triggerId) {
        this.triggerId = triggerId;
    }

    public Long getMapinfoId() {
        return this.mapinfoId;
    }

    public void setMapinfoId(Long mapinfoId) {
        this.mapinfoId = mapinfoId;
    }


    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MapInfoTriggerId))
            return false;

        MapInfoTriggerId castOther = (MapInfoTriggerId) other;
        boolean ret = true;

        ret = ret && (this.getTriggerId() == castOther.getTriggerId() ||
                (this.getTriggerId() != null && castOther.getTriggerId() != null
                        && this.getTriggerId().equals(castOther.getTriggerId())));

        ret = ret && (this.getMapinfoId() == castOther.getMapinfoId() ||
                (this.getMapinfoId() != null && castOther.getMapinfoId() != null
                        && this.getMapinfoId().equals(castOther.getMapinfoId())));

        return ret;
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result +
                (this.getTriggerId() == null ? 0 : this.getTriggerId().hashCode());

        result = 37 * result +
                (this.getMapinfoId() == null ? 0 : this.getMapinfoId().hashCode());

        return result;
    }
}