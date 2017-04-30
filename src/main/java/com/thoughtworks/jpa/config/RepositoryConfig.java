package com.thoughtworks.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.thoughtworks.jpa.repository"})
public class RepositoryConfig {
}
