package com.pluralsight;
import java.sql.Array;
import java.util.ArrayList;                              // tool for the list
import java.util.Scanner;                               // tool to read what user types
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            System.out.print("Enter your choice: ");            // print so the user types on the same line

            homeChoice = userInput.nextLine().toUpperCase();// read the letter, make it CAPITAL so "d" and "D" both work

            switch (homeChoice) {                         // jump to the case that matches the letter
                case "D" -> {                            // user pick D
                     addDeposit(userInput, transactionList);                       //  Add a Deposit
                }
                case "P" -> {                                                    // user picked P
                    makePayment(userInput, transactionList);                    //  go make a payment
                }
                case "L" -> {                                                // user picked L
                    ledgerScreen(userInput, transactionList);               // open the ledger screen
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
        // -----------  ADD DEPOSIT: ask for the details, add it to the list, save the file ----------
        public static void addDeposit(Scanner userInput, ArrayList<Transaction> transactionList) {
            System.out.println();                       // blank line for spacing
            System.out.println("ADD DEPOSIT");         // screen title

            System.out.print("Description: ");                 // ask what the deposit is(print = type on the same line)
            String description = userInput.nextLine();        // ex "Null pointer nachos x 10"

            System.out.print("Vendor: ");                    // ask who its from
            String vendor = userInput.nextLine();           // ex " Walk- ins"

            System.out.print("Amount: ");                 // ask how much
            double amount = 0;
            try {
                amount = Double.parseDouble(userInput.nextLine());
            }
            catch (Exception e){
                System.out.println("That's not a number.");
            }

            if (amount <= 0) {                       // a deposit must be MORE than 0
                System.out.println("Amount must be more than 0. Deposit not saved."); // tell the user save nothing
            } else {                                  // the amount is good, so save it
                LocalDate today = LocalDate.now();                  // today's date
                LocalDateTime rightNow = LocalDateTime.now();      // the current date + time
                DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");// time style hours:minutes:seconds
                String time = rightNow.format(timeFormat);                          // ex "00.50.10"

                Transaction newDeposit = new Transaction(today, time, description, vendor, amount);// Build the deposit
                transactionList.add(newDeposit);                                             // ADD it to the list
                LedgerFile.saveTransactions(transactionList);                  // save the whole list to CSV right away

                System.out.printf("Deposit of $%.2f saved!\n", amount);         // confirm to the user(" close after\n)
            }
        }
                         //MAKE PAYMENT: ask for details, save it as a NEGATIVE amount
            public static void makePayment(Scanner userInput, ArrayList<Transaction>transactionList) {

                System.out.println();                                    // blank line for spacing
                System.out.println("MAKE PAYMENT");                     // screen title

                System.out.print("Description: ");                     // ask what the payment is for
                String description = userInput.nextLine();            // ex "tortilla chips bulk order"

                System.out.print("Vendor: ");                       // ask who its paid too
                String vendor = userInput.nextLine();              // ex "restaurant depot"

                System.out.print("Amount: ");                    // ask how much (user types a positive number)
                double amount = 0;
                try {
                    amount = Double.parseDouble(userInput.nextLine());
                }
                catch (Exception e){
                    System.out.println("That's not a number.");
                }

                if (amount <= 0 ) {
                    System.out.println("amount must be more than 0. Payment not saved.");// the user should type more than 0
                }
                else {                                                               // the amount is good to save
                    LocalDate today = LocalDate.now();                              // today's date
                    LocalDateTime rightNow = LocalDateTime.now();                  // the current date + time
                    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss"); // time style
                    String time = rightNow.format(timeFormat);                   // ex " 09:10:25"

                    double paymentAmount = amount * -1;                        // make it NEGATIVE (money going OUT)

                    Transaction newPayment = new Transaction(today, time, description, vendor, paymentAmount);// build
                    transactionList.add(newPayment);                 // ADD it to the list
                    LedgerFile.saveTransactions(transactionList); // SAVE to CSV right away

                    System.out.printf("Payment of $%.2f saved!\n",amount); // confirm (shows the positive number)

                }
        }
        //--------------------------            (loops backwards)-------------------------------
    public static void displayNewestFirst(ArrayList<Transaction>listToShow) { // receives ANY list of transactions
        if (listToShow.size() == 0) {                                      //nothing in the list
            System.out.println("No transaction found.");
        }
        else {
            System.out.println();                                        // blank line for spacing
            System.out.println("Date | Time | Description | Vendor | Amount"); // column titles
            for (int i = listToShow.size() -1; i >= 0; i--){//star at LAST SPOT(size -1),keep going while i >=0 -1 each round
            listToShow.get(i).display(); }                            // print the transaction at spot i
        }
    }
    //                              LEDGER SCREEN: ALL Deposits,Payment,Reports, Home
    public static void ledgerScreen(Scanner userInput, ArrayList<Transaction> transactionList) {
        String ledgerChoice;                                        // the letter the user picks
        do {
            // the ledger menu goes HERE
            System.out.println();                                   // blank line for spacing
            System.out.println("Ledger - what would you like to see?");
            System.out.println("A) ALL");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Enter your choice: ");               // print so its one the same line

            ledgerChoice = userInput.nextLine().toUpperCase();  // read their letter, make it uppercase
            switch (ledgerChoice) {                           //*********
                case "A" -> {                                // A = show ALL
                    displayNewestFirst(transactionList);        // print the whole list
                }
                case "D" -> {                             // D = Deposits only
                    ArrayList<Transaction> depositList = new ArrayList<Transaction>(); // empty box for deposits
                    for (Transaction currentTransaction : transactionList) {      // check each transaction
                        if (currentTransaction.getAmount() > 0) {                // positive = deposit
                            depositList.add(currentTransaction);                // put it in the box
                        }
                    }
                    displayNewestFirst(depositList);                        // print only deposits
                }
                case "P" -> {
                    ArrayList<Transaction> paymentList = new ArrayList<Transaction>(); // empty box for payments
                    for (Transaction currentTransaction : transactionList) {        // check each transaction
                        if (currentTransaction.getAmount() < 0) {
                            paymentList.add(currentTransaction);
                        }
                    }
                    displayNewestFirst(paymentList);                      // print only *******
                }
                case "R" -> {                                          // R = reports
                    reportsScreen(userInput, transactionList);        // open the report screen
                }
                case "H" -> {                                    // H = back to Home
                    System.out.println();                        // blank line; loop ends below
                }
                default -> {
                    System.out.println("Invalid choice, please try again.");
                }
            }
        } while (!ledgerChoice.equals("H"));                      // Keep going until they pick H(back to home)
    }                           // report screen: filter by date or vendor
        public static void reportsScreen(Scanner userInput, ArrayList<Transaction>transactionList) {//**** 2
            LocalDate today = LocalDate.now();                             //  Today's date
            int thisMonth = today.getMonthValue();                        // this month as a #
            int thisYear = today.getYear();                              // this year, like 2026
            // figure out last month for report 2
            int lastMonth;                                            // will hold last month's #
            int lastMonthYear;                                       // will hold the year last month was in

            if(thisMonth == 1) {                                   // January is special: 1-1 = 0 there is no 0 month
                lastMonth = 12;                                   // so last month was in December
                lastMonthYear = thisYear - 1;                    // of the year before
            } else {                                            // every other month
                lastMonth = thisMonth - 1;                      // just go back one month
                lastMonthYear = thisYear;                     // same year
            }   // show the menu until the user picks 0
                String reportChoice;                        // the number the user picks
                do {
                    System.out.println();                  // blank line
                    System.out.println("REPORTS - pick a report: ");
                    System.out.println("1) Month to Date");
                    System.out.println("2) Previous Month");
                    System.out.println("3) Year to Date");
                    System.out.println("4) Previous Year");
                    System.out.println("5) Search by vendor");
                    System.out.println("0) Back");
                    System.out.print("Enter your choice: ");      // print so they type one same line

                    reportChoice = userInput.nextLine();


                    switch (reportChoice) {                                  // jump to the report they choose
                        //1) MONTH TO DATE: this month AND this year
                        case "1" -> {
                            ArrayList<Transaction> reportList = new ArrayList<Transaction>(); // EMPTY BOX
                            for (Transaction currentTransactions : transactionList) {
                                if (currentTransactions.getDate().getMonthValue() == thisMonth) { // same month
                                    if (currentTransactions.getDate().getYear() == thisYear) {  // and same year
                                        reportList.add(currentTransactions);
                                    }
                                }
                            }
                            displayNewestFirst(reportList); // print the report
                        }
                        // 2) PREVIOUS MONTH: last month AND the year it was in
                        case "2" -> {                                         // 2 = previous month
                            ArrayList<Transaction> reportList = new ArrayList<Transaction>(); // empty box
                            for (Transaction currentTransactions : transactionList) {        // check each one
                                if (currentTransactions.getDate().getMonthValue() == lastMonth) { // lastMonth
                                    if (currentTransactions.getDate().getYear() == lastMonthYear) { // and the right year
                                        reportList.add(currentTransactions); // put it in the box
                                    }
                                }
                            }
                            displayNewestFirst(reportList);                             // print the report
                        }
                       // 3) YEAR TO DATE: anything from this year
                        case "3" -> {                                               // 3 == year to date
                            ArrayList<Transaction> reportList = new ArrayList<Transaction>();
                            for (Transaction currentTransactions : transactionList) {
                                if (currentTransactions.getDate().getYear() == thisYear) {          // same year
                                    reportList.add(currentTransactions);
                                }
                            }
                            displayNewestFirst(reportList);
                        }
                        // 4) PREVIOUS YEAR: anything from last year
                        case "4" -> {
                            ArrayList<Transaction> reportList = new ArrayList<Transaction>();
                        for (Transaction currentTransactions : transactionList) {
                            if (currentTransactions.getDate().getYear() == thisYear - 1) { // last year
                                reportList.add(currentTransactions);
                            }
                        }
                        displayNewestFirst(reportList);
                    }
                    // 5) SEARCH BY VENDOR: ask for a name,show only the vendor
                    case "5" -> {                                          // 5 = search by Vendor
                        System.out.print("Vendor name: ");
                        String vendorSearch = userInput.nextLine();
                        ArrayList<Transaction>reportList = new ArrayList<Transaction>();
                            for (Transaction currentTransactions : transactionList ) {
                                if (currentTransactions.getVendor().toUpperCase().equals(vendorSearch.toUpperCase())){
                                    reportList.add(currentTransactions);
                                }
                            }
                            displayNewestFirst(reportList);
                        }
                        // 0) BACK: leave report and return to ledger
                        case "0" -> {
                            System.out.println();
                        }
                        // anything else
                        default -> {
                            System.out.println("Invalid choice, please try again.");

                        }
                        }
                        } while (!reportChoice.equals("0"));       // keep going until they pick 0(back)
                }


    }





