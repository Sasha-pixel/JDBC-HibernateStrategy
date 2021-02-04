package com.example.DBstrategy.Strategies.jdbc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
public class Connector {
    private String url = "jdbc:postgresql://10.10.10.142:5432/backtosch";
    private String username = "kanenkovaa";
    private String password = "Tjed_913";

    @Bean
    public Statement connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url,username, password);
        Statement statement = connection.createStatement();
        return statement;
    }
}
