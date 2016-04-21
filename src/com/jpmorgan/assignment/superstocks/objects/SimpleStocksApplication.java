package com.jpmorgan.assignment.superstocks.objects;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.jpmorgan.assignment.superstocks.enums.SimpleStockType;
import com.jpmorgan.assignment.superstocks.enums.StockBuyOrSellIndicator;

public class SimpleStocksApplication {

	public static void main(String[] args) {

		//Sample data given
		Map<String, SimpleStock> stocks  = new HashMap<String, SimpleStock>();
	        stocks.put("TEA", SimpleStock.getNewSimpleStock("TEA", SimpleStockType.COMMON, 0.0, 0.0, 100.0));
	        stocks.put("POP", SimpleStock.getNewSimpleStock("POP", SimpleStockType.COMMON, 8.0, 0.0, 100.0));
	        stocks.put("ALE", SimpleStock.getNewSimpleStock("ALE", SimpleStockType.COMMON, 23.0, 0.0, 60.0));
	        stocks.put("GIN", SimpleStock.getNewSimpleStock("GIN", SimpleStockType.PREFERRED, 8.0, 0.2, 100.0));
	        stocks.put("JOE", SimpleStock.getNewSimpleStock("JOE", SimpleStockType.COMMON, 13.0, 0.0, 250.0));
	        
	        //do calculations
	        DecimalFormat decimalFormat = new DecimalFormat("#.00");
	        for (SimpleStock stock:stocks.values()){
	        	StockOperations stockOperations = new StockOperations(stock);
	        	System.out.println(stock.getStockName() +" dividend yield: "+ stockOperations.calculateStockDividentYield(5.0));
	        	System.out.println(stock.getStockName() +" P/E Ratio: "+ stockOperations.calculateStockDividentYield(5.0));
	        	
	        	Random r = new Random();
	        	for (int i=1; i <= 10; i++) {
            		Integer rangeMin = 1;
            		Integer rangeMax = 10;
            		double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            		StockTrades tradeBuy = new StockTrades(i, StockBuyOrSellIndicator.BUY, randomValue, new Date());
            		stock.buyAStock(tradeBuy);
            		System.out.println( stock.getStockName() + " bought " + i + " shares in value: " + decimalFormat.format(randomValue)+"p");
            		randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            		StockTrades tradeSell = new StockTrades(i, StockBuyOrSellIndicator.SELL, randomValue, new Date());
            		stock.sellAStock(tradeSell);
            		System.out.println( stock.getStockName() + " sold " + i + " shares in value: " + decimalFormat.format(randomValue)+"p");
            	}
	        	System.out.println("Stock price based on trades recorded last 15 minutes is: "+decimalFormat.format(stockOperations.calculateStockPriceBasedOnlLastQuarterRecordedTrades()));
	        }
	        System.out.println("CBGE All share index for all stocks is: "+decimalFormat.format(SimpleStocksCBGE.calculateTheGBCEUsingTheGeometricMean(stocks)));
	}

}
