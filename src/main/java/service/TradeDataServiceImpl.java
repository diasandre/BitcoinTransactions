package service;

import constants.JSONKeys;
import me.andrz.builder.map.MapBuilder;
import model.TradeItem;
import model.Trades;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class TradeDataServiceImpl implements TradeDataService {

    private final static String TRADE_TYPE_SELL = "sell";
    private final static String TRADE_TYPE_BUY = "buy";

    private Trades trades;

    public TradeDataServiceImpl(Trades trades) {
        this.trades = trades;
    }

    public Collection<TradeItem> getBiggestTrades(String tradeType, int limit) {
        if (isValid(tradeType)) {
            return trades.getTrade().stream()
                    .filter((item -> item.getType().equals(tradeType)))
                    .sorted(Comparator.comparing(TradeItem::getAmount))
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private boolean isValid(String tradeType) {
        return tradeType.equals(TRADE_TYPE_SELL) || tradeType.equals(TRADE_TYPE_BUY);
    }

    public Double getAverage(String tradeType) {
        return trades.getTrade().stream()
                .filter((item -> item.getType().equals(tradeType)))
                .mapToDouble(item -> item.getAmount().doubleValue() * item.getPrice().doubleValue())
                .average().getAsDouble();
    }

    public Map<String, Object> getFormattedData() {
        return new MapBuilder<String, Object>()
                .p(JSONKeys.LARGEST_SELL.getKey(), getBiggestTrades(TRADE_TYPE_SELL, 5))
                .p(JSONKeys.LARGEST_BUY.getKey(), getBiggestTrades(TRADE_TYPE_SELL, 5))
                .p(JSONKeys.AVERAGE_BUY.getKey(), getAverage(TRADE_TYPE_BUY))
                .p(JSONKeys.AVERAGE_SELL.getKey(), getAverage(TRADE_TYPE_SELL))

                .build();
    }
}
