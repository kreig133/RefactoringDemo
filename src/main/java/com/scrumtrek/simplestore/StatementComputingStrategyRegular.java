package com.scrumtrek.simplestore;


public class StatementComputingStrategyRegular implements StatementComputingStrategy {
    @Override
    public double computeAmount(int daysRented) {
        double currentAmount = 2;
        if (daysRented > 2) {
            currentAmount += (daysRented - 2) * 1.5;
        }
        return currentAmount;
    }

    @Override
    public boolean needToAddBonus(int daysMoreThanOne) {
        return false;
    }
}
