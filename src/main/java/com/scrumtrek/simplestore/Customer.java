package com.scrumtrek.simplestore;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private List<Rental> rentals = new ArrayList<>();

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addRental(Rental arg){
		rentals.add(arg);
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
				
		StringBuilder rentalReport = new StringBuilder();

		for(Rental each: rentals) {
			double thisAmount = calculateAmountForRental(each);

			// Add frequent renter points
			frequentRenterPoints++;

			// Add bonus for a two-day new-release rental
			if (needToAddBonus(each)) {
				frequentRenterPoints ++;
			}

			// Show figures for this rental
			generateStatementPartForRental(rentalReport, each, thisAmount);
			totalAmount += thisAmount;
		}

		return generateStatement(totalAmount, frequentRenterPoints, rentalReport.toString());
	}

	boolean needToAddBonus(Rental each) {
		return each.getMovie().getPriceCode() instanceof StatementComputingNewRelease
				&& each.getDaysRented() > 1;
	}

	@VisibleForTesting
	double calculateAmountForRental(Rental rental) {
		double thisAmount = 0;

		thisAmount = rental.getMovie().getPriceCode().computeAmount(rental, thisAmount);

		return thisAmount;
	}

	@VisibleForTesting
	void generateStatementPartForRental(StringBuilder rentalReport, Rental each, double thisAmount) {
		rentalReport.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
	}

	@VisibleForTesting
	String generateStatement(double totalAmount, int frequentRenterPoints, String rentalReport) {
		StringBuilder stringBuilder = new StringBuilder();
		
		addStatementHeader(stringBuilder);

		//add body
		stringBuilder.append(rentalReport);

		addStatementFooter(stringBuilder, totalAmount, frequentRenterPoints);
		return stringBuilder.toString();
	}

	private void addStatementFooter(StringBuilder stringBuilder, double totalAmount, int frequentRenterPoints) {
		stringBuilder.append("Amount owed is ")
				.append(totalAmount)
				.append("\n")
				.append("You earned ")
				.append(frequentRenterPoints)
				.append(" frequent renter points.");
	}

	private void addStatementHeader(StringBuilder stringBuilder) {
		stringBuilder
				.append("Rental record for ")
				.append(name)
				.append("\n");
	}
}

