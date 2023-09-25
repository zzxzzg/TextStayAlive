package com.guardz.alive.start;

import com.guardz.alive.enginer.Game;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.guardz.alive")
public class TextStayAliveApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextStayAliveApplication.class, args);
    }

}
