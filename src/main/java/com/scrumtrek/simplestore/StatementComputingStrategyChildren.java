package com.scrumtrek.simplestore;

/**
 * Created by Alexey on 19.10.2015.
 */
public class StatementComputingStrategyChildren implements StatementComputingStrategy {

    public double computeAmount(double currentAmount, int daysRented) {
        currentAmount += 1.5;
        if (daysRented > 3)
        {
            currentAmount = (daysRented - 3) * 1.5;
        }
        return currentAmount;
    }

    public boolean needToAddBonus(int daysMoreThanOne){
        return false;
    }
}
