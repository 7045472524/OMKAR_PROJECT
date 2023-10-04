package com.nimap.app;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NimapApplicationTest {

    private static final Logger LOG = LoggerFactory.getLogger(NimapApplicationTest.class);

    @Test
    void contextLoads() {
        LOG.debug("inside contextLoads() LOG");
        System.out.println("inside contextLoads SOP--->");
    }
}
