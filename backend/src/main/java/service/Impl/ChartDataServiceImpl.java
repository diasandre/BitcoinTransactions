package service.Impl;

import api.ApiConsumer;
import me.andrz.builder.map.MapBuilder;
import model.Trade;
import service.ChartDataService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartDataServiceImpl implements ChartDataService {

    private Collection<Trade> trades;

    public ChartDataServiceImpl(Collection<Trade> trades) {
        this.trades = trades;
    }

    private Collection<BigDecimal> getPrice(String tradeType) {
        return this.trades.stream()
                .filter(item -> item.getType().equals(tradeType))
                .map(Trade::getPrice).collect(Collectors.toList());
    }

    private Collection<Long> getDate(String tradeType) {
        return this.trades.stream()
                .filter(item -> item.getType().equals(tradeType))
                .map(Trade::getDate).collect(Collectors.toList());
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

    public ChartDataServiceImpl onChangeTime(Long fromDate, Long toDate) {
        this.trades = new ApiConsumer().read(fromDate, toDate);
        return this;
    }

    public Collection<Trade> getTrades() {
        return trades;
    }
}
