package com.jj.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

/**
 * 流程定义部署
 * act_re_deployment  部署信息
 * act_re_procdef   流程定义的信息
 * act_ge_bytearray     流程定义的bpmn文件和png文件
 */
public class ActivitiDeployment {

    /**
     * 单个文件的部署
     * @param args
     */
    public static void main(String[] args){
        //简洁的方式创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        //获得RepositoryService实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //进行流程部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday2.bpmn")  //添加bpmn资源
                .addClasspathResource("diagram/holiday2.png")   //添加png资源
                .name("请假申请单流程")                           //流程的名称
                .deploy();//部署方法
        //输出部署的信息
        System.out.println(deployment.getKey());
        System.out.println(deployment.getId());
    }

    /**
     * 压缩文件部署
     * @param args
     */
//    public static void main(String[] args){
//        //将压缩包转化为流对象
//        InputStream inputStream = ActivitiDeployment.class
//                .getClassLoader().getResourceAsStream("diagram/holidayBpmn.zip");
//        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
//
//        //创建ProcessEngine
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//
//        //获取RepositoryService
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//
//        //调用Deployment
//        Deployment deployment = repositoryService.createDeployment()
//                .addZipInputStream(zipInputStream)
//                .deploy();
//
//        System.out.println(deployment.getName());
//        System.out.println(deployment.getId());
//    }

}
