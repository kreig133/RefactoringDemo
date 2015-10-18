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
    public void testNewRelease() {
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
    public void testPriceCodes() throws Exception {


    }
}
