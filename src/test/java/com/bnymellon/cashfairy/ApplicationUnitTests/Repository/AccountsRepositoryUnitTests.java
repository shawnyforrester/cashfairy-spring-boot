package com.bnymellon.cashfairy.ApplicationUnitTests.Repository;
import com.bnymellon.cashfairy.dao.AccountsRepository;
import com.bnymellon.cashfairy.model.accounts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AccountsRepositoryUnitTests {

    @Autowired
    AccountsRepository aRepo;

    @Autowired
    TestEntityManager entityManager;

    /**
     * This test checks to ensure that a new payment object is successfully persisted
     *
     */
    @Test
    public void testAddNewPayment(){
        accounts account = new accounts();

        account.setChecking_account("0001");
        account.setSavings_account("0002");
        account.setBalance(1000D);
        account.setDebit_card("4114-0723-1111-5678");

        entityManager.persistAndFlush(account);
        assertThat(aRepo.findAll().stream()
                .filter(entry -> entry.getBalance() == 1000D)
                .count() == 1);
    }

    /**
     * This checks that an object is successfully deleted from the database.
     */
    @Test void checkForDeletedEntity(){
        accounts account = new accounts();
        account.setChecking_account("0001");
        account.setSavings_account("0002");
        account.setBalance(1000D);
        account.setDebit_card("4114-0723-1111-5678");

        entityManager.persistAndFlush(account);
        aRepo.delete(account);
        assertThat(aRepo.findAll().stream()
                .filter(entry -> entry.getBalance() == 1000D)
                .count() == 0);

    }



}
