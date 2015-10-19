package com.scrumtrek.simplestore.statement;


import com.google.common.annotations.VisibleForTesting;
import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Rental;

public class StatementGenerator {

    public static String generateStatement(Customer customer) {
        StringBuilder stringBuilder = new StringBuilder();

        addStatementHeader(stringBuilder, customer.getName());

        addStatementBody(stringBuilder, customer);

        addStatementFooter(stringBuilder, customer);
        return stringBuilder.toString();
    }

    @VisibleForTesting
    private static void addStatementHeader(StringBuilder stringBuilder, String name) {
        stringBuilder
          .append("Rental record for ")
          .append(name)
          .append("\n");
    }

    private static void addStatementBody(StringBuilder stringBuilder, Customer customer) {
        for (Rental each : customer.getRentals()) {
            // Show figures for this rental
            generateStatementPartForRental(stringBuilder, each,
              each.getMovie().getComputingStrategy().computeAmount(each.getDaysRented()));
        }
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


    @VisibleForTesting
    static void addStatementFooter(StringBuilder stringBuilder, Customer customer) {
        stringBuilder.append("Amount owed is ")
          .append(calculateTotalAmount(customer))
          .append("\n")
          .append("You earned ")
          .append(calculateFrequentRenterPoints(customer))
          .append(" frequent renter points.");
    }

    private static int calculateFrequentRenterPoints(Customer customer) {
        int frequentRenterPoints = customer.getRentals().size();

        for (Rental each : customer.getRentals()) {
            // Add bonus for a two-day new-release rental
            if (each.getMovie().getComputingStrategy().needToAddBonus(each.getDaysRented())) {
                frequentRenterPoints++;
            }
        }

        return frequentRenterPoints;
    }

    private static double calculateTotalAmount(Customer customer) {
        double totalAmount = 0;

        for (Rental each : customer.getRentals()) {
            totalAmount += each.getMovie().getComputingStrategy().computeAmount(each.getDaysRented());
        }

        return totalAmount;
    }
}
