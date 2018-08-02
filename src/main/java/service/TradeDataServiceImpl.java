package service;

import model.TradeItem;
import model.Trades;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TradeDataServiceImpl implements TradeDataService {

    private final static String TRADE_TYPE_SELL = "sell";
    private final static String TRADE_TYPE_BUY = "buy";

    private Collection<TradeItem> trades;

    public TradeDataServiceImpl(Collection<TradeItem> trades) {
        this.trades = trades;
    }

    public Collection<TradeItem> getBiggestTrades(String tradeType, int limit) {
        if (isValid(tradeType)) {
            return trades.stream()
                    .filter((item -> item.getType().equals(tradeType)))
                    .sorted(Comparator.comparing(TradeItem::getValue).reversed())
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private boolean isValid(String tradeType) {
        return tradeType.equals(TRADE_TYPE_SELL) || tradeType.equals(TRADE_TYPE_BUY);
    }

    public Double getAverage(String tradeType) {
        return trades.stream()
                .filter((item -> item.getType().equals(tradeType)))
                .mapToDouble(item -> item.getValue().doubleValue())
                .average().getAsDouble();
    }

    public Trades getFormattedData() {
        return new Trades()
                .setLargest_buy(getBiggestTrades(TRADE_TYPE_BUY, 5))
                .setLargest_sell(getBiggestTrades(TRADE_TYPE_SELL, 5))
                .setAverage_buy(getAverage(TRADE_TYPE_BUY))
                .setAverage_sell(getAverage(TRADE_TYPE_SELL));
    }
}
