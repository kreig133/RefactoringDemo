package com.scrumtrek.simplestore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieTest {

    private Movie movie;
    private StatementComputingStrategy childrens = new StatementComputingStrategyChildren();
    private String title = "title";

    @Before
    public void before() {
        movie = new Movie(title, childrens);
    }

    @Test
    public void testComputingStrategy() {
        assertEquals(movie.getComputingStrategy(), childrens);
    }

    @Test
    public void testSetComputingStrategy() {
        StatementComputingStrategy computingStrategy = new StatementComputingStrategyRegular();
        movie.setComputingStrategy(computingStrategy);
        assertEquals(movie.getComputingStrategy(), computingStrategy);
    }

    @Test
    public void testGetTitle() throws Exception {
        assertEquals(movie.getTitle(), title);
    }
}
