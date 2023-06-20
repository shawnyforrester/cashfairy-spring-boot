package com.bnymellon.cashfairy.ApplicationUnitTests.Controller;

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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.List;

import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


/**
 * This class will test the controllers in the application. The @WebMvcTest annotation
 * will only load the controller/presentation layer (anything annotated @Controller)
 * it is not loading services or repos
 */
@WebMvcTest
public class AuthControllerUnitTests {

    @Autowired
    private authController ac;

    @MockBean
    private AuthenticationService as;

    @Autowired
    private MockMvc mockMvc;


    //Login and authentication tests
    @Test
    public void registrationHandlerTest() throws Exception {
        //Build the request
        RequestBuilder request = MockMvcRequestBuilders.get("/registration");

        //Perform the request
        MvcResult mvcResult = mockMvc.perform(request)
                .andDo(print())
                .andReturn();
        //assert
        String response = mvcResult.getResponse().getContentAsString();
        System.out.print(response);


    }

    @Test
    public void loginAuthenticationHandlerTest(){}

    //Account controller tests
    @Test
    public void getAccountDetailsByIdTest(){}

    @Test
    public void addPaymentDetailsTest(){}

    //Transaction controller test case
    @Test
    public void createTransactionTest(){}

}
