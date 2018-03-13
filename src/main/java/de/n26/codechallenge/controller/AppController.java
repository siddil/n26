package de.n26.codechallenge.controller;

import de.n26.codechallenge.model.Transaction;
import de.n26.codechallenge.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.POST)
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        transactionService.recordTransaction(transaction);
        return null;
    }

}
