package com.jj.activiti2;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;

import java.util.List;

public class HistoryQuery {
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();

        //创建HistoricActivityInstanceQuery查询器
        HistoricActivityInstanceQuery query = historyService.createHistoricActivityInstanceQuery();

        //按条件查询
        List<HistoricActivityInstance> instanceList = query.processInstanceId("2501")
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();

        //遍历输出
        instanceList.forEach(historicActivityInstance -> {
            System.out.println(historicActivityInstance.getActivityId());
            System.out.println(historicActivityInstance.getActivityName());
            System.out.println(historicActivityInstance.getProcessDefinitionId());
            System.out.println(historicActivityInstance.getProcessInstanceId());
            System.out.println("=============================================");
        });
    }
}
