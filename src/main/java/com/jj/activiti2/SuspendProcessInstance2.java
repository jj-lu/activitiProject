package com.jj.activiti2;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 单个流程实例的挂起与激活
 * 当被挂起不能执行接下的任务，不能再启动该流程
 */
public class SuspendProcessInstance2 {
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //获取运行service
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //查询符合的流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey("1001")
                .singleResult();

        //判断
        if (processInstance.isSuspended()){
            //激活这个流程实例
            runtimeService.activateProcessInstanceById(processInstance.getId());
            System.out.println("流程实例："+processInstance.getId()+"激活");
        }else {
            //挂起这个流程实例
            runtimeService.suspendProcessInstanceById(processInstance.getId());
            System.out.println("流程实例："+processInstance.getId()+"挂起");
        }
    }
}

/**
 * 测试在挂起状态下流程能不能运行
 * 抛出错误
 */
class testSuspend{
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("zhangsan")
                .list();

        list.forEach(task -> {
            taskService.complete(task.getId());
            System.out.println(task.getName()+"已完成");
        });
    }
}
