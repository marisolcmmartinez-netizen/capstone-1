package com.pluralsight;
import java.util.ArrayList;                              // tool for the list
import java.util.Scanner;                               // tool to read what user types

public class ByteBistroApp {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);                            // keyboard reader, made INSIDE THE main
        ArrayList<Transaction> transactionList = LedgerFile.loadTransactions(); // load every transaction from csv

        // Welcome banner (the restaurant's starter screen)
        System.out.println("=======================================");
        System.out.println("         THE BYTE BISTRO - Ledger");
        System.out.println("      Where every bite is delicious");
        System.out.println("=======================================");

        homeScreen(userInput, transactionList);                        // go to home and HAND it to Scanner and the list

        System.out.println("Thanks for visiting the Byte Bistro. Goodbye!!!"); // runs AFTER the user exits
    }

    //                      HOME SCREEN: receives the Scanner and list,loop until x
    public static void homeScreen(Scanner userInput, ArrayList<Transaction>transactionList) {//receives Scanner & list from main
        String homeChoice;                 // holds the letter the user types(made BEFORE the loop so "while" can see it

        do {                               // do/while = show me the menu AT LEAST once

            System.out.println();         // blank line for spacing
            System.out.println("HOME - what would you like to do?"); // menu title
            System.out.println("D) Add Deposit");                   // option D
            System.out.println("P) Make Payment (Debit)");         // option P
            System.out.println("L) Ledger");                      // option L
            System.out.println("X) Exit");                       // option x
            System.out.print("Enter your choice: ");            // print(sout) so the user types on the same line

            homeChoice = userInput.nextLine().toUpperCase();// read the letter, make it CAPITAL so "d" and "D" both work

            switch (homeChoice) {                         // jump to the case that matches the letter
                case "D" -> {                            // user pick D
                    System.out.println("Add Deposit - coming soon!"); // placeholder: will call AddDeposit()
                }
                case "P" -> {                          // user picked P
                    System.out.println("Make Payment - coming soon!");   // placeholder will call makePayment()
                }
                case "L" -> {
                    System.out.println("Ledger - coming soon!");       // placeholder: will call LedgerScreen()
                }
                case "X" -> {                                         // user picked X
                    System.out.println();                            // blank line; the loop ends below
                }
                default -> {                                       // anything that isn't D,P,L or X
                    System.out.println("Invalid choice, please try again.");
                }
            }


        } while (!homeChoice.equals("X"));                      // keep looping WHILE the choice is not "X"(! means NOT)
    }


}
