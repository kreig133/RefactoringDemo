package com.scrumtrek.simplestore;

/**
 * Created by Alexey on 19.10.2015.
 */
public class StatementComputingNewRelease implements StatementComputing {

    public double computeAmount(Rental rental, double thisAmount) {
        thisAmount += rental.getDaysRented() * 3;

        return thisAmount;
    }
}