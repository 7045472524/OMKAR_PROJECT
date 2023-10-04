package com.nimap.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath*:applicationContext.xml"})
public class NimapApplication {
    private static final Logger LOG = LoggerFactory.getLogger(NimapApplication.class);

    public static void main(String[] args) {
        LOG.debug("inside Starting Nimap Test Application");

        System.out.println("inside Application starting...");

        SpringApplication.run(NimapApplication.class, args);

        System.out.println("inside Application started...");
    }
}
