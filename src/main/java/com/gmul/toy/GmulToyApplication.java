package com.gmul.toy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GmulToyApplication {
    private static ConfigurableApplicationContext configurableApplicationContext;

    public static ApplicationContext getApplicationContext() {
        return GmulToyApplication.configurableApplicationContext;
    }

    public static void main(String[] args) {
        configurableApplicationContext = SpringApplication.run(GmulToyApplication.class, args);
    }
}
