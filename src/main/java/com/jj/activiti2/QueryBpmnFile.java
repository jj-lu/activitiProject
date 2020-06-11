package com.jj.activiti2;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * 读取资源文件保存到路径下
 */
public class QueryBpmnFile {

    public static void main(String[] args) throws IOException {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition holiday = definitionQuery.processDefinitionKey("holiday")
                .singleResult();
        String deploymentId = holiday.getDeploymentId();

        //读取图片信息及bpmn文件信息(输入流) 第一个参数是：部署id，第二个参数是：参数代表资源名称
        InputStream pngIS = repositoryService.getResourceAsStream(deploymentId, holiday.getDiagramResourceName());
        InputStream bpmnIS = repositoryService.getResourceAsStream(deploymentId, holiday.getResourceName());

        //构建出outputstream输出流
        OutputStream pngOS = new FileOutputStream("D:\\" + holiday.getDiagramResourceName());
        OutputStream bpmnOS = new FileOutputStream("D:\\" + holiday.getResourceName());

        //输入输出流的转换
        IOUtils.copy(pngIS,pngOS);
        IOUtils.copy(bpmnIS,bpmnOS);

        //关闭IO流
        pngIS.close();
        pngOS.close();
        bpmnIS.close();
        bpmnOS.close();
    }
}
