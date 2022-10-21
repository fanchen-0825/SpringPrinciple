package com.FCfactory.practice_02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.annotation.Resource;

public class TestBeanFactory {

    public static void main(String[] args) {
        //构造BeanFactory的实现类 作为容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //先提供bean的定义（class，scope，初始化 销毁）  然后BeanFactory创建实例对象

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).
                setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config",beanDefinition);


        //与注解相关的工具类
        //registerAnnotationConfigProcessors
        // 给BeanFactory添加常用的BeanFactory后处理器（原本的BeanFactory不具有解析部分注解的功能）
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        //现在BeanFactory只是有了后处理器 但是还没有调用调


        //获得并调用BeanFactory后处理器
        //根据类型获得多个bean
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor -> {
            //给BeanFactory调用BeanFactory后处理器
            //BeanFactory后处理器给BeanFactory补充了bean定义
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        });

        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }



        //获得并给BeanFactory添加Bean后处理器（建立BeanFactory与后处理器的联系）
        //Bean后处理器主要对Bean的生命周期各个阶段提供扩展 例如 @Autowired  @Resource...
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);

        System.out.println(beanFactory.getBean(Bean1.class).getBean2());
        //第一次进行bean2的获取 结果是是null 因为此时没有添加Bean后处理器
        //第二次进行bean2的获取 结果是com.FCfactory.practice_02.TestBeanFactory$Bean2@7c7b252e 说明此时Bean后处理器生效

        //BeanFactory默认是延迟进行单例对象的实例化  我们可以调用方法让BeanFactory进行提前对单例对象的实例化


    }

    /**
     * BeanFactory不会主动实现调用BeanFactory后处理器
     * BeanFactory不会主动调用Bean后处理器
     * BeanFactory不会主动初始化单例bean
     * BeanFactory不能解析表达式 ${} #{}
     * 综上所述 BeanFactory的功能非常少 需要手动实现
     */

    @Configuration
    static class Config {
        @Bean
        public Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2();
        }

    }


    static class Bean1 {
        private static final Logger log = LoggerFactory.getLogger(Bean1.class);

        public Bean1() {
            log.debug("构造 Bean1()");
        }

        @Autowired
        private Bean2 bean2;

        public Bean2 getBean2() {
            return bean2;
        }

    }

    static class Bean2 {
        private static final Logger log = LoggerFactory.getLogger(Bean2.class);

        public Bean2() {
            log.debug("构造 Bean2()");
        }
    }
}
