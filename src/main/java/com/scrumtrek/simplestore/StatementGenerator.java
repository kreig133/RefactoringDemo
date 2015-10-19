package com.scrumtrek.simplestore;


import com.google.common.annotations.VisibleForTesting;

public class StatementGenerator {

    public static String generateStatement(String name, double totalAmount, int frequentRenterPoints, String rentalReport) {
        StringBuilder stringBuilder = new StringBuilder();

        addStatementHeader(stringBuilder, name);

        //add body
        stringBuilder.append(rentalReport);

        addStatementFooter(stringBuilder, totalAmount, frequentRenterPoints);
        return stringBuilder.toString();
    }

    @VisibleForTesting
    static void addStatementFooter(StringBuilder stringBuilder, double totalAmount, int frequentRenterPoints) {
        stringBuilder.append("Amount owed is ")
                .append(totalAmount)
                .append("\n")
                .append("You earned ")
                .append(frequentRenterPoints)
                .append(" frequent renter points.");
    }

    @VisibleForTesting
    static private void addStatementHeader(StringBuilder stringBuilder, String name) {
        stringBuilder
                .append("Rental record for ")
                .append(name)
                .append("\n");
    }
}
