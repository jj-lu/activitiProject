package com.jj.activiti2;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * 全部流程实例的挂起与激活
 * 当被挂起不能执行接下的任务，不能再启动该流程
 */
public class SuspendProcessInstance {
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //查询器根据流程key查出该流程
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("holiday")
                .singleResult();

        //判断是否暂停
        if (processDefinition.isSuspended()){
            //暂停，可以激活
            repositoryService.activateProcessDefinitionById(processDefinition.getId(),true,null);
            System.out.println("流程："+processDefinition.getId()+"已被激活");
        }else {
            repositoryService.suspendProcessDefinitionById(processDefinition.getId(),true,null);
            System.out.println("流程："+processDefinition.getId()+"已被挂起");
        }
    }
}
