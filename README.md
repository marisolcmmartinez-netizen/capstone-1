# The Byte Bistro Ledger

This is my Capstone 1 project. It's a Java console app that keeps track of deposits and payments for The Byte Bistro, a made-up restaurant with coding jokes on the menu (like Null Pointer Nachos and 404 Fries Not Found).

## What it does
- Add a deposit
- Make a payment
- Look at the ledger (all, deposits, or payments), newest first
- Run reports: Month To Date, Previous Month, Year To Date, Previous Year, and Search by Vendor
- Saves everything to transactions.csv

## How to run it
1. Open the project in IntelliJ
2. Run ByteBistroApp.java
3. Pick options from the menu

## Files
- Transaction.java - holds one transaction (date, time, description, vendor, amount)
- LedgerFile.java - reads and saves the CSV file
- ByteBistroApp.java - the menus and screens
- transactions.csv - where the transactions are saved



## What was hard
- Keeping track of my curly braces
- Previous Month in January, since month 1 minus 1 is 0, so I used an if/else to make it December of last year
