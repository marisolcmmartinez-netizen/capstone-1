package com.pluralsight;
import java.time.LocalDate;

public class Transaction {
    private LocalDate date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, String time, String description, String vendor, double amount ){
        this.date = date;
        this.time = time;
        this.description=description;
        this.vendor= vendor;
        this.amount = amount;
    }

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
    public void display() {
        System.out.printf("%s | %s | %s | %s | %.2f\n",
                this.date, this.time, this.description, this.vendor, this.amount);
    }



}
