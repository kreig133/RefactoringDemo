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
        final PriceCodes newRelease = PriceCodes.NewRelease;
        final Rental rental = getRental(newRelease);

        customer.addRental(rental);

        final String statement = customer.Statement();
        assertTrue(statement.contains(TITLE + "\t"+ 30));

        assertTrue(customer.getName().equals(CUSTOMER_NAME));
    }

    private Rental getRental(PriceCodes newRelease) {
        final Movie movie = new Movie(TITLE, newRelease);
        return new Rental(movie, 10);
    }

    @Test
    public void testRegular() throws Exception {


    }
}
