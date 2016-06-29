package com.centit.dde.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dde.po.MapinfoTrigger;

public class MapinfoTriggerDao extends BaseDaoImpl<MapinfoTrigger> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(MapinfoTriggerDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("cid.triggerId", CodeBook.EQUAL_HQL_ID);

            filterField.put("cid.mapinfoId", CodeBook.EQUAL_HQL_ID);


            filterField.put("triggerSql", CodeBook.LIKE_HQL_ID);

            filterField.put("triggerDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("triggerType", CodeBook.LIKE_HQL_ID);

            filterField.put("triggerTime", CodeBook.LIKE_HQL_ID);

            filterField.put("triggerDatabase", CodeBook.LIKE_HQL_ID);

            filterField.put("tiggerOrder", CodeBook.LIKE_HQL_ID);

            filterField.put("isprocedure", CodeBook.LIKE_HQL_ID);

        }
        return filterField;
    }

    public List<MapinfoTrigger> listTriggerByMapinfoId(Long mapinfoId) {
        StringBuffer sql = new StringBuffer();
        sql.append("from MapinfoTrigger t where  t.cid.mapinfoId=");
        sql.append(mapinfoId);
        sql.append(" order by t.triggerTime, t.tiggerOrder");
        return listObjects(sql.toString());
    }

    public Long getTriggerId() {
        return this.getNextLongSequence("D_TRIGGERID");
    }
}