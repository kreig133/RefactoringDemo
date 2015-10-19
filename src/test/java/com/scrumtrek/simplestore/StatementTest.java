package com.scrumtrek.simplestore;

import com.scrumtrek.simplestore.statement.StatementGenerator;
import com.scrumtrek.simplestore.statement.strategies.StatementComputingStrategy;
import com.scrumtrek.simplestore.statement.strategies.StatementComputingStrategyChildren;
import com.scrumtrek.simplestore.statement.strategies.StatementComputingStrategyNewRelease;
import com.scrumtrek.simplestore.statement.strategies.StatementComputingStrategyRegular;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class StatementTest {
    private static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    private static final String TITLE = "title";
    private StatementComputingStrategy inputStrategy;
    private int inputDays;
    private String expectedResultStatement;

    public StatementTest(StatementComputingStrategy input, int days, String expected) {
        inputStrategy = input;
        inputDays = days;
        expectedResultStatement = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
          {
            new StatementComputingStrategyRegular(),
            1,
            getExpectedResult("2.0", "2.0", 1)
          },
          {
            new StatementComputingStrategyNewRelease(),
            1,
            getExpectedResult("3.0", "3.0", 1),
          },
          {
            new StatementComputingStrategyChildren(),
            1,
            getExpectedResult("1.5", "1.5", 1)
          },
          {
            new StatementComputingStrategyRegular(),
            4,
            getExpectedResult("5.0", "5.0", 1)
          },
          {
            new StatementComputingStrategyNewRelease(),
            4,
            getExpectedResult("12.0", "12.0", 2),
          },
          {
            new StatementComputingStrategyChildren(),
            4,
            getExpectedResult("1.5", "1.5", 1)
          }
        });
    }

    private static String getExpectedResult(String num, String amount, int points) {
        return "Rental record for " + CUSTOMER_NAME + "\n"
          + "\ttitle\t" + num + "\n"
          + "Amount owed is " + amount + "\n"
          + "You earned " + points + " frequent renter points.";
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

        String statement = StatementGenerator.generateStatement(custMickeyMouse);

        final String actualStatement = "Rental record for Mickey Mouse\n" +
          "\tCinderella\t3.0\n" +
          "\tStar Wars\t6.5\n" +
          "\tGladiator\t15.0\n" +
          "Amount owed is 24.5\n" +
          "You earned 4 frequent renter points.";

        assertEquals(statement, actualStatement);
    }

    //    @Test
    //    public void testOneDay() {
    //        final String expectedResult1 = getExpectedResult("2.0", "2.0", 1);
    //        final String expectedResult2 = getExpectedResult("3.0", "3.0", 1);
    //        final String expectedResult3 = getExpectedResult("1.5", "1.5", 1);
    //
    //        checkStrategies(new String[] { expectedResult1, expectedResult2, expectedResult3 }, 1);
    //    }
    //
    //    @Test
    //    public void testFourDays() {
    //        final String pr11 = getExpectedResult("5.0", "5.0", 1);
    //        final String pr21 = getExpectedResult("12.0", "12.0", 2);
    //        final String pr31 = getExpectedResult("1.5", "1.5", 1);
    //
    //        checkStrategies(new String[] { pr11, pr21, pr31 }, 4);
    //    }
    //
    //    private void checkStrategies(String[] results, int day) {
    //        checkResultForStrategyAndDays(results[0], day, new StatementComputingStrategyRegular());
    //        checkResultForStrategyAndDays(results[1], day, new StatementComputingStrategyNewRelease());
    //        checkResultForStrategyAndDays(results[2], day, new StatementComputingStrategyChildren());
    //    }

    @Test
    public void test() {
        checkResultForStrategyAndDays(expectedResultStatement, inputDays, inputStrategy);
    }

    private void checkResultForStrategyAndDays(String result, int day, StatementComputingStrategy computingStrategy) {
        final Rental rental = new Rental(new Movie(TITLE, computingStrategy), day);
        final Customer customer = new Customer(CUSTOMER_NAME);
        customer.addRental(rental);

        final String statement = StatementGenerator.generateStatement(customer);

        assertTrue(statement.equals(result));
    }
}
