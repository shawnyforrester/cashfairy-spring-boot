package com.bnymellon.cashfairy.ApplicationUnitTests.Controller;

import com.bnymellon.cashfairy.config.SecurityConfiguration;
import com.bnymellon.cashfairy.controller.AuthenticationRequest;
import com.bnymellon.cashfairy.controller.AuthenticationResponse;
import com.bnymellon.cashfairy.controller.authController;
import com.bnymellon.cashfairy.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ReferenceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * This class will test the controllers in the application. The @WebMvcTest annotation
 * will only load the controller/presentation layer (anything annotated @Controller)
 * it is not loading services or repos.
 */
@WebMvcTest(authController.class)
@Import(SecurityConfiguration.class)
public class AuthControllerUnitTests {

    @Autowired
    private authController ac;

    @MockBean
    private AuthenticationService as;

    @Autowired
    private MockMvc mockMvc;


    //Login and authentication tests
    @Test
    @WithMockUser
    public void registrationHandlerTest() throws Exception {

    //resource used https://www.arhohuttunen.com/spring-security-testing/
    }

    @Test
    public void loginAuthenticationHandlerTest() throws Exception {

        //resource used https://www.arhohuttunen.com/spring-security-testing/
    }

    //Account controller tests
    @Test
    public void getAccountDetailsByIdTest(){}

    @Test
    public void addPaymentDetailsTest(){}

    //Transaction controller test case
    @Test
    public void createTransactionTest(){}

}
