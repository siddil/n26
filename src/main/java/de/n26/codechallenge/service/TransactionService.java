package de.n26.codechallenge.service;

import de.n26.codechallenge.messagebuffer.MessageBuffer;
import de.n26.codechallenge.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    MessageBuffer buffer;



    public void recordTransaction(Transaction transaction) {
        //buffer.getBuffer().computeIfPresent(transaction.getTimestamp(),(k,v)->((List<Transaction>)v).add(transaction));
        List<Transaction> toUpdate = (List<Transaction>) buffer.getBuffer().get(transaction.getTimestamp());
        toUpdate.add(transaction);
    }
}
