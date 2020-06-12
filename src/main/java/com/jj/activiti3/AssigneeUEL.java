package com.jj.activiti3;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class AssigneeUEL {
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        //设置assignee值
        Map<String,Object> map = new HashMap<>();
        map.put("assignee0","zs");
        map.put("assignee1","ls");
        map.put("assignee2","ww");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday2", map);

        System.out.println(processEngine.getName());
    }
}
