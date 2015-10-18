package com.scrumtrek.simplestore;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Alexey on 18.10.2015.
 */
public class StatementTest {
    private String CUSTOMER_NAME = "CUSTOMER_NAME";
    private static final String TITLE = "title";

    @Test
    public void testNewRelease() {

        final String pr1 = "Rental record for "+CUSTOMER_NAME+"\n"
          + "\ttitle\t2.0\n"
          + "Amount owed is 2.0\n"
          + "You earned 1 frequent renter points.";

        final String pr2 = "Rental record for CUSTOMER_NAME\n"
          + "\ttitle\t3.0\n"
          + "Amount owed is 3.0\n"
          + "You earned 1 frequent renter points.";

        final String pr3 = "Rental record for CUSTOMER_NAME\n"
          + "\ttitle\t1.5\n"
          + "Amount owed is 1.5\n"
          + "You earned 1 frequent renter points.";

        String[] results = new String[]{pr1, pr2, pr3};

        for (PriceCodes priceCode : PriceCodes.values()) {
            final Customer customer = new Customer(CUSTOMER_NAME);

            for (int day = 1; day < 2; day++) {
                final Rental rental = new Rental(new Movie(TITLE, priceCode), day);

                customer.addRental(rental);

                final String statement = customer.Statement();
                System.out.println("statement = " + statement);

//                assertTrue(statement.equals(results[day - 1]));
                assertTrue(statement.equals(results[priceCode.ordinal()]));
            }
        }
    }


}
