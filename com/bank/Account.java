package com.bank;

import java.time.LocalDate;

public class Account {
    private String accountNumber;
    private String holderName;
    private String holderAddress;
    private LocalDate openingDate;
    private double currentBalance;
    private TransactionHistory transactionHistory;

    // Constructor
    public Account(String accountNumber, String holderName, String holderAddress, LocalDate openingDate, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.holderAddress = holderAddress;
        this.openingDate = openingDate;
        this.currentBalance = initialBalance;
        this.transactionHistory = new TransactionHistory();
    }

    //Method do add a transaction
    public void addTransaction(String type, double amount){
        LocalDate date = LocalDate.now();

        //update the balance based on transaction type
        if (type.equalsIgnoreCase("deposit")){
            currentBalance += amount;
        } else if (type.equalsIgnoreCase("withdrawal")) {
            if (currentBalance >= amount){
                currentBalance -= amount;
            } else{
                System.out.println("Insufficient balance for withdrawal!");
                return;
            }
        } else{
            System.out.println("Invalid transaction type!");
            return;
        }
        // Create and add a transaction
        Transaction transaction = new Transaction(type, amount, date);
        transactionHistory.addTransaction(transaction);
    }

    //Method to retrieve transaction history
    public Transaction[] getTransactionHistory(){
        return transactionHistory.getTransactions();
    }
    //Method to retrieve sorted transaction history
    public Transaction[] getSortedTransactionHistory(){
        return transactionHistory.getSortedTransactions();
    }

    @Override
    public String toString() {
        return STR."""
                Account Number: \{accountNumber}
                Holder Name: \{holderName}
                Holder Address: \{holderAddress}
                Opening Date: \{openingDate}
                Current Balance: £\{currentBalance}""";
    }

}
