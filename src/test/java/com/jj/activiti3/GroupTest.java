package com.jj.activiti3;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * 候选用户
 */
public class GroupTest {

    /**
     *部署流程
     */
    @Test
    public void test1(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday5.bpmn")
                .name("有候选人的请假单流程")
                .deploy();

        System.out.println(deploy.getName());
        System.out.println(deploy.getId());
        System.out.println(deploy.getKey());
    }

    /**
     * 开始流程
     */
    @Test
    public void test2(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance holiday5 = runtimeService.startProcessInstanceByKey("holiday5");
        System.out.println("流程部署id："+holiday5.getDeploymentId());
        System.out.println("流程定义id："+holiday5.getProcessDefinitionId());
        System.out.println("流程实例id："+holiday5.getId());
    }

    /**
     * 提交请假单任务
     */
    @Test
    public void test3(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task singleResult = taskService.createTaskQuery()
                .taskAssignee("xiaozhang")
                .processDefinitionKey("holiday5")
                .singleResult();

        if (singleResult != null){
            taskService.complete(singleResult.getId());
            System.out.println("提交了请假单");
        }
    }


    /**
     * 查询候选用户的组任务
     */
    @Test
    public void test4(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("holiday5")
                .taskCandidateUser("zhangsan")
                .list();

        list.forEach(task -> {
            System.out.println(task.getProcessInstanceId());
            System.out.println(task.getId());
            System.out.println(task.getName());
            System.out.println(task.getAssignee());//null
        });
    }

    /**
     * 拾取任务
     */
    @Test
    public void test5(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday5")
                .taskCandidateUser("zhangsan")
                .singleResult();

        if (task != null){
            taskService.claim(task.getId(),"lisi");//第一个参数为任务ID，第二个参数为具体的候选用户
            System.out.println("拾取成功");
        }

        System.out.println(task.getAssignee());
    }

    /**
     * 拾取任务后完成任务
     */
    @Test
    public void test6(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .taskAssignee("zhangsan")
                .processDefinitionKey("holiday5")
                .singleResult();

        if (task != null){
            System.out.println(task.getAssignee());
            System.out.println(task.getId());
            taskService.complete(task.getId());
            System.out.println("完成任务："+task.getId());
        }
    }

    /**
     * 任务交接或归还任务
     */
    @Test
    public void test7(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday5")
                .taskAssignee("555")
                .singleResult();

        if (task != null){
            System.out.println(task.getName());

            //归还任务
            //taskService.setAssignee(task.getId(),null);

            //移交任务
            taskService.setAssignee(task.getId(),"ls");//可以移交到不在候选组中的人
        }

        System.out.println("success");
    }
}
