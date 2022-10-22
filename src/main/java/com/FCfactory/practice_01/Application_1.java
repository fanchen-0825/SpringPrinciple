package com.FCfactory.practice_01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@Slf4j
@EnableAspectJAutoProxy
public class Application_1 {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application_1.class, args);
        log.info("项目启动成功");
//        System.out.println(context.getEnvironment().getProperty("java_home"));


        //context.getBean(Register.class).registe();
        context.getBean(Register2.class).registe();

    }

}
