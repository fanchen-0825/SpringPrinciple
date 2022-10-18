package com.FCfactory.practice_01;

import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.beans.EventHandler;
import java.time.Clock;

/**
 * 使用AOP 完成用户注册与发送短信的解耦
 */
public class UserRegisteredEvent extends ApplicationEvent {
    public UserRegisteredEvent(Object source) {
        super(source);
    }

}
