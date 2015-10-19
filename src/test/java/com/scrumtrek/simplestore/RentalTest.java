package com.scrumtrek.simplestore;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RentalTest {

    private Movie movieMock = Mockito.mock(Movie.class);

    @Test
    public void testRental() {
        final int daysRented = 5;
        final Rental rental = new Rental(movieMock, daysRented);

        assertTrue(rental.getDaysRented() == daysRented);
        assertNotNull(rental.getMovie());
    }

}
