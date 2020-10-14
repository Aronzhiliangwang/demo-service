package top.misspro.demo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.xml.ws.Service;

/**
 * @author Evan
 */
@SpringBootApplication
@ComponentScan({"top.misspro.demo.service", "top.misspro.demo.dao"})//ioc容器的扫描位置，路径可以包含扫描，比如可以：top.misspro.demo 代替
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(Service.class, args);
    }
}
