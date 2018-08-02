package service;

import model.TradeItem;

import java.util.Collection;
import java.util.Map;

public interface TradeDataService {

    Collection<TradeItem> getBiggestTrades(String tradeType, int limit);
    Map<String, Object> getFormattedData();

}
