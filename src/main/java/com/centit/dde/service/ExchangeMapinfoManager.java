package com.centit.dde.service;

import java.util.List;
import java.util.Map;

import com.centit.core.service.BaseEntityManager;
import com.centit.core.utils.PageDesc;
import com.centit.dde.exception.SqlResolveException;
import com.centit.dde.po.ExchangeMapinfo;
import com.centit.sys.security.FUserDetail;

public interface ExchangeMapinfoManager extends BaseEntityManager<ExchangeMapinfo> {
    public List<String> listDatabaseName();

    public List<ExchangeMapinfo> listImportExchangeMapinfo(List<Long> mapinfoId);

    /**
     * 验证Sql有效性
     *
     * @param object
     * @throws SqlResolveException
     */
    void validator(ExchangeMapinfo object) throws SqlResolveException;

    /**
     * 保存导出Sql语句时更新相关的查询字段信息
     *
     * @param object
     * @param userDetail
     * @throws SqlResolveException
     */
    void saveObject(ExchangeMapinfo object, FUserDetail userDetail) throws SqlResolveException;

    List<ExchangeMapinfo> listObjectExcludeUsed(Map<String, Object> filterMap, PageDesc pageDesc);
}