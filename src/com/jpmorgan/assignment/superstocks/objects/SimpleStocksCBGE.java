package com.jpmorgan.assignment.superstocks.objects;

import java.util.Map;

/**
 * 
 * @author vaios
 *
 */
public class SimpleStocksCBGE {

	/**
	 * Calculate the GBCE All Share Index for all stocks
	 * @return The GBCE All Share Index
	 */
	public static Double calculateTheGBCEUsingTheGeometricMean(final Map<String, SimpleStock> stocks){		
			Double allShareIndex = 0.0;
			
			for(SimpleStock stock: stocks.values()) {
				allShareIndex+=stock.getStockPrice();
			}
			return Math.pow(allShareIndex, 1.0 / stocks.size());
		}	
}

