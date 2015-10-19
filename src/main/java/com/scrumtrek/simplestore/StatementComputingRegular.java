package com.scrumtrek.simplestore;

/**
 * Created by Alexey on 19.10.2015.
 */
public class StatementComputingRegular implements StatementComputing {

    public double computeAmount(Rental rental, double thisAmount) {
        thisAmount += 2;
        if (rental.getDaysRented() > 2)
        {
            thisAmount += (rental.getDaysRented() - 2) * 1.5;
        }
        return thisAmount;
    }

}
