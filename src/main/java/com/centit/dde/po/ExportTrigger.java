package com.centit.dde.po;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


/**
 * create by scaffold
 *
 * @author codefan@hotmail.com
 */
@Entity
@Table(name="D_EXPORT_TRIGGER")
public class ExportTrigger implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    @Column(name="CID")
    @GeneratedValue(generator = "assignedGenerator")
    @GenericGenerator(name = "assignedGenerator", strategy = "assigned")
    private ExportTriggerId cid;
    
    @Column(name="TRIGGERSQL")
    private String triggerSql;
    
    @Column(name="TRIGGERDESC")
    private String triggerDesc;
    
    @Column(name="TRIGGERTYPE")
    private String triggerType;
    
    @Column(name="TRIGGERTIME")
    private String triggerTime;

    @Column(name="TIGGERORDER")
    private Long tiggerOrder;
    
    @Column(name="ISPROCEDURE")
    private String isprocedure;

    /**
     * 是否是  交换前表级的触发器 源库
     *
     * @return
     */
    public boolean isBeforeTransferAtSource() {
        return "B".equals(triggerTime) && "T".equals(triggerType);
    }

    /**
     * 是否是  交换后表级的触发器 源库
     *
     * @return
     */
    public boolean isAfterTransferAtSource() {
        return "A".equals(triggerTime) && "T".equals(triggerType);
    }

    /**
     * 是否是 交换前行级的触发器 源库
     *
     * @return
     */
    public boolean isBeforeWriteAtSource() {
        return "B".equals(triggerTime) && "L".equals(triggerType);
    }

    /**
     * 是否是 交换后行级的触发器 源库
     *
     * @return
     */
    public boolean isAfterWriteAtSource() {
        return "A".equals(triggerTime) && "L".equals(triggerType);
    }

    /**
     * 是否是 失败后行级的触发器 源库
     *
     * @return
     */
    public boolean isWriteErrorAtSource() {
        return "E".equals(triggerTime) && "L".equals(triggerType);
    }

    public String getIsprocedure() {
        return isprocedure;
    }

    public void setIsprocedure(String isprocedure) {
        this.isprocedure = isprocedure;
    }
    // Constructors

    /**
     * default constructor
     */
    public ExportTrigger() {
    }

    /**
     * minimal constructor
     */
    public ExportTrigger(ExportTriggerId id

    ) {
        this.cid = id;


    }

    /**
     * full constructor
     */
    public ExportTrigger(ExportTriggerId id
            , String triggerSql, String triggerDesc, String triggerType,
                         String triggerTime, Long tiggerOrder, String isprocedure) {
        this.cid = id;


        this.triggerSql = triggerSql;
        this.triggerDesc = triggerDesc;
        this.triggerType = triggerType;
        this.triggerTime = triggerTime;

        this.tiggerOrder = tiggerOrder;
        this.isprocedure = isprocedure;
    }

    public ExportTriggerId getCid() {
        return this.cid;
    }

    public void setCid(ExportTriggerId id) {
        this.cid = id;
    }

    public Long getTriggerId() {
        if (this.cid == null)
            this.cid = new ExportTriggerId();
        return this.cid.getTriggerId();
    }

    public void setTriggerId(Long triggerId) {
        if (this.cid == null)
            this.cid = new ExportTriggerId();
        this.cid.setTriggerId(triggerId);
    }

    public Long getExportId() {
        if (this.cid == null)
            this.cid = new ExportTriggerId();
        return this.cid.getExportId();
    }

    public void setExportId(Long exportId) {
        if (this.cid == null)
            this.cid = new ExportTriggerId();
        this.cid.setExportId(exportId);
    }


    // Property accessors

    public String getTriggerSql() {
        return this.triggerSql;
    }

    public void setTriggerSql(String triggerSql) {
        this.triggerSql = triggerSql;
    }

    public String getTriggerDesc() {
        return this.triggerDesc;
    }

    public void setTriggerDesc(String triggerDesc) {
        this.triggerDesc = triggerDesc;
    }

    public String getTriggerType() {
        return this.triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public String getTriggerTime() {
        return this.triggerTime;
    }

    public void setTriggerTime(String triggerTime) {
        this.triggerTime = triggerTime;
    }


    public Long getTiggerOrder() {
        return this.tiggerOrder;
    }

    public void setTiggerOrder(Long tiggerOrder) {
        this.tiggerOrder = tiggerOrder;
    }


    public void copy(ExportTrigger other) {

        this.setTriggerId(other.getTriggerId());
        this.setExportId(other.getExportId());

        this.triggerSql = other.getTriggerSql();
        this.triggerDesc = other.getTriggerDesc();
        this.triggerType = other.getTriggerType();
        this.triggerTime = other.getTriggerTime();
        this.tiggerOrder = other.getTiggerOrder();
        this.isprocedure = other.getIsprocedure();

    }

    public void copyNotNullProperty(ExportTrigger other) {

        if (other.getTriggerId() != null)
            this.setTriggerId(other.getTriggerId());
        if (other.getExportId() != null)
            this.setExportId(other.getExportId());

        if (other.getTriggerSql() != null)
            this.triggerSql = other.getTriggerSql();
        if (other.getTriggerDesc() != null)
            this.triggerDesc = other.getTriggerDesc();
        if (other.getTriggerType() != null)
            this.triggerType = other.getTriggerType();
        if (other.getTriggerTime() != null)
            this.triggerTime = other.getTriggerTime();

        if (other.getTiggerOrder() != null)
            this.tiggerOrder = other.getTiggerOrder();
        if (other.getIsprocedure() != null)
            this.isprocedure = other.getIsprocedure();

    }

    public void clearProperties() {

        this.triggerSql = null;
        this.triggerDesc = null;
        this.triggerType = null;
        this.triggerTime = null;
        this.tiggerOrder = null;
        this.isprocedure = null;

    }
}
