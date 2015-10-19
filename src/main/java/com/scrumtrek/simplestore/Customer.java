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

	public String statement()
	{
		double totalAmount = 0;
		int frequentRenterPoints = 0;
				
		StringBuilder rentalReport = new StringBuilder();

		for(Rental each: rentals) {
			double thisAmount = calculateAmountForRental(each);

			// Add frequent renter points
			frequentRenterPoints++;

			// Add bonus for a two-day new-release rental
			if ((each.getMovie().getPriceCode() == PriceCodes.NewRelease) && (each.getDaysRented() > 1))
			{
				frequentRenterPoints ++;
			}

			// Show figures for this rental
			generateStatementPartForRental(rentalReport, each, thisAmount);
			totalAmount += thisAmount;
		}


		return generateStatement(totalAmount, frequentRenterPoints, rentalReport.toString());
	}

	@VisibleForTesting
	void generateStatementPartForRental(StringBuilder rentalReport, Rental each, double thisAmount) {
		rentalReport.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
	}

	@VisibleForTesting
	double calculateAmountForRental(Rental rental) {
		double thisAmount = 0;

		// Determine amounts for each line
		switch(rental.getMovie().getPriceCode()) {
            case Regular:
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                {
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                }
                break;

            case NewRelease:
                thisAmount += rental.getDaysRented() * 3;
                break;

            case Childrens:
                thisAmount += 1.5;
                if (rental.getDaysRented() > 3)
                {
                    thisAmount = (rental.getDaysRented() - 3) * 1.5;
                }
                break;
        }
		return thisAmount;
	}

	@VisibleForTesting
	String generateStatement(double totalAmount, int frequentRenterPoints, String rentalReport) {
		return new StringBuilder("Rental record for ")
				.append(name)
				.append("\n")
				.append(rentalReport)
				// Add footer lines
				.append("Amount owed is " )
				.append(totalAmount)
				.append("\n")
				.append("You earned ")
				.append(frequentRenterPoints)
				.append(" frequent renter points.")
				.toString();
	}
}

