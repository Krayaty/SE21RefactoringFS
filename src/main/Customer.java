package main;

import lombok.Getter;

import java.lang.*;
import java.util.*;

@Getter
public class Customer {

    private String name;
    private double totalCharge;
    private int frequentRenterPoints;
    private Vector rentals = new Vector();

    public Customer (String name){
        this.name = name;
        this.totalCharge = 0;
        this.frequentRenterPoints = 0;
    };

    public void addRental(Rental arg) {
        rentals.addElement(arg);
    };

    public String statement() {

        Enumeration enum_rentals = rentals.elements();

        String result = "main.Rental Record for " + this.getName() + "\n" +
                "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";

        while (enum_rentals.hasMoreElements()) {
            Rental each = (Rental) enum_rentals.nextElement();
            this.frequentRenterPoints += each.getFrequentRenterPoints();
            this.totalCharge += each.getCharge();
            result += "\t" + each.getMovie().getTitle()+ "\t" + "\t" + each.getDaysRented() + "\t" + each.getCharge() + "\n";
        }

        result += "Amount owed is " + this.totalCharge + "\n";
        result += "You earned " + this.frequentRenterPoints + " frequent renter points";

        return result;
    }

}
