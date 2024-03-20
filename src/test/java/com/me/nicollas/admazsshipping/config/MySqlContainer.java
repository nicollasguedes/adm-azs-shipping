package com.me.nicollas.admazsshipping.config;

import com.me.nicollas.admazsshipping.AdmAzsShippingApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@Configuration
public class MySqlContainer {

    @Bean
    @ServiceConnection
    @RestartScope
    MySQLContainer<?> mySQLContainer() {
        return new MySQLContainer<>(DockerImageName.parse("mysql:8"));
    }

    public static void main(String[] args) {
        SpringApplication.from(AdmAzsShippingApplication::main)
                .with(MySqlContainer.class)
                .run(args);
    }
}
  