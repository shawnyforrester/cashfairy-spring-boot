package com.bnymellon.cashfairy.ApplicationIntegrationTests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecuredControllerRestTemplateIntegrationTest {

    private WebTestClient webClient;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup(){


        //resource: https://www.arhohuttunen.com/spring-security-testing/
        //We can alo use the @AutoConfigureMockMvc annotation as well.
    }
    @Test
    public void registrationHandler () throws Exception{


    }
}
