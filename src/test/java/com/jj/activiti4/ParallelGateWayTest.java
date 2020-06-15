package com.jj.activiti4;

import com.jj.activiti3.Holiday;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 并行网关
 */
public class ParallelGateWayTest {

    /**
     * 部署
     */
    @Test
    public void test1(){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine = configuration.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday7.bpmn")
                .name("并行网关请假单流程")
                .deploy();

        System.out.println(deploy.getName());
        System.out.println(deploy.getId());
        System.out.println(deploy.getKey());
    }

    /**
     * 启动实例
     */
    @Test
    public void test2(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Holiday holiday = new Holiday();
        holiday.setNum(1F);

        Map<String,Object> map = new HashMap<>();
        map.put("holiday",holiday);

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("holiday7");
        if (instance != null){
            System.out.println(instance.getName());
            System.out.println(instance.getId());//77501
            System.out.println(instance.getProcessDefinitionKey());//holiday7
        }
    }

    /**
     *角色执行任务
     */
    @Test
    public void test3(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday7")
                .taskAssignee("xiaowu")
                .singleResult();

        taskService.complete(task.getId());
        System.out.println(task.getId()+":"+task.getName());
//        Holiday holiday = new Holiday();
//        holiday.setNum(1F);
//        taskService.setVariable(task.getId(),"holiday",holiday);
    }
}

