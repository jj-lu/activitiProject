package com.jj.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类：测试activiti所需要的25张表生成
 */
public class ActivitiTest {

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;


    @Test
    public void testGenTable(){
        //创建ProcessEngineConfiguration对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //创建ProcessEngine对象
        ProcessEngine engine = configuration.buildProcessEngine();
        System.out.println("success");
    }

    /**
     * 更简单的方法。需要activiti配置文件名称为activiti.cfg.xml，bean的id为processEngineConfiguration
     */
    @Test
    public void testGenTable2(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println("success");
    }

    /**
     * test:SpringIOC
     */
    @Test
    public void testIOC(){
        System.out.println("testIOC:"+processEngineConfiguration);
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println(processEngine);
    }
}
