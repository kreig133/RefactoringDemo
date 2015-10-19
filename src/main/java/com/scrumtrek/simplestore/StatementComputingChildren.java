package com.scrumtrek.simplestore;

/**
 * Created by Alexey on 19.10.2015.
 */
public class StatementComputingChildren implements StatementComputing {

    public double computeAmount(Rental rental, double thisAmount) {
        thisAmount += 1.5;
        if (rental.getDaysRented() > 3)
        {
            thisAmount = (rental.getDaysRented() - 3) * 1.5;
        }
        return thisAmount;
    }

}
