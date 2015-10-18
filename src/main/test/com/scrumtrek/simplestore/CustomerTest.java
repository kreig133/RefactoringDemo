package com.scrumtrek.simplestore;


import org.junit.Assert;
import org.junit.Test;

public class CustomerTest{
    private String testString = "TEST";


    @Test
    public void testGetName() {
        Customer customer = new Customer(testString);
        Assert.assertEquals(customer.getName(), testString);
    }

    @Test
    public void testStatement() {
        Customer customer = new Customer(testString);
        customer.addRental(new Rental(new Movie("title", PriceCodes.NewRelease), 10));

    }
}
