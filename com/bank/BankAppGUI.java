package com.bank;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class BankAppGUI extends JFrame {
    private ArrayList<Account> accounts;
    private JTextArea displayArea;

    public BankAppGUI(ArrayList<Account> accounts) {
        this.accounts = accounts;

        // Setting up the JFrame
        setTitle("Bank Account Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        JButton createAccountButton = new JButton("Create Account");
        JButton displayAccountButton = new JButton("Display Accounts");
        JButton addTransactionButton = new JButton("Add Transaction");
        JButton showLastTransactionsButton = new JButton("Show Last Transactions");
        JButton showSortedTransactionsButton = new JButton("Show Sorted Transactions");
        JButton deleteAccountButton = new JButton("Delete Account");

        buttonPanel.add(createAccountButton);
        buttonPanel.add(displayAccountButton);
        buttonPanel.add(addTransactionButton);
        buttonPanel.add(showLastTransactionsButton);
        buttonPanel.add(showSortedTransactionsButton);
        buttonPanel.add(deleteAccountButton);

        // Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action listeners for buttons
        createAccountButton.addActionListener(e -> showCreateAccountForm());
        displayAccountButton.addActionListener(e -> displayAccounts());
        addTransactionButton.addActionListener(e -> showAddTransactionForm());
        showLastTransactionsButton.addActionListener(e -> showLastTransactions());
        showSortedTransactionsButton.addActionListener(e -> showSortedTransactions());
        deleteAccountButton.addActionListener(e -> showDeleteAccountForm());
    }

    // Method to display all accounts
    private void displayAccounts() {
        displayArea.setText("ALL ACCOUNTS:\n\n");
        for (Account account : accounts) {
            displayArea.append(account.toString() + "\n");
        }
    }

    // Show Create Account Form
    private void showCreateAccountForm() {
        JTextField accountNumberField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField balanceField = new JTextField();

        Object[] fields = {
                "Account Number: ", accountNumberField,
                "Name: ", nameField,
                "Address: ", addressField,
                "Initial Balance: ", balanceField
        };

        int option = JOptionPane.showConfirmDialog(this, fields, "Create Account", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String accountNumber = accountNumberField.getText();
            String name = nameField.getText();
            String address = addressField.getText();
            double balance = Double.parseDouble(balanceField.getText());

            // check if the account number already exists
            if (findAccountByNumber(accountNumber) != null) {
                JOptionPane.showMessageDialog(this, "Account number already exists! Please choose a different account number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Account account = new Account(accountNumber, name, address, LocalDate.now(), balance);
            accounts.add(account);
            displayArea.setText("Account Created Successfully!\n");
        }
    }

    private Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    // Show Add Transaction Form
    public void showAddTransactionForm() {
        JTextField accountNumberField = new JTextField();
        JComboBox<String> transactionTypeField = new JComboBox<>(new String[]{"Deposit", "Withdrawal"});
        JTextField amountField = new JTextField();

        Object[] fields = {
                "Account Number: ", accountNumberField,
                "Transaction Type: ", transactionTypeField,
                "Amount: ", amountField
        };

        int option = JOptionPane.showConfirmDialog(
                this,fields, "Add Transaction", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String accountNumber = accountNumberField.getText();
            String transactionType = (String) transactionTypeField.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());

            Account account = findAccountByNumber(accountNumber);
            if (account != null) {
                if (transactionType.equalsIgnoreCase("Deposit")){
                    account.addTransaction("deposit", amount);
                    displayArea.setText("Deposit transaction added successfully!\n");
                } else if (transactionType.equalsIgnoreCase("withdrawal")) {
                    //check if there is sufficient balance
                    if (account.getCurrentBalance() >= amount) {
                        displayArea.setText("Withdrawal transaction added successfully!\n");
                    } else {
                        JOptionPane.showMessageDialog(this, "Insuficient balance for withdrawal.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                displayArea.setText("Account not found.\n");
            }
        }
    }

    // Show the last four transactions for a specific account
    private void showLastTransactions() {
        String accountNumber = JOptionPane.showInputDialog(
                this,
                "Enter Account Number:",
                "Show Last Transactions",
                JOptionPane.PLAIN_MESSAGE
        );
        Account account = findAccountByNumber(accountNumber);

        if (account != null) {
            displayArea.setText("Last Four Transactions:\n\n");
            for (Transaction transaction : account.getTransactionHistory()) {
                displayArea.append(transaction.toString() + "\n");
            }
        } else {
            displayArea.setText("Account not found.\n");
        }
    }

    // Show the last four transactions in sorted order for a specific account
    private void showSortedTransactions() {
        String accountNumber = JOptionPane.showInputDialog(
                this,
                "Enter Account Number:",
                "Show Sorted Transactions",
                JOptionPane.PLAIN_MESSAGE
        );
        Account account = findAccountByNumber(accountNumber);

        if (account != null) {
            displayArea.setText("Last Four Transactions (Sorted by Amount):\n\n");
            for (Transaction transaction : account.getSortedTransactionHistory()) {
                displayArea.append(transaction.toString() + "\n");
            }
        } else {
            displayArea.setText("Account not found.\n");
        }
    }

    // Show Delete Account Form
    private void showDeleteAccountForm() {
        String accountNumber = JOptionPane.showInputDialog(
                this,
                "Enter Account Number to Delete:",
                "Delete Account",
                JOptionPane.PLAIN_MESSAGE
        );
        Account account = findAccountByNumber(accountNumber);

        if (account != null) {
            accounts.remove(account);
            displayArea.setText("Account deleted successfully!\n");
        } else {
            displayArea.setText("Account not found.\n");
        }
    }

    public static void main(String[] args) {
        ArrayList<Account> accounts = new ArrayList<>();
        SwingUtilities.invokeLater(() -> {
            BankAppGUI app = new BankAppGUI(accounts);
            app.setVisible(true);
        });
    }
}
