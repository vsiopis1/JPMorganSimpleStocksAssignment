package com.jpmorgan.assignment.superstocks.objects;

import java.util.Date;
import java.util.TreeMap;

import com.jpmorgan.assignment.superstocks.enums.SimpleStockType;

/**
 * Simple Stock declaration object
 * @author vaios
 *
 */
public class SimpleStock {
	
	private String stockName;
	private SimpleStockType stockType;
	private Double lastDivident;
	private Double fixedDivident;
    private Double parValue;
	private TreeMap<Date,StockTrades> trades;
	
	private SimpleStock(final String stockName , final SimpleStockType stockType, 
			            final Double lastDivident, final Double fixedDivident, final Double parValue ){
		
		this.stockName = stockName;
		this.stockType = stockType;
		this.lastDivident = lastDivident;
		this.fixedDivident = fixedDivident;
		this.parValue = parValue;
		this.trades = new TreeMap<Date, StockTrades>();
	
	}

	/**Static Factory of Simple Stocks
	 * 
	 * @param stockName
	 * @param stockType
	 * @param lastDivident
	 * @param fixedDivident
	 * @param parValue
	 * @return
	 */
	public static SimpleStock getNewSimpleStock(final String stockName , final SimpleStockType stockType,
												final Double lastDivident, final Double fixedDivident, 
												final Double parValue){
		
		return new SimpleStock(stockName, stockType, lastDivident, fixedDivident,parValue);
	}

	/**Do a new Trade. Buy Stocks
	 * 
	 * @param trade
	 */

	public void buyAStock(final StockTrades trade ){
		trades.put(trade.getTimestamp(), trade);
	}
	
	/**Do a new Trade. Sell Stocks
	 * 
	 * @param trade
	 */
	
	public void sellAStock(final StockTrades trade){		
		trades.put(trade.getTimestamp(),trade);
	}
	
	/**
	 * 
	 * @return The last trade price
	 */
	public Double getStockPrice() {
		
		if (this.trades.size() > 0) 
			return this.trades.lastEntry().getValue().getPrice();

		return 0.0d;
		
	}
	
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public SimpleStockType getStockType() {
		return stockType;
	}

	public void setStockType(SimpleStockType stockType) {
		this.stockType = stockType;
	}

	public Double getLastDivident() {
		return lastDivident;
	}

	public void setLastDivident(Double lastDivident) {
		this.lastDivident = lastDivident;
	}

	public Double getFixedDivident() {
		return fixedDivident;
	}

	public void setFixedDivident(Double fixedDivident) {
		this.fixedDivident = fixedDivident;
	}

	public TreeMap<Date,StockTrades> getTrades() {
		return trades;
	}

	public void setTrades(TreeMap<Date,StockTrades> trades) {
		this.trades = trades;
	}

	public Double getParValue() {
		return parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}
	
}
