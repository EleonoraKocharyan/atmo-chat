package com.common.atmochat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class})
@Configuration
@ComponentScan
//@EnableWebSocket
public class AtmoChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtmoChatApplication.class, args);
	}
}
