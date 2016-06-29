package com.centit.dde.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.centit.core.dao.BaseDaoImpl;
import com.centit.core.dao.CodeBook;
import com.centit.dde.po.DatabaseInfo;
import com.centit.dde.po.ExchangeTask;
import com.centit.support.utils.StringBaseOpt;

public class ExchangeTaskDao extends BaseDaoImpl<ExchangeTask> {
    private static final long serialVersionUID = 1L;
    public static final Log log = LogFactory.getLog(ExchangeTaskDao.class);

    public Map<String, String> getFilterField() {
        if (filterField == null) {
            filterField = new HashMap<String, String>();

            filterField.put("taskId", CodeBook.EQUAL_HQL_ID);


            filterField.put("taskName", CodeBook.LIKE_HQL_ID);
            filterField.put("taskNameEq", "taskName = ?");

            filterField.put("taskType", CodeBook.LIKE_HQL_ID);

            filterField.put("taskCron", CodeBook.LIKE_HQL_ID);

            filterField.put("taskDesc", CodeBook.LIKE_HQL_ID);

            filterField.put("lastRunTime", CodeBook.LIKE_HQL_ID);

            filterField.put("nextRunTime", CodeBook.LIKE_HQL_ID);

            filterField.put("isvalid", CodeBook.EQUAL_HQL_ID);

            filterField.put("createTime", CodeBook.LIKE_HQL_ID);

            filterField.put("created", CodeBook.LIKE_HQL_ID);

            filterField.put(CodeBook.ORDER_BY_HQL_ID, "createTime desc");

            filterField.put("taskType", CodeBook.EQUAL_HQL_ID);
        }
        return filterField;
    }

    /*public List<Object> getSqlValues(String sql){
        return this.getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(sql).list();
    }
    */
    public List<List<Object>> getSqlValues(DatabaseInfo DatabaseInfo, String sql) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;

        List<List<Object>> datas = new ArrayList<List<Object>>();
        if (DatabaseInfo.getDatabaseType().equals("1")) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (DatabaseInfo.getDatabaseType().equals("2")) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            conn = DriverManager.getConnection(DatabaseInfo.getDatabaseUrl(), DatabaseInfo.getUsername(), StringBaseOpt.decryptBase64Des(DatabaseInfo.getPassword()));
            pstmt = conn.prepareStatement(sql);
            rsmd = pstmt.getMetaData();
            rs = pstmt.executeQuery();
            int columnCount = rsmd.getColumnCount();

            // 循环结果集
            while (rs.next()) {
                List<Object> dataTemp = new ArrayList<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    dataTemp.add(rs.getObject(i));
                }
                datas.add(dataTemp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return datas;
    }

    public List<Object> insertDatas(DatabaseInfo DatabaseInfo, String sql, List<List<Object>> datas) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String errorMessage = "成功添加记录";
        int correctCount = 0;
        List<Object> logs = new ArrayList<Object>();
        List<Object> logTemp = new ArrayList<Object>();

        if (DatabaseInfo.getDatabaseType().equals("1")) {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (DatabaseInfo.getDatabaseType().equals("2")) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        try {
            conn = DriverManager.getConnection(DatabaseInfo.getDatabaseUrl(), DatabaseInfo.getUsername(), StringBaseOpt.decryptBase64Des(DatabaseInfo.getPassword()));
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        logs.add(0, new Date());
        for (int i = 0; i < datas.size(); i++) {
            List<Object> log = new ArrayList<Object>();
            try {
                List<Object> data = datas.get(i);
                for (int j = 1; j <= data.size(); j++) {
                    pstmt.setObject(j, data.get(j - 1));
                }
                pstmt.executeUpdate();
                correctCount++;

            } catch (SQLException e) {
                errorMessage = e.getMessage();
                log.add(0, errorMessage);
                log.add(1, datas.get(i));
            }
            logTemp.add(log);
        }
        logs.add(1, new Date());
        logs.add(2, correctCount);
        logs.add(3, datas.size() - correctCount);
        logs.add(4, logTemp);
        try {
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    public String getMapinfoName(Long mapinfoId) {
        String hql = "select t.mapinfoName from ExchangeMapinfo t where t.mapinfoId=" + mapinfoId;
        Object o = this.getSingleObjectByHql(hql);
        if (o == null)
            return "";
        return o.toString();

    }

    public Long getNewTaskId() {
        return this.getNextLongSequence("D_TASK_ID");
    }


}