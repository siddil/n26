package de.n26.codechallenge.model;

public class Statistic {

    private int numberOfTransactions=0;

    public void resetTransactionsCount(){
        numberOfTransactions =0;
    }

    public void incrementTransactionsCount(){
        numberOfTransactions = numberOfTransactions++;
    }
}
