package com.scrumtrek.simplestore;

/**
 * Created by Alexey on 19.10.2015.
 */
public class StatementComputingStrategyNewRelease implements StatementComputingStrategy {

    public double computeAmount(double currentAmount, int daysRented) {
        return currentAmount += daysRented * 3;
    }

    public boolean needToAddBonus(int daysMoreThanOne) {
        return daysMoreThanOne > 1;
    }
}
