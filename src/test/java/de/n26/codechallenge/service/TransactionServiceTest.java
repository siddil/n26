package de.n26.codechallenge.service;

import de.n26.codechallenge.AppRunner;
import de.n26.codechallenge.messagebuffer.MessageBuffer;
import de.n26.codechallenge.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = {TransactionService.class, MessageBuffer.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceTest {

    @Autowired
    private  TransactionService service;

    @Test
    public void testInsertion(){
        Transaction toInsert = new  Transaction();
        toInsert.setAmount(0.0);
        toInsert.setTimestamp(System.currentTimeMillis());
        service.recordTransaction(toInsert);
    }

    @Test
    public void checkLongLastingRun() throws InterruptedException {

        new Runnable(){

            @Override
            public void run() {
                for (int i =0; i<=65000;i++){
                    Transaction toInsert = new  Transaction();
                    toInsert.setAmount(0.0);
                    toInsert.setTimestamp(System.currentTimeMillis());
                    service.recordTransaction(toInsert);
                }
            }
        }.run();
    }
}
