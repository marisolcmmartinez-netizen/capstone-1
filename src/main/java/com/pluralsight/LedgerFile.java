package com.pluralsight;
import java.io.*;                                            // file tools: filereader, bufferreader, IOException
import java.time.LocalDate;                                 //  tool for real dates
import java.util.ArrayList;                                //   tool for a list that can grow

public class LedgerFile {
    public static ArrayList<Transaction> loadTransactions() { // method that GIVES BACK a list of transactions
        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();   // make a EMPTY list

        try{                                                                   // try this; if it fails, jump to catch
            FileReader csvFileOpener = new FileReader("src/main/resources/transactions.csv"); //OPEN the file
            BufferedReader csvLineReader = new BufferedReader(csvFileOpener);                 // read it LINE BY LINE

            String csvline;                                                               // empty box to hold one line
            csvLineReader.readLine();                                          // read the HEADER line and throw it away

            while ((csvline = csvLineReader.readLine()) != null){        // read the next line while there IS one
                String[] transactioninfo = csvline.split("\\|");  // cut the line at every | into 5 pieces
                LocalDate date = LocalDate.parse(transactioninfo[0]);   // piece 0: text "2026-10-05 -> real date"
                String time = transactioninfo[1];
                String description = transactioninfo[2];
                String vendor = transactioninfo[3];
                double amount = Double.parseDouble(transactioninfo[4]);  // piece 4: text "420.00" -> number 420.00

                Transaction transaction = new Transaction(date,time,description,vendor,amount); // BUILD on transaction
                transactionList.add(transaction);                                          //ADD it to the list
            }
            csvLineReader.close();  // done reading -> CLOSE the file
        }

        catch (IOException e) { // if the file is missing or can't be opened...
            System.out.println("Could not read the transactions file."); // show a message instead of crashing
        }
        return transactionList; // HAND BACK the full list
    }

    public static void saveTransactions(ArrayList<Transaction> transactionsList) {    // receives the full list to save
        try {                                                                    // try this: if it fails, jump to catch

            FileWriter csvFileWriter = new FileWriter("src/main/resources/transactions.csv");// OPEN the file to WRITE
            BufferedWriter csvLineWriter = new BufferedWriter(csvFileWriter);           // Write text

            csvLineWriter.write("date|time|description|vendor|amount\n"); // Write the HEADER first

            for(Transaction currentTransaction : transactionsList) { //for EACH transaction in the list
                String csvLine = String.format("%s|%s|%s|%s|%.2f\n",    // build one line in CSV format
                 currentTransaction.getDate() ,                        // fills blank 1
                 currentTransaction.getTime() ,                       // fills blank 2
                 currentTransaction.getDescription() ,               // fills blank 3
                 currentTransaction.getVendor(),                    // fills blank 4
                 currentTransaction.getAmount());                  // fills blank 5 (note the); at the END)
                csvLineWriter.write(csvLine);                     // write it into the file
            }
            csvLineWriter.close();                              // CLOSE the file (this actually saves it)
        }
         catch (IOException e) {                              // if the file cant  be opened...
             System.out.println("Could not save the transaction file."); // show this message instead of crashing
         }

    }
}
