package com.scrumtrek.simplestore;


import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest{
    private String testString = "TEST";

    @Test
    public void testGetName() {
        final Customer customer = new Customer(testString);
        assertEquals(customer.getName(), testString);
    }

    @Test
    public void testStatement() {
        final Customer customer = new Customer(testString);
        final String title = "title";
        final Movie movie = new Movie(title, PriceCodes.NewRelease);
        final Rental rental = new Rental(movie, 10);

        customer.addRental(rental);

        final String statement = customer.Statement();
        assertTrue(statement.contains(title + "\t"+ 30));

        assertTrue(customer.getName().equals(testString));

    }

    @Test
    public void testStatement1() {
        // Create movies
        Movie movCinderella = new Movie("Cinderella", PriceCodes.Childrens);
        Movie movStarWars = new Movie("Star Wars", PriceCodes.Regular);
        Movie movGladiator = new Movie("Gladiator", PriceCodes.NewRelease);

        // Create customers
        Customer custMickeyMouse = new Customer("Mickey Mouse");

        // Create rentals
        Rental rental1 = new Rental(movCinderella, 5);
        Rental rental2 = new Rental(movStarWars, 5);
        Rental rental3 = new Rental(movGladiator, 5);

        // Assign rentals to customers
        custMickeyMouse.addRental(rental1);
        custMickeyMouse.addRental(rental2);
        custMickeyMouse.addRental(rental3);

        String statement = custMickeyMouse.Statement();

        assertEquals(statement,
                "Rental record for Mickey Mouse\n" +
                        "\tCinderella\t3.0\n" +
                        "\tStar Wars\t6.5\n" +
                        "\tGladiator\t15.0\n" +
                        "Amount owed is 24.5\n" +
                        "You earned 4 frequent renter points.");
    }

    @Test
    public void testPriceCodes() throws Exception {


    }
}
