package com.jpmorgan.assignment.superstocks.tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.jpmorgan.assignment.superstocks.enums.SimpleStockType;
import com.jpmorgan.assignment.superstocks.enums.StockBuyOrSellIndicator;
import com.jpmorgan.assignment.superstocks.objects.SimpleStock;
import com.jpmorgan.assignment.superstocks.objects.StockOperations;
import com.jpmorgan.assignment.superstocks.objects.StockTrades;

public class StockOperationsTest {

	@Test
	public void testDividendYield() {
		StockOperations stockPOP = new StockOperations(SimpleStock.getNewSimpleStock("POP", SimpleStockType.COMMON, 8.0, 0.0, 100.0));
        StockOperations stockGIN = new StockOperations(SimpleStock.getNewSimpleStock("GIN", SimpleStockType.PREFERRED, 8.0, 0.2, 100.0));
        // Test dividend for Common
		Double dividendPOP = stockPOP.calculateStockDividentYield(5.0);
		Double expectedDividendPOP = 8.0/5.0;
		assertEquals(expectedDividendPOP, dividendPOP, 0.0);
		// Test dividend for Preferred
		Double dividendGIN = stockGIN.calculateStockDividentYield(1.0);
		Double expectedDividendGIN =(0.2 * 100.0)/1.0;
		assertEquals(expectedDividendGIN, dividendGIN, 0.0);
	}

	@Test
	public void testPERatio() {
		StockOperations stockALE = new StockOperations(SimpleStock.getNewSimpleStock("ALE", SimpleStockType.COMMON,23.0, 0.0, 60.0));
        Double peRatioALE = stockALE.calculateStockPERatio(1.0);
        Double expectedPeRatioALE = 1.0/23.0;
        assertEquals(expectedPeRatioALE, peRatioALE, 0.0);
	}

	@Test
	public void testBuy() {
		SimpleStock stockALE = SimpleStock.getNewSimpleStock("ALE", SimpleStockType.COMMON,23.0, 0.0, 60.0);
		StockTrades tradeBuy = new StockTrades(5, StockBuyOrSellIndicator.BUY, 5.0, new Date());
		stockALE.buyAStock(tradeBuy);
		assertEquals(5.0, stockALE.getStockPrice(), 0.0);
	}

	@Test
	public void testSell() {
		SimpleStock stockALE = SimpleStock.getNewSimpleStock("ALE", SimpleStockType.COMMON,23.0, 0.0, 60.0);
		StockTrades tradeSell = new StockTrades(5, StockBuyOrSellIndicator.SELL, 5.0, new Date());
		stockALE.buyAStock(tradeSell);
		assertEquals(5.0, stockALE.getStockPrice(), 0.0);
	}

	@Test
	public void testCalculateVolumeWeightedStockPrice() {
		SimpleStock stockPOP = SimpleStock.getNewSimpleStock("POP", SimpleStockType.COMMON, 8.0, 0.0, 100.0);
		StockOperations stockOperation = new StockOperations(stockPOP);
		StockTrades tradeSell = new StockTrades(2, StockBuyOrSellIndicator.SELL, 10.0, new Date());
		stockPOP.sellAStock(tradeSell);
		StockTrades tradeBuy = new StockTrades(2, StockBuyOrSellIndicator.BUY, 10.0, new Date());
		stockPOP.buyAStock(tradeBuy);		
		Double lastMinutesPrice = stockOperation.calculateStockPriceBasedOnlLastQuarterRecordedTrades();
		assertEquals(10.0, lastMinutesPrice, 0.0);
		Date now = new Date();
		// Date 15 minutes ago
		Date startTime = new Date(now.getTime() - (15 * 60 * 1000));
		stockPOP.getTrades().put(startTime, new StockTrades(10,StockBuyOrSellIndicator.BUY , 10.0,startTime));
		// The new (outdated) trade should not affect calculation of the Volume Weighted Stock Price
		lastMinutesPrice = stockOperation.calculateStockPriceBasedOnlLastQuarterRecordedTrades();
		assertEquals(10.0, lastMinutesPrice, 0.0);
	}
}
