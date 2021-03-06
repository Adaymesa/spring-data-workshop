package com.thoughtworks.jpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.thoughtworks.jpa.controller"})
public class ControllerConfig {
}
