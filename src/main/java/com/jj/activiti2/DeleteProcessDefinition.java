package com.jj.activiti2;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;

/**
 * 删除流程定义
 * act_ge_bytearray:删除了bpmn和png
 * act_re_deployment
 * act_re_prodef
 * history的历史信息会被保存
 * 注意事项：
 * 正在执行的这一套流程没有完全审批结束的时候，如果删除流程定义就会失败
 * 强制删除可以使用repositoryService.deleteDeployment("1",true);
 * 参数true代表联级删除，此时就会先删除没有完成的流程结点，最后删除流程定义信息
 */
public class DeleteProcessDefinition {
    public static void main(String[] args){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //删除流程定义
        repositoryService.deleteDeployment("1");
        System.out.println("success");
    }
}
