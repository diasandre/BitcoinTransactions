package service.Impl;

import api.ApiConsumer;
import constants.TradeType;
import me.andrz.builder.map.MapBuilder;
import model.Trade;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import service.TradeDataService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TradeDataServiceImpl implements TradeDataService {
    private Collection<Trade> trades;

    public TradeDataServiceImpl(Collection<Trade> trades) {
        this.trades = trades;
    }

    public Collection<Trade> getBiggestTrades(String tradeType, int limit) {
        Stream<Trade> filteredTrades = filterTrades(tradeType);
        if (filteredTrades != null) {
            return filteredTrades
                    .sorted(Comparator.comparing(Trade::getValue).reversed())
                    .limit(limit)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public double getAverage(String tradeType) {
        if(trades.isEmpty()){
            return Double.NaN;
        }

        Stream<Trade> filteredTrades = filterTrades(tradeType);
        if (filteredTrades != null) {
            return filteredTrades
                    .mapToDouble(item -> item.getValue().doubleValue())
                    .average().orElse(Double.NaN);
        } else {
            return Double.NaN;
        }

    }

    public double getMedian(String tradeType) {
        if(trades.isEmpty()){
            return Double.NaN;
        }

        Stream<Trade> filteredTrades = filterTrades(tradeType);
        if (filteredTrades != null) {
            List<BigDecimal> mappedTrades = filteredTrades
                    .map(Trade::getValue)
                    .collect(Collectors.toList());

            BigDecimal[] values = mappedTrades.toArray(new BigDecimal[mappedTrades.size()]);

            Arrays.sort(values);

            int middle = values.length / 2;
            BigDecimal medianValue;
            if (values.length % 2 == 1)
                medianValue = values[middle];
            else
                medianValue = (values[middle - 1].add(values[middle])).divideToIntegralValue(new BigDecimal(2));

            return medianValue.doubleValue();
        } else {
            return Double.NaN;
        }
    }

    public double getDeviation(String tradeType) {
        if(trades.isEmpty()){
            return Double.NaN;
        }

        StandardDeviation standardDeviation = new StandardDeviation();
        List<Double> collect = trades.stream()
                .filter((item -> item.getType().equals(tradeType)))
                .map(item -> item.getValue().doubleValue())
                .collect(Collectors.toList());

        Double[] values = collect.toArray(new Double[collect.size()]);

        return standardDeviation.evaluate(ArrayUtils.toPrimitive(values));
    }


    public Map<String, Object> getFormattedData() {
        return new MapBuilder<String, Object>()
                .p("largest_sell", getBiggestTrades(TradeType.SELL.getValue(), 5))
                .p("largest_buy", getBiggestTrades(TradeType.BUY.getValue(), 5))
                .p("average_sell", getAverage(TradeType.SELL.getValue()))
                .p("average_buy", getAverage(TradeType.BUY.getValue()))
                .p("median_sell", getMedian(TradeType.BUY.getValue()))
                .p("median_buy", getMedian(TradeType.BUY.getValue()))
                .p("deviation_sell", getDeviation(TradeType.BUY.getValue()))
                .p("deviation_buy", getDeviation(TradeType.BUY.getValue()))
                .build();
    }

    private Stream<Trade> filterTrades(String tradeType) {
        if (isValid(tradeType)) {
            return trades.stream()
                    .filter((item -> item.getType().equals(tradeType)));
        } else {
            return null;
        }
    }

    private boolean isValid(String tradeType) {
        return tradeType.equals(TradeType.SELL.getValue()) || tradeType.equals(TradeType.BUY.getValue());
    }

    public TradeDataServiceImpl onChangeTime(Long fromDate, Long toDate) {
        this.trades = new ApiConsumer().read(fromDate, toDate);
        return this;
    }
}
