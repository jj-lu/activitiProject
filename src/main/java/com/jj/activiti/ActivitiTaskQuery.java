package com.jj.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 *查询任务列表
 */
public class ActivitiTaskQuery {

    /**
     * 查询需要填写的请假单
     * @param args
     */
    /**
     * public static void main(String[] args){
     *         //创建ProcessEngine对象
     *         ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
     *
     *         //获取TaskService对象
     *         TaskService taskService = processEngine.getTaskService();
     *
     *         //调用createTaskQuery()获得任务列表
     *         List<Task> taskList = taskService.createTaskQuery()
     *                 .processDefinitionKey("holiday")    //流程名称
     *                 .taskAssignee("zhangsan")   //查询执行人
     *                 .list();
     *
     *         //遍历任务例表
     *         taskList.forEach(task->{
     *             System.out.println("任务负责人："+task.getAssignee());
     *             System.out.println("流程实例ID："+task.getProcessInstanceId());
     *             System.out.println("任务ID："+task.getId());//2505
     *             System.out.println("任务名称："+task.getName());
     *         });
     *
     *
     *
     *         System.out.println("success");
     *     }
     */

    /**
     * 查询部门经理需要的审批
     * @param args
     */
    /**
     * public static void main(String[] args){
     *         ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
     *         TaskService taskService = processEngine.getTaskService();
     *         List<Task> taskList = taskService.createTaskQuery()
     *                 .taskAssignee("lisi")
     *                 .list();
     *         taskList.forEach(task -> {
     *             System.out.println("任务负责人："+task.getAssignee());
     *             System.out.println("流程实例ID："+task.getProcessInstanceId());
     *             System.out.println("任务ID："+task.getId());//5002
     *             System.out.println("任务名称："+task.getName());
     *         });
     *         System.out.println("success");
     *     }
     */

    /**
     * 查询总经理审批
     * @param args
     */
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("wangwu")
                .list();
        list.forEach(task -> {
            System.out.println("任务负责人："+task.getAssignee());
            System.out.println("流程实例ID："+task.getProcessInstanceId());
            System.out.println("任务ID："+task.getId());//7002
            System.out.println("任务名称："+task.getName());
        });

        System.out.println("success");
    }
}
