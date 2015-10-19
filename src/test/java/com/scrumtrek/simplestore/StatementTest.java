package com.scrumtrek.simplestore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StatementTest {
    private String CUSTOMER_NAME = "CUSTOMER_NAME";
    private static final String TITLE = "title";


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

        String statement = StatementGenerator.statement(custMickeyMouse);

        final String actualStatement = "Rental record for Mickey Mouse\n" +
                "\tCinderella\t3.0\n" +
                "\tStar Wars\t6.5\n" +
                "\tGladiator\t15.0\n" +
                "Amount owed is 24.5\n" +
                "You earned 4 frequent renter points.";

        assertEquals(statement, actualStatement);
    }

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

    // Helper methods
    private void testState(String[] results, int day) {
        testAll(results[0], day, new StatementComputingStrategyRegular());
        testAll(results[1], day, new StatementComputingStrategyNewRelease());
        testAll(results[2], day, new StatementComputingStrategyChildren());
    }

    private void testAll(String result, int day, StatementComputingStrategy computingStrategy) {
        final Rental rental = new Rental(new Movie(TITLE, computingStrategy), day);
        final Customer customer = new Customer(CUSTOMER_NAME);
        customer.addRental(rental);

        final String statement = StatementGenerator.statement(customer);
        System.out.println("statement = " + statement);

        assertTrue(statement.equals(result));
    }

    private String getPr(String num, String amount, int points) {
        return "Rental record for "+CUSTOMER_NAME+"\n"
              + "\ttitle\t" + num + "\n"
              + "Amount owed is " + amount + "\n"
              + "You earned "+points+" frequent renter points.";
    }
}
