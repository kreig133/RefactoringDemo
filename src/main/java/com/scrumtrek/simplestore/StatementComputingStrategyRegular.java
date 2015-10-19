package com.scrumtrek.simplestore;

/**
 * Created by Alexey on 19.10.2015.
 */
public class StatementComputingStrategyRegular implements StatementComputingStrategy {

    public double computeAmount(double currentAmount, int daysRented) {
        currentAmount += 2;
        if (daysRented > 2) {
            currentAmount += (daysRented - 2) * 1.5;
        }
        return currentAmount;
    }

    public boolean needToAddBonus(int daysMoreThanOne) {
        return false;
    }
}
