package com.scrumtrek.simplestore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatementTest {
    private String CUSTOMER_NAME = "CUSTOMER_NAME";
    private static final String TITLE = "title";

    @Test
    public void testNewRelease() {
        final String pr1 = getPr("2.0","2.0",1);
        final String pr2 = getPr("3.0",  "3.0" , 1);
        final String pr3 = getPr("1.5", "1.5", 1);

        testState(new String[]{pr1, pr2, pr3}, 1);

        final String pr11 = getPr("5.0","5.0",1);
        final String pr21 = getPr("12.0",  "12.0" , 2);
        final String pr31 = getPr("1.5", "1.5", 1);

        testState(new String[]{pr11, pr21, pr31}, 4);
    }

    private void testState(String[] results, int day) {
        for (PriceCodes priceCode : PriceCodes.values()) {
            final Rental rental = new Rental(new Movie(TITLE, priceCode), day);
            final Customer customer = new Customer(CUSTOMER_NAME);
            customer.addRental(rental);

            final String statement = customer.Statement();
            System.out.println("statement = " + statement);

            assertTrue(statement.equals(results[priceCode.ordinal()]));
        }
    }

    private String getPr(String num, String amount, int points) {
        return "Rental record for "+CUSTOMER_NAME+"\n"
              + "\ttitle\t" + num + "\n"
              + "Amount owed is " + amount + "\n"
              + "You earned "+points+" frequent renter points.";
    }
}
