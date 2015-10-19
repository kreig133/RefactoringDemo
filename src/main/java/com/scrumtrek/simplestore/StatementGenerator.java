package com.scrumtrek.simplestore;


import com.google.common.annotations.VisibleForTesting;

public class StatementGenerator {
    public static String statement(Customer customer) {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        StringBuilder rentalReport = new StringBuilder();

        for (Rental each : customer.getRentals()) {
            double thisAmount1 = 0;

            thisAmount1 = each.getMovie().getComputingStrategy().computeAmount(thisAmount1, each.getDaysRented());

            double thisAmount = thisAmount1;

            // Add frequent renter points
            frequentRenterPoints++;

            // Add bonus for a two-day new-release rental
            if (each.getMovie().getComputingStrategy().needToAddBonus(each.getDaysRented())) {
                frequentRenterPoints++;
            }

            // Show figures for this rental
            generateStatementPartForRental(rentalReport, each, thisAmount);
            totalAmount += thisAmount;
        }

        return generateStatement(customer.getName(), totalAmount, frequentRenterPoints, rentalReport.toString());
    }


    @VisibleForTesting
    static void generateStatementPartForRental(StringBuilder rentalReport, Rental rental, double thisAmount) {
        rentalReport
                .append("\t")
                .append(rental.getMovie().getTitle())
                .append("\t")
                .append(thisAmount)
                .append("\n");
    }

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
