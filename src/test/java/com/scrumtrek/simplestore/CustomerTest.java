package com.scrumtrek.simplestore;


import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest{
    private String CUSTOMER_NAME = "CUSTOMER_NAME";
    private static final String TITLE = "title";

    @Test
    public void testGetName() {
        final Customer customer = new Customer(CUSTOMER_NAME);
        assertEquals(customer.getName(), CUSTOMER_NAME);
    }

    @Test
    public void testNewRelease() {
        final Customer customer = new Customer(CUSTOMER_NAME);
        final Rental rental = getRental(new StatementComputingStrategyNewRelease());

        customer.addRental(rental);

        final String statement = customer.statement();
        assertTrue(statement.contains(TITLE + "\t"+ 30));

        assertTrue(customer.getName().equals(CUSTOMER_NAME));
    }

    private Rental getRental(StatementComputingStrategy newRelease) {
        final Movie movie = new Movie(TITLE, newRelease);
        return new Rental(movie, 10);
    }

    @Test
    public void testStatement() {
        // Create movies
        Movie movCinderella = new Movie("Cinderella", new StatementComputingStrategyChildren());
        Movie movStarWars = new Movie("Star Wars", new StatementComputingStrategyRegular());
        Movie movGladiator = new Movie("Gladiator", new StatementComputingStrategyNewRelease());

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

        String statement = custMickeyMouse.statement();

        final String actualStatement = "Rental record for Mickey Mouse\n" +
          "\tCinderella\t3.0\n" +
          "\tStar Wars\t6.5\n" +
          "\tGladiator\t15.0\n" +
          "Amount owed is 24.5\n" +
          "You earned 4 frequent renter points.";

        assertEquals(statement, actualStatement);
    }

    @Test
    public void testRegular() throws Exception {


    }
}
