package com.jj.activiti3;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

public class VariableTest {

    @Autowired
    private static ProcessEngineConfiguration processEngineConfiguration;

    /**
     * test:springIOC
     */
    public static void main(String[] args){
        System.out.println(processEngineConfiguration);//null
        String history = processEngineConfiguration.getHistory();//exception
    }

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
    public static void main2(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myProcess_1")
                .singleResult();

        System.out.println(singleResult.getDeploymentId());
        System.out.println(singleResult.getKey());
    }
}
