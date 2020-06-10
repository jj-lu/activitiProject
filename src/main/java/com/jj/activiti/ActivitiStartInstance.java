package com.jj.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动流程实例：先部署后启动
 */
public class ActivitiStartInstance {

    public static void main(String[] args){

        //创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //获得RuntimeService对象
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //调用流程开始实例方法startProcessInstanceByKey()
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday");

        System.out.println("流程 部署id："+processInstance.getDeploymentId());
        System.out.println("流程 实例id："+processInstance.getId());
    }
}
