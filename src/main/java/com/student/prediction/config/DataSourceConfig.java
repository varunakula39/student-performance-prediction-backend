package com.student.prediction.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url:jdbc:mysql://localhost:3306/student_performance_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC}")
    private String mysqlUrl;

    @Value("${spring.datasource.username:root}")
    private String mysqlUsername;

    @Value("${spring.datasource.password:root}")
    private String mysqlPassword;

    @Bean
    @Primary
    public DataSource dataSource() {
        System.out.println("[DataSourceConfig] Verifying MySQL connection at: " + mysqlUrl);
        boolean mysqlConnected = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(mysqlUrl, mysqlUsername, mysqlPassword)) {
                mysqlConnected = true;
                System.out.println("[DataSourceConfig] Successfully connected to MySQL database!");
            }
        } catch (Exception e) {
            System.out.println("[DataSourceConfig] MySQL initial test note: " + e.getMessage());
        }

        if (mysqlConnected) {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(mysqlUrl);
            config.setUsername(mysqlUsername);
            config.setPassword(mysqlPassword);
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            return new HikariDataSource(config);
        } else {
            System.out.println("===============================================================================");
            System.out.println(" [EduPredict AI] Seamless Resilience Activated:");
            System.out.println(" MySQL connection with root/" + mysqlPassword + " was not established.");
            System.out.println(" Activating local persistent embedded database so your project runs flawlessly!");
            System.out.println(" To connect to your MySQL instance, set DB_PASSWORD in application.properties");
            System.out.println(" or set the environment variable: DB_PASSWORD=your_actual_password");
            System.out.println("===============================================================================");
            HikariConfig h2Config = new HikariConfig();
            h2Config.setJdbcUrl("jdbc:h2:file:./data/student_performance_db;MODE=MySQL;DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE");
            h2Config.setDriverClassName("org.h2.Driver");
            h2Config.setUsername("sa");
            h2Config.setPassword("");
            return new HikariDataSource(h2Config);
        }
    }
}
