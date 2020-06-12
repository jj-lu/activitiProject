package com.jj.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * 完成任务
 * act_hi_actinst:更新了刚刚完成记录的结束时间，新增下一个任务的开始时间
 * act_hi_identitylink:新增了刚刚完成任务的参与者信息
 * act_hi_taskinst:任务实例，更新了刚刚完成记录的结束时间，新增下一个任务的开始时间
 * act_ru_execution:
 * act_ru_indetitylink:新增了参与者记录
 * act_ru_task:删除已完成任务节点，新增记录下一个任务节点
 */
public class ActivitiCompleteTask {
    /**
     * 填写请假单
     * @param args
     */
    /**
     *public static void main(String[] args){
     *         //创建processEngine对象
     *         ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
     *
     *         //获取taskService对象
     *         TaskService taskService = processEngine.getTaskService();
     *
     *         //调用complete()方法完成任务
     *         taskService.complete("2505");//上一步查询出来的任务ID
     *
     *         System.out.println("success");
     *     }
     */

    /**
     * 部门经理审批
     * @param args
     */
    /**
     * public static void main(String[] args){
     *         ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
     *         TaskService taskService = processEngine.getTaskService();
     *         taskService.complete("5002");
     *         System.out.println("success");
     *     }
     */

    /**
     * 查询总经理的审批并处理
     * act_ru_task:没有记录，整个流程已走完
     * act_ru_identity:没有记录
     * act_ru_taskinst:没有记录
     * @param args
     */
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        //taskService.complete("7002");
        Task task = taskService.createTaskQuery()
                .taskAssignee("zs")
                .singleResult();
        System.out.println("任务ID："+task.getId());
        taskService.complete(task.getId());
        System.out.println("success");
    }
}
