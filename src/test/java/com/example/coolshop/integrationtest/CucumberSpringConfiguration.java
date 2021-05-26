package com.example.coolshop.integrationtest;

import com.example.coolshop.integrationtest.stepdef.World;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Before;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;

@SpringBootTest(classes = {TestConfiguration.class, World.class})
@CucumberContextConfiguration
public class CucumberSpringConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringConfiguration.class);

    /**
     * Need this method so the cucumber will recognize this class as glue and load spring context configuration
     */
    @Before
    public void setUp() {
        LOG.info(() -> "-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
    }

}
