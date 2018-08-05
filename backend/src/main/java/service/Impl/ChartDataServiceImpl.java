package service.Impl;

import me.andrz.builder.map.MapBuilder;
import model.TradeItem;
import service.ChartDataService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartDataServiceImpl implements ChartDataService {

    private Collection<TradeItem> trades;

    public ChartDataServiceImpl(Collection<TradeItem> trades) {
        this.trades = trades;
    }

    private Collection<BigDecimal> getPrice(String tradeType) {
        return this.trades.stream()
                .filter(item -> item.getType().equals(tradeType))
                .map(TradeItem::getPrice).collect(Collectors.toList());
    }

    private Collection<Long> getDate(String tradeType) {
        return this.trades.stream()
                .filter(item -> item.getType().equals(tradeType))
                .map(TradeItem::getDate).collect(Collectors.toList());
    }

    public Map<String, Object> getChartData(String tradeType) {
        return new MapBuilder<String, Object>()
                .p("labels", getDate(tradeType))
                .p("datasets", Collections.singletonList(
                        new MapBuilder<String, Object>()
                                .p("label", "Price")
                                .p("data", getPrice(tradeType))
                                .p("backgroundColor", "#FBBA16")
                                .build()
                ))
                .build();
    }

    //GENERATED

    public Collection<TradeItem> getTrades() {
        return trades;
    }
}
