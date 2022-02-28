package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@RestController
@Slf4j
public class DataResourcesDemoApplication implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
  private void showConnection() throws SQLException {
      log.info("数据源基本信息"+dataSource.toString());
      Connection connection= dataSource.getConnection();
      log.info("数据库链接信息"+connection);
      connection.close();
  }
    @Override
    public void run(String... args) throws Exception {
       showConnection();
       showData();
    }
    private void showData(){
        jdbcTemplate.queryForList("select * from FOO")
                .forEach(row->log.info(row.toString()));//方法().方法() 链式编程
        // row->log.info(row.toString()) lambda 兰不哒表达式 本质：一个方法  since1.8
    }
}
