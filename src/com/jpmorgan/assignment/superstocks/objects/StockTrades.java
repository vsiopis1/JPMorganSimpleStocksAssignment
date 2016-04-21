package com.jpmorgan.assignment.superstocks.objects;

import java.util.Date;

import com.jpmorgan.assignment.superstocks.enums.StockBuyOrSellIndicator;

/**
 * Stock trades records
 * @author vaios
 *
 */
public class StockTrades {

	private Integer quantityOfShares;
	private StockBuyOrSellIndicator indicator;
	private Double price;
	private Date timestamp;

		
	public StockTrades(final Integer quantityOfShares,
			final StockBuyOrSellIndicator indicator, final Double price, final Date timestamp) {
		super();
		this.quantityOfShares = quantityOfShares;
		this.indicator = indicator;
		this.price = price;
		this.timestamp = timestamp;
	}


	public Integer getQuantityOfShares() {
		return quantityOfShares;
	}
	public void setQuantityOfShares(Integer quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}
	public StockBuyOrSellIndicator getIndicator() {
		return indicator;
	}
	public void setIndicator(StockBuyOrSellIndicator indicator) {
		this.indicator = indicator;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
