package com.jj.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 启动流程实例：先部署后启动
 * act_hi_actinst:已经执行完和正在要做的流程，当流程还没有开始工作，结束时间不存在
 * act_hi_identitylink:记录流程的参与者
 * act_hi_procinst:流程实例
 * act_hi_taskinst:任务表，记录哪个人执行什么任务，开始时间和完成时间
 * act_ru_execution:记录流程实例，执行表
 * act_ru_identitylik:记录当前流程的参与者
 * act_ru_task:记录当前人执行的任务
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
