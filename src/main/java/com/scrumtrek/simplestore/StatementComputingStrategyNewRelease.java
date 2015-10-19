package com.scrumtrek.simplestore;

/**
 * Created by Alexey on 19.10.2015.
 */
public class StatementComputingStrategyNewRelease implements StatementComputingStrategy {

    public double computeAmount(Rental rental, double thisAmount) {
        thisAmount += rental.getDaysRented() * 3;

        return thisAmount;
    }

    public boolean needToAddBonus(int daysMoreThanOne){
        return daysMoreThanOne > 1;
    }
}
