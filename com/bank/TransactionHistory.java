package com.bank;

public class TransactionHistory {
    private final int MAX_SIZE = 4; //limit to the last 4 transactions.
    private Transaction[] transactions = new Transaction[MAX_SIZE];
    private int currentIndex = 0;
    private int count = 0;

    // method to add a transaction to the history
    public void addTransaction(Transaction transaction){
        transactions[currentIndex] = transaction;
        currentIndex = (currentIndex + 1) % MAX_SIZE;
        if (count < MAX_SIZE){
            count++;
        }
    }

    // Method to retrieve transactions in a list format
    public Transaction[] getTransactions(){
        Transaction[] recentTransactions = new Transaction[count];
        for (int i = 0; i < count; i++){
            int index = (currentIndex + i) % MAX_SIZE;
            recentTransactions[i] = transactions[index];
        }
        return recentTransactions;
    }
}
