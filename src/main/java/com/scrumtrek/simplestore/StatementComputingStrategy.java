package com.scrumtrek.simplestore;

public interface StatementComputingStrategy {
    double computeAmount(int daysRented);

    boolean needToAddBonus(int daysMoreThanOne);
}
