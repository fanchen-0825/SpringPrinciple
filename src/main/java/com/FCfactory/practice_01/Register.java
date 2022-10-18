package com.FCfactory.practice_01;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 用户注册
 */
@Component
@Slf4j
@Data
@AllArgsConstructor
public class Register {

    private ApplicationEventPublisher context;



    public void registe() {
        log.info("用户注册");
        context.publishEvent(new UserRegisteredEvent(this));
    }

}
