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

		return StatementGenerator.generateStatement(name, totalAmount, frequentRenterPoints, rentalReport.toString());
	}

	@VisibleForTesting
	boolean needToAddBonus(Rental each) {
		return each.getMovie().getComputingStrategy() instanceof StatementComputingStrategyNewRelease
				&& each.getDaysRented() > 1;
	}

	@VisibleForTesting
	double calculateAmountForRental(Rental rental) {
		double thisAmount = 0;

		thisAmount = rental.getMovie().getComputingStrategy().computeAmount(rental, thisAmount);

		return thisAmount;
	}

	@VisibleForTesting
	void generateStatementPartForRental(StringBuilder rentalReport, Rental rental, double thisAmount) {
		rentalReport
				.append("\t")
				.append(rental.getMovie().getTitle())
				.append("\t")
				.append(thisAmount)
				.append("\n");
	}


}

