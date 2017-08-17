// package com.happyship.db;
//
// import java.sql.SQLException;
//
// import javax.sql.DataSource;
//
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.jdbc.core.JdbcTemplate;
//
// import com.zaxxer.hikari.HikariConfig;
// import com.zaxxer.hikari.HikariDataSource;
//
// @Configuration
// @ConfigurationProperties(prefix = "params.datasource")
// public class DataBaseConfig extends HikariConfig {
//
// @Bean
// public DataSource dataSource() throws SQLException {
// return new HikariDataSource(this);
// }
//
// @Bean
// public JdbcTemplate jdbcTemplate() throws SQLException {
// return new JdbcTemplate(dataSource());
// }
// }
