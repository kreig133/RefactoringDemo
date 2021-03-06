package com.scrumtrek.simplestore.statement.strategies;


public class StatementComputingStrategyNewRelease implements StatementComputingStrategy {
    @Override
    public double computeAmount(int daysRented) {
        return daysRented * 3.0;
    }

    @Override
    public boolean needToAddBonus(int daysMoreThanOne) {
        return daysMoreThanOne > 1;
    }
}
