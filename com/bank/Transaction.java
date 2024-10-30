package com.bank;

import java.time.LocalDate;

public class Transaction {
    private String transactionType;
    private double transactionAmount;
    private LocalDate transactionDate;

    // Constructor
    public Transaction(String transactionType, double transactionAmount, LocalDate transactionDate){
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
    }

    // Getter methods
    public String getTransactionType(){
        return transactionType;
    }

    public double getTransactionAmount(){
        return transactionAmount;
    }

    public LocalDate getTransactionDate(){
        return transactionDate;
    }

    @Override
    public String toString(){
        return STR."\{transactionType} of £\{transactionAmount} on \{transactionDate}";
    }
}
