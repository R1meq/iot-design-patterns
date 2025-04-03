package org.iot.patterns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PatternsApplication {
    public static void main(String[] args) {
        SpringApplication.run(PatternsApplication.class, args);
    }
}
