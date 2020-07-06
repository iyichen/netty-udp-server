package xyz.iyichen.udp.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {
    // 启动类
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}