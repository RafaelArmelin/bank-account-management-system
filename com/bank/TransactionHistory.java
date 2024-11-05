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

    //New method to get transactions sorted by amount
    public Transaction[] getSortedTransactions(){
        Transaction[] recentTransactions = getTransactions();

        //Insertion Sort Algorithm for sorting by transaction amount
        for(int i = 1; i < recentTransactions.length; i++){
            Transaction key = recentTransactions[i];
            int j = i - 1;

            while (j >= 0 && recentTransactions[j].getTransactionAmount() > key.getTransactionAmount()){
                recentTransactions[j + 1] = recentTransactions[j];
                j = j - 1;
            }
            recentTransactions[j + 1] = key;
        }
        return recentTransactions;
    }
}