package com.bank;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("123455968", "John Doe", "123 Holloway Road", LocalDate.of(2024,11,01), 2000.50);

        //Add transactions
        account.addTransaction("deposit", 200);
        account.addTransaction("withdrawal", 50);
        account.addTransaction("deposit", 100);
        account.addTransaction("withdrawal", 78);
        account.addTransaction("deposit", 700);

        //display account and transactions
        System.out.println(account);
        System.out.println("\nLast Four Transactions: ");
        for (Transaction transaction : account.getTransactionHistory()){
            System.out.println(transaction);
        }
    }
}
