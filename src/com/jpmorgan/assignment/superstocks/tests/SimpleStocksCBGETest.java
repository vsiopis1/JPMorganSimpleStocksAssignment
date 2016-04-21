package com.jpmorgan.assignment.superstocks.tests;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;

import org.junit.Test;

import com.jpmorgan.assignment.superstocks.enums.SimpleStockType;
import com.jpmorgan.assignment.superstocks.enums.StockBuyOrSellIndicator;
import com.jpmorgan.assignment.superstocks.objects.SimpleStock;
import com.jpmorgan.assignment.superstocks.objects.SimpleStocksCBGE;
import com.jpmorgan.assignment.superstocks.objects.StockTrades;

public class SimpleStocksCBGETest {

	@Test
	public void testAllShareIndex() {
        HashMap<String, SimpleStock> stocks = new HashMap<String, SimpleStock>();
        stocks.put("TEA", SimpleStock.getNewSimpleStock("TEA", SimpleStockType.COMMON, 0.0, 0.0, 100.0));
        stocks.put("POP", SimpleStock.getNewSimpleStock("POP", SimpleStockType.COMMON, 8.0, 0.0, 100.0));
        stocks.put("ALE", SimpleStock.getNewSimpleStock("ALE", SimpleStockType.COMMON, 23.0, 0.0, 60.0));
        stocks.put("GIN", SimpleStock.getNewSimpleStock("GIN", SimpleStockType.PREFERRED, 8.0, 0.2, 100.0));
        stocks.put("JOE", SimpleStock.getNewSimpleStock("JOE", SimpleStockType.COMMON, 13.0, 0.0, 250.0));
        for (SimpleStock stock: stocks.values()) {
            //Add trades
        	for (int i=1; i <= 10; i++) {
        		StockTrades tradeBuy = new StockTrades(1, StockBuyOrSellIndicator.BUY, 1.0, new Date());
        		stock.buyAStock(tradeBuy);
        		StockTrades tradeSell = new StockTrades(1, StockBuyOrSellIndicator.SELL, 1.0, new Date());
        		stock.sellAStock(tradeSell);
        	}
        }
        Double result = SimpleStocksCBGE.calculateTheGBCEUsingTheGeometricMean(stocks);
        assertEquals(1.379729661461215, result, 0.0);
	}

}
