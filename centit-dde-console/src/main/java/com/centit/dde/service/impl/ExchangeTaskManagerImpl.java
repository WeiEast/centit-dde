package com.centit.dde.service.impl;

import com.centit.dde.dao.ExchangeTaskDao;
import com.centit.dde.dataio.ExportData;
import com.centit.dde.dataio.ImportData;
import com.centit.dde.po.ExchangeTask;
import com.centit.dde.service.ExchangeTaskManager;
import com.centit.dde.transfer.TransferManager;
import com.centit.framework.hibernate.service.BaseEntityManagerImpl;
import com.centit.framework.security.model.CentitUserDetails;
import com.centit.framework.staticsystem.po.DatabaseInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;

@Service
public class ExchangeTaskManagerImpl
        extends BaseEntityManagerImpl<ExchangeTask,Long,ExchangeTaskDao>
        implements ExchangeTaskManager, org.quartz.Job {

    public static final Log logger = LogFactory.getLog(ExchangeTaskManager.class);

    private static final String EXCHANGE_TASK_ID = "EXCHANGE_TASK_ID";

    private static final String JOB_NAME_PREFIX = "Job_ExchangeTask_";

    private static final String JOB_GROUP_EXCHANGETASK = "JOB_GROUP_EXCHANGETASK";
    
    private ExchangeTaskDao exchangeTaskDao;

    private TransferManager transferManager;

    private ExportData exportData;

    private ImportData importData;

    
    public void setImportData(ImportData importData) {
        this.importData = importData;
    }
    
    public void setExportData(ExportData exportData) {
        this.exportData = exportData;
    }

    public void setTransferManager(TransferManager transferManager) {
        this.transferManager = transferManager;
    }

    @Resource(name = "exchangeTaskDao")
    @NotNull
    public void setExchangeTaskDao(ExchangeTaskDao baseDao) {
        this.exchangeTaskDao = baseDao;
        setBaseDao(this.exchangeTaskDao);
    }

    public List<List<Object>> getSqlValues(DatabaseInfo databaseInfo, String sql) {
        return this.exchangeTaskDao.getSqlValues(databaseInfo, sql);
    }

    public List<Object> insertDatas(DatabaseInfo databaseInfo, String sql, List<List<Object>> datas) {
        return this.exchangeTaskDao.insertDatas(databaseInfo, sql, datas);
    }

    public String getMapinfoName(Long mapinfoId) {
        return this.exchangeTaskDao.getMapinfoName(mapinfoId);
    }

    public void initTimerTask() {
        // 初始化时加载所有任务
        List<ExchangeTask> exchangeTasks = exchangeTaskDao.listObjects();
        for (ExchangeTask exchangeTask : exchangeTasks) {
            if (exchangeTask.getTaskCron() != null) {
                try {
                    saveNewTimerTask(exchangeTask);
                } catch (Exception e) {
                    logger.error(exchangeTask.getTaskName() + " 表达式错误", e);
                }
            }
        }
    }

    @Override
    public Long getNewTaskId() {
        return this.exchangeTaskDao.getNewTaskId();
    }


    public void execute(JobExecutionContext jobexecutioncontext) {

        Long taskId = jobexecutioncontext.getJobDetail().getJobDataMap().getLong(EXCHANGE_TASK_ID);

        // 判断启用禁用
        ExchangeTask object = getObjectById(taskId);
        if ("0".equals(object.getIsvalid())) {
            return;
        }


        //更新下次执行时间
        CronSequenceGenerator generator = new CronSequenceGenerator(object.getTaskCron(), TimeZone.getDefault());
        object.setNextRunTime(generator.next(new Date()));
        exchangeTaskDao.saveObject(object);

        //执行调试
        executeTask(object,"System","0");
    }

    public boolean executeTask(ExchangeTask exchangeTask,String userCode,String runType) {
        try {
            if ("1".equals(exchangeTask.getTaskType())) {
                transferManager.runTransferTask(exchangeTask.getTaskId(), userCode, runType,"1");
            } else if ("2".equals(exchangeTask.getTaskType())) {
                exportData.runExportTask(exchangeTask.getTaskId(), userCode, runType,"2");
            } else if ("3".equals(exchangeTask.getTaskType())) {
                importData.runImportTask(exchangeTask.getTaskId(), userCode, runType);
            }
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }
    
    public boolean executeTask(Long taskID,String userCode,String runType) {
        ExchangeTask object = getObjectById(taskID);
        if ("0".equals(object.getIsvalid())) {
            return false;
        }
        return executeTask(object,userCode,runType);
    }
    

    @Override
    public void saveNewTimerTask(ExchangeTask exchangeTask) {
        //JobDetail jobDetail = schedulerManager.getJobDetail(JOB_NAME_PREFIX + exchangeTask.getTaskId(),
        //        JOB_GROUP_EXCHANGETASK);
        // 设置回调
        //jobDetail.getJobDataMap().put(QuartzJobBean.QUARTZ_JOB_BEAN_KEY, this);

        //jobDetail.getJobDataMap().put(EXCHANGE_TASK_ID, exchangeTask.getTaskId());
        //schedulerManager.schedule(jobDetail, "Trigger_ExchangeTask_" + exchangeTask.getTaskId(),
        //       JOB_GROUP_EXCHANGETASK, exchangeTask.getTaskCron());

    }

    @Override
    public boolean delTimerTask(ExchangeTask exchangeTask) {
        return false;
        //return schedulerManager.deleteJob(JOB_NAME_PREFIX + exchangeTask.getTaskId(), JOB_GROUP_EXCHANGETASK);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateTimerTask(ExchangeTask exchangeTask) {
        delTimerTask(exchangeTask);
        saveNewTimerTask(exchangeTask);
        return true;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(ExchangeTask object, CentitUserDetails user) {
        Long taskId = object.getTaskId();
        object.setTaskName(object.getTaskName().trim());
        ExchangeTask dbObject = getObjectById(taskId);
        if (dbObject != null) {
            dbObject.copyNotNullProperty(object);
            object = dbObject;
        }

        // 判断是否为新增
        if (null == object.getTaskId() || 0 == object.getTaskId()) {
            Map<String, Object> filterMap = new HashMap<>();
            filterMap.put("taskNameEq", object.getTaskName());
            List<ExchangeTask> listObjects = listObjects(filterMap);
            if (!org.springframework.util.CollectionUtils.isEmpty(listObjects)) {
            }


            object.setTaskId(getNewTaskId()); // Long.toString(System.currentTimeMillis()));
            object.setCreateTime(new Date());
            object.setIsvalid("1");
            if (user != null) {
                object.setCreated(user.getUserCode());
                object.setCreatedName(user.getUserName());
            }
//                saveMessage("添加交换任务成功！");
        } else {
//                saveMessage("编辑交换任务成功！");
        }
        if (object.getTaskCron() != null) {
            saveNewTimerTask(object);
        }

        //更新下次执行时间
        if (object.getTaskCron() != null) {
            try {
                CronSequenceGenerator generator = new CronSequenceGenerator(object.getTaskCron(), TimeZone.getDefault());
                object.setNextRunTime(generator.next(new Date()));
            } catch (Exception e) {
//                    saveError("定时任务表达式不正确");

//                    return ERROR;
                return;
            }
        }

        saveObject(object);
    }
}
