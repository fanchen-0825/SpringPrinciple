package com.FCfactory.practice_01;

import lombok.AllArgsConstructor;
import lombok.Data;
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
public class Register2 {

    public void registe() {
        log.info("用户注册");
    }

}
