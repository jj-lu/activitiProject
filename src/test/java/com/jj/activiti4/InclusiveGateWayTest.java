package com.jj.activiti4;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 *测试包含网关：
 *      特点：具有排他网关和并行网关的一些共同点
 *          可以设置流程变量，当流程变量取值都成立时，此时若干个分支都可以挂靠
 */
public class InclusiveGateWayTest {


    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    /**
     *部署流程
     */
    @Test
    public void test1(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/examine.bpmn")
                .name("体检流程")
                .deploy();

        System.out.println(deploy.getName());
        System.out.println(deploy.getId());
    }

    /**
     *启动流程
     */
    @Test
    public void test2(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String,Object> map = new HashMap<>();
        Integer userType = 2;
        map.put("userType",userType);

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("examine", map);

        System.out.println("流程定义ID："+instance.getProcessDefinitionId());
        System.out.println("流程实例ID："+instance.getId());
    }

    /**
     * 任务执行
     */
    @Test
    public void test3(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .taskAssignee("xiaowang")
                .processDefinitionKey("examine")
                .singleResult();

        taskService.complete(task.getId());
        System.out.println(task.getId()+":"+task.getName()+":已完成");
    }

    /**
     * spring测试
     */
    @Test
    public void test22(){
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
    }
}
