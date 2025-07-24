package com.bank.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bank.controller.CustomerController;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class TerminateBean {

	@PreDestroy
    public void onDestroy() {
        log.info("Spring Container is destroyed!");
    }
    
    @Bean
    public TerminateBean getTerminateBean() {
        return new TerminateBean();
    }
}
