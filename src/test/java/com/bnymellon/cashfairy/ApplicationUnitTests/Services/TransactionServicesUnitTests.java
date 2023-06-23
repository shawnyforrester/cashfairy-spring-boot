package com.bnymellon.cashfairy.ApplicationUnitTests.Services;
import com.bnymellon.cashfairy.dao.TransactionRepository;
import com.bnymellon.cashfairy.model.transactions;
import com.bnymellon.cashfairy.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;



@ExtendWith(MockitoExtension.class) // required to properly inject the mock
public class TransactionServicesUnitTests {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository tRepo;

    //mockdata
    List<transactions> allTransactions = new ArrayList<>();

    @Test
    public void createNewTransactionTest_shouldReturn_TransactionObject(){


    }
}
