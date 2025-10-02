package com.project.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Transaction {
    private String date;
    private double amount;
    private String description;

    public String toString() {
        return "Transaction {" + " date = " + date + " amount = " + amount + " description = " + description + " }";
    }
}