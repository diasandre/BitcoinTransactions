package service;

import model.Trade;

import java.util.Collection;
import java.util.Map;

public interface TradeDataService {

    Map<String, Object> getFormattedData();

    Collection<Trade> getBiggestTrades(String tradeType, int limit);
    double getAverage(String tradeType);
    double getMedian(String tradeType);
    double getDeviation(String tradeType);

}
