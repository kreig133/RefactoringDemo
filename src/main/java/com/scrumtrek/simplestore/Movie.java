package com.scrumtrek.simplestore;

/**
 * This class incapsulates movie
 */
public class Movie {
	private String title;
	private StatementComputing priceCode;

	public Movie(String title, StatementComputing priceCode) {
		this.title = title;
		this.priceCode = priceCode;
	}

	public StatementComputing getPriceCode()	{
		return priceCode;
	}
	
	public void setPriceCode(StatementComputing value) {
		priceCode = value;
	}

	public String getTitle() {
		return title;
	}
}

