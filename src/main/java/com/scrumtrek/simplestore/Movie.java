package com.scrumtrek.simplestore;

/**
 * This class incapsulates movie
 */
public class Movie {
    private String title;
    private StatementComputingStrategy computingStrategy;

    public Movie(String title, StatementComputingStrategy computingStrategy) {
        this.title = title;
        this.computingStrategy = computingStrategy;
    }

    public StatementComputingStrategy getComputingStrategy() {
        return computingStrategy;
    }

    public void setComputingStrategy(StatementComputingStrategy value) {
        computingStrategy = value;
    }

    public String getTitle() {
        return title;
    }
}

