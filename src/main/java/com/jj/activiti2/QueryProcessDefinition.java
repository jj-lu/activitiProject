package com.jj.activiti2;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;

/**
 * 查询流程定义
 */
public class QueryProcessDefinition {
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //获取ProcessDefinitionQuery对象，相当于一个查询器
        ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
        //设置条件，并查询出当前的所有定义流程
        List<ProcessDefinition> list = definitionQuery.processDefinitionKey("holiday")
                .orderByProcessDefinitionVersion()
                .desc()
                .list();

        list.forEach(processDefinition -> {
            System.out.println("流程定义的ID："+processDefinition.getId());
            System.out.println("流程定义的名称："+processDefinition.getName());
            System.out.println("流程定义的key："+processDefinition.getKey());
            System.out.println("流程定义的版本号："+processDefinition.getVersion());
            System.out.println("部署的ID："+processDefinition.getDeploymentId());
        });
    }
}
