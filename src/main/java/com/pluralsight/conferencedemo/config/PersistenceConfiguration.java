package com.pluralsight.conferencedemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {

//    @Value("${DB_URL}")
//    private String url;
//
//    @Value("${DB_USERNAME}")
//    private String username;
//
//    @Value("${DB_PASSWORD}")
//    private String password;

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:postgresql://localhost:5432/conference_app");
        builder.username("postgres");
        builder.password("Welcome");
//        builder.url(url);
//        builder.username(username);
//        builder.password(password);
        System.out.println("Custom DB URL wired...");
        return builder.build();
    }

}
