package com.scrumtrek.simplestore;

import com.scrumtrek.simplestore.statement.strategies.StatementComputingStrategy;
import com.scrumtrek.simplestore.statement.strategies.StatementComputingStrategyChildren;
import com.scrumtrek.simplestore.statement.strategies.StatementComputingStrategyRegular;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    private Movie movie;
    private StatementComputingStrategy computingStrategyChildren = new StatementComputingStrategyChildren();
    private String title = "title";

    @Before
    public void before() {
        movie = new Movie(title, computingStrategyChildren);
    }

    @Test
    public void testComputingStrategy() {
        assertEquals(movie.getComputingStrategy(), computingStrategyChildren);
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
