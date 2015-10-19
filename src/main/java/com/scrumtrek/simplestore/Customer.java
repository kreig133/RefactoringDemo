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
				
		String rentalReport = "";
		for(Rental each: rentals) {
			double thisAmount = calculateAmountForRental(each);

			// Add frequent renter points
			frequentRenterPoints++;

			// Add bonus for a two-day new-release rental
			if ((each.getMovie().getPriceCode() == PriceCodes.NEW_RELEASE) && (each.getDaysRented() > 1))
			{
				frequentRenterPoints ++;
			}

			// Show figures for this rental
			rentalReport += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}


		return generateStatement(totalAmount, frequentRenterPoints, rentalReport);
	}

	@VisibleForTesting
	double calculateAmountForRental(Rental rental) {
		double thisAmount = 0;

		// Determine amounts for each line
		switch(rental.getMovie().getPriceCode()) {
            case REGULAR:
                thisAmount += 2;
                if (rental.getDaysRented() > 2)
                {
                    thisAmount += (rental.getDaysRented() - 2) * 1.5;
                }
                break;

            case NEW_RELEASE:
                thisAmount += rental.getDaysRented() * 3;
                break;

            case CHILDRENS:
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
		String result = "Rental record for " + name + "\n";
		result += rentalReport;
		// Add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points.";
		return result;
	}
}

