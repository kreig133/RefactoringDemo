package com.scrumtrek.simplestore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    private Movie movie;
    private PriceCodes childrens = PriceCodes.Childrens;
    private String title = "title";

    @Before
    public void before() {
        movie = new Movie(title, childrens);
    }

    @Test
    public void testGetPriceCode() {
        assertEquals(movie.getPriceCode(), childrens);
    }

    @Test
    public void testSetPriceCode() {
        PriceCodes newPriceCode = PriceCodes.Regular;
        movie.setPriceCode(newPriceCode);
        assertEquals(movie.getPriceCode(), newPriceCode);
    }

    @Test
    public void testGetTitle() throws Exception {
        assertEquals(movie.getTitle(), title);
    }
}