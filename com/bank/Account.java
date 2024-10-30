package com.bank;

import java.time.LocalDate;

public class Account {
    private String accountNumber;
    private String holderName;
    private String holderAddress;
    private LocalDate openingDate;
    private double currentBalance;

    // Constructor
    public Account(String accountNumber, String holderName, String holderAddress, LocalDate openingDate, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.holderAddress = holderAddress;
        this.openingDate = openingDate;
        this.currentBalance = initialBalance;
    }

    // Getter methods
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getHolderAddress() {
        return holderAddress;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }
    @Override
    public String toString() {
        return STR."""
                Account Number: \{accountNumber}
                Holder Name: \{holderName}
                Holder Address: \{holderAddress}
                Opening Date: \{openingDate}
                Current Balance: $\{currentBalance}""";
    }

}
