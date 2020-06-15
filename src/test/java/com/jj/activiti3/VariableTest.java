package com.jj.activiti3;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 启动流程时设置变量
 */
public class VariableTest {

    /**
     * holiday4的流程部署
     */
    @Test
    public void test1(){
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
     * 验证流程是否部署
     */
    @Test
    public void test2(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition singleResult = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myProcess_1")
                .singleResult();

        System.out.println(singleResult.getKey());
        System.out.println(singleResult.getDeploymentId());
    }



    /**
     *启动流程实例，设置流程变量的值,请假天数为1
     * act_ge_bytearray
     * act_ru_variable
     */
    @Test
    public void test3(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        Map<String,Object> map = new HashMap<>();

        Holiday holiday = new Holiday();
        holiday.setNum(5F);
        map.put("holiday",holiday);

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("myProcess_1", map);

        System.out.println(instance.getName());
        System.out.println(instance.getProcessDefinitionKey());
    }


    /**
     * 完成任务
     * 请假为一天：zhangsan--lisi--人事存档
     * 请假为五天：zhangsan--lisi--总经理审批--人事存档
     */
    @Test
    public void test4(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task singleResult = taskService.createTaskQuery()
                .taskAssignee("zhaoliu")
                .processDefinitionKey("myProcess_1")
                .singleResult();

        if (singleResult != null){
            taskService.complete(singleResult.getId());
            System.out.println("任务执行完毕");
        }
    }

}
