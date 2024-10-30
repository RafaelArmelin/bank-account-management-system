package com.bank;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bank Account Management System");

        //create a sample account
        Account testAccount = new Account(
                "123456789",
                "John Doe",
                "123 Holloway Road",
                LocalDate.of(2024,10,29),
                1000.00
        );
        //display the account details
        System.out.println(testAccount);
    }
}
