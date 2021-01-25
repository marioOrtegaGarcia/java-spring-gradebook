package com.mortegagarcia.gradebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class GradebookApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradebookApplication.class, args);
    }

}
