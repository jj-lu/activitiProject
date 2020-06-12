package com.jj.activiti2;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动流程实例，添加进businessKey
 */
public class BusinessKeyAdd {
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //获取RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //启动流程实例，同时还要指定业务标识businessKey，请假单的id;第一个参数为流程定义key，第二个参数为businessKey
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday","1001");
        System.out.println(processInstance.getBusinessKey());

    }
}
