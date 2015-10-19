package com.scrumtrek.simplestore;

/**
 * Created by Alexey on 19.10.2015.
 */
public interface StatementComputingStrategy {
    public double computeAmount(Rental rental, double thisAmount);

    public boolean needToAddBonus(int daysMoreThanOne);
}
