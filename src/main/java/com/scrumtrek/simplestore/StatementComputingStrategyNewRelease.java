package com.scrumtrek.simplestore;


public class StatementComputingStrategyNewRelease implements StatementComputingStrategy {
    @Override
    public double computeAmount(int daysRented) {
        return  daysRented * 3;
    }

    @Override
    public boolean needToAddBonus(int daysMoreThanOne) {
        return daysMoreThanOne > 1;
    }
}
