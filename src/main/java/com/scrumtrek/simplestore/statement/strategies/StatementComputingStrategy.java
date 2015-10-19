package com.scrumtrek.simplestore.statement.strategies;

public interface StatementComputingStrategy {
    double computeAmount(int daysRented);

    boolean needToAddBonus(int daysMoreThanOne);
}
