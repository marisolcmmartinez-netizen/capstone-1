package com.pluralsight;
import java.time.LocalDate;

public class Transaction {                                        // BLUEPRINT for one transaction (one line of the csv)
    // data members:  what every transaction HAS
    private LocalDate date;
    private String time;
    private String description;
    private String vendor;
    private double amount;
    // constuctor: Builds one transaction
    public Transaction(LocalDate date, String time, String description, String vendor, double amount ){
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor= vendor;
        this.amount = amount;
    }
    //  getters: let other classes READ each value ( not setters because transaction never changes
    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }
    //            PRINTS this transaction as one row
    public void display() {
        System.out.printf("%s | %s | %s | %s | %.2f\n",
                this.date, this.time, this.description, this.vendor, this.amount);
    }



}
