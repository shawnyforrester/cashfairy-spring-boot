package com.bnymellon.cashfairy.ApplicationUnitTests.Repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * This class will test the repositories in the application
 */
@DataJpaTest
public class CustomerRepositoryUnitTests {

    @Test
    void contextLoads() {
    }

    //The syntax for a negative test: assertThrow( Exception, ()-> rep.save(object));

}
