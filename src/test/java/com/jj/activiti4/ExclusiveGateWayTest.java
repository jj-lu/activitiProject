package com.jj.activiti4;

import com.jj.activiti3.Holiday;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ExclusiveGateWayTest {

    /**
     * 部署流程定义
     */
    @Test
    public void test1(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday6.bpmn")
                .name("排他网关")
                .deploy();

        System.out.println(deploy.getKey());
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    /**
     * 启动流程实例
     */
    @Test
    public void test2(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String,Object> map = new HashMap<>();
        Holiday holiday = new Holiday();
        holiday.setNum(5F);
        map.put("holiday",holiday);

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("holiday6", map);

        System.out.println(instance.getProcessDefinitionId());
        System.out.println(instance.getProcessDefinitionKey());
        System.out.println(instance.getName());
        System.out.println(instance.getId());
    }

    /**
     * 加入排他网关后，当路径都符合也只能走其中一条
     */
    @Test
    public void test3(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .taskAssignee("lisi")
                .processDefinitionKey("holiday6")
                .singleResult();

        if (task != null){
            taskService.complete(task.getId());
            System.out.println(task.getName()+"：已完成");
        }
    }
}
