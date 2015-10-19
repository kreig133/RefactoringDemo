package com.scrumtrek.simplestore;

/**
 * Created by Alexey on 19.10.2015.
 */
public interface StatementComputingStrategy {
    public double computeAmount(double currentAmount, int daysRented);

    public boolean needToAddBonus(int daysMoreThanOne);
}
