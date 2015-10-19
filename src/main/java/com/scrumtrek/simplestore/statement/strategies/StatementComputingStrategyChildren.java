package com.scrumtrek.simplestore.statement.strategies;

public class StatementComputingStrategyChildren implements StatementComputingStrategy {
    @Override
    public double computeAmount(int daysRented) {
        double currentAmount = 1.5;
        if (daysRented > 3) {
            currentAmount = (daysRented - 3) * 1.5;
        }
        return currentAmount;
    }

    @Override
    public boolean needToAddBonus(int daysMoreThanOne) {
        return false;
    }
}
