package com.FCfactory.practice_01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 利用事件方式完成用户注册与发送短信的解耦
 */
@Component
@Slf4j
public class Listener {

    @EventListener
    public void doWith(UserRegisteredEvent event){
        log.info("监听到事件：{}",event);
        log.info("发送短信");
    }
}
