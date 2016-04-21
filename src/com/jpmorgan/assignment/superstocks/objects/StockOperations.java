package com.jpmorgan.assignment.superstocks.objects;

import java.util.Date;
import java.util.SortedMap;

/**
 * IStockOperations interface implementation
 * @author vaios
 *
 */
public class StockOperations implements IStockOperations {

	private SimpleStock stock;
	
     
	public StockOperations(final SimpleStock stock) {
		super();
		this.stock = stock;
	}

	@Override
	public Double calculateStockDividentYield(final Double stockPrice) {
		  
		    switch(stock.getStockType()){
		    case COMMON: 
		    	return (stock.getLastDivident()/stockPrice);
		    case PREFERRED: 
		    	return ((stock.getFixedDivident()*stock.getParValue())/stockPrice);
		    default: 
		    	return 0.0;
		    }
		
	}

	@Override
	public Double calculateStockPERatio(final Double stockPrice) {
		
		return (stockPrice/stock.getLastDivident());
	}

	@Override
	public void addANewStockTrade(final StockTrades trade) {
		switch(trade.getIndicator()){
		case BUY: 
			stock.buyAStock(trade);
		case SELL:
			stock.sellAStock(trade);
		}
		
	}

	/**
	 * Get trades 15minutes ago and calculate stock price based on these trades
	 */
	@Override
	public Double calculateStockPriceBasedOnlLastQuarterRecordedTrades() {
		
		Date fifteenMinutesAgo = new Date((new Date()).getTime() - (15*60*1000)); 
		SortedMap<Date,StockTrades> trades = stock.getTrades().tailMap(fifteenMinutesAgo);
		
		int totalQuantity = 0;
		Double sumOfTradepricesMultiplyQuantity = 0.0d;
		for(StockTrades trade:trades.values()){
			totalQuantity += trade.getQuantityOfShares();
			sumOfTradepricesMultiplyQuantity += trade.getPrice()*trade.getQuantityOfShares();
		}
		return sumOfTradepricesMultiplyQuantity/totalQuantity;
	}

	
}
