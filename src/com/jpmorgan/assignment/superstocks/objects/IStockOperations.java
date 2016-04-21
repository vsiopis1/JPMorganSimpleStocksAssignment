package com.jpmorgan.assignment.superstocks.objects;

/**
 * Interface privides calculation operation for a given Simple Stock
 * @author vaios
 *
 */
public interface IStockOperations {

	Double calculateStockDividentYield(final Double stockPrice );
	Double calculateStockPERatio(final Double stockPrice);
	void addANewStockTrade(final StockTrades trade);
	Double calculateStockPriceBasedOnlLastQuarterRecordedTrades();
}
