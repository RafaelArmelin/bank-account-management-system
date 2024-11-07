package com.bank;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class BankAppGUI extends JFrame {
    private ArrayList<Account> accounts;
    private JTextArea displayArea;

    public BankAppGUI(ArrayList<Account> accounts){
        this.accounts = accounts;

        //setting up the JFrame
        setTitle("Bank Account Management System");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3,2));
        JButton createAccountButton = new JButton("Create Account");
        JButton displayAccountButton = new JButton("Display Account");
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

        //display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        //action listeners for buttons
        createAccountButton.addActionListener(e -> showCreateAccountForm());
        displayAccountButton.addActionListener(e -> displayAccounts());
        //addTransactionButton.addActionListener(e -> showAddTransactionForm());
       // showLastTransactionsButton.addActionListener(e -> showLastTransactions());
        // showSortedTransactionsButton.addActionListener(e -> showSortedTransactions());
        //deleteAccountButton.addActionListener(e -> showDeleteAccountForm());
    }


    // method to display accounts
    private void displayAccounts(){
        displayArea.setText("All Accounts:\n");
        for (Account account : accounts){
            displayArea.append(account.toString() + "\n");
        }
    }

    //show Create Account Form
    private void showCreateAccountForm(){
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
        if (option == JOptionPane.OK_OPTION){
            String accountNumber = accountNumberField.getText();
            String name = nameField.getText();
            String address = addressField.getText();
            double balance = Double.parseDouble(balanceField.getText());

            Account account = new Account(accountNumber, name, address, LocalDate.now(), balance);
            accounts.add(account);
            displayArea.setText("Account Created Successfully!\n");
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
