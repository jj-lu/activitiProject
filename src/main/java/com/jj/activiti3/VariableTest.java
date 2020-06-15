package com.jj.activiti3;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

public class VariableTest {

    /**
     * 流程部署——
     * @param args
     */
    public static void main1(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday4.bpmn")
                .addClasspathResource("diagram/holiday4.png")
                .name("请假流程-流程变量")
                .deploy();

        System.out.println(deploy.getKey());
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myProcess_1")
                .singleResult();

        System.out.println(singleResult.getDeploymentId());
        System.out.println(singleResult.getKey());
    }
}
