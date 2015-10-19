package com.scrumtrek.simplestore;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    private static final String CUSTOMER_NAME = "CUSTOMER_NAME";

    @Test
    public void testGetName() {
        final Customer customer = new Customer(CUSTOMER_NAME);
        assertEquals(customer.getName(), CUSTOMER_NAME);
    }
}
