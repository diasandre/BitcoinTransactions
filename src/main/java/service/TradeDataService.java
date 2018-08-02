package service;

import model.TradeItem;
import model.Trades;

import java.util.Collection;

public interface TradeDataService {

    Trades getFormattedData();
    Collection<TradeItem> getBiggestTrades(String tradeType, int limit);
    Double getAverage(String tradeType);

}
