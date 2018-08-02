package service;

import constants.TradeType;
import model.TradeItem;
import model.Trades;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TradeDataServiceImpl implements TradeDataService {
    private Collection<TradeItem> trades;

    public TradeDataServiceImpl(Collection<TradeItem> trades) {
        this.trades = trades;
    }

    public Collection<TradeItem> getBiggestTrades(String tradeType, int limit) {
        Stream<TradeItem> filteredTrades = filterTrades(tradeType);
        if (filteredTrades != null) {
            return filteredTrades
                    .sorted(Comparator.comparing(TradeItem::getValue).reversed())
                    .limit(limit)
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }

    public double getAverage(String tradeType) {
        Stream<TradeItem> filteredTrades = filterTrades(tradeType);
        if (filteredTrades != null) {
            return filteredTrades
                    .mapToDouble(item -> item.getValue().doubleValue())
                    .average().orElse(Double.NaN);
        } else {
            return Double.NaN;
        }

    }

    public double getMedian(String tradeType) {
        Stream<TradeItem> filteredTrades = filterTrades(tradeType);
        if (filteredTrades != null) {
            List<BigDecimal> mappedTrades = filteredTrades
                    .map(TradeItem::getValue)
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
        StandardDeviation standardDeviation = new StandardDeviation();
        List<Double> collect = trades.stream()
                .filter((item -> item.getType().equals(tradeType)))
                .map(item -> item.getValue().doubleValue())
                .collect(Collectors.toList());

        Double[] values = collect.toArray(new Double[collect.size()]);

        return standardDeviation.evaluate(ArrayUtils.toPrimitive(values));
    }


    public Trades getFormattedData() {
        return new Trades()
                .setLargest_buy(getBiggestTrades(TradeType.BUY.getValue(), 5))
                .setLargest_sell(getBiggestTrades(TradeType.SELL.getValue(), 5))
                .setAverage_buy(getAverage(TradeType.BUY.getValue()))
                .setAverage_sell(getAverage(TradeType.SELL.getValue()))
                .setMedian_buy(getMedian(TradeType.BUY.getValue()))
                .setMedian_sell(getMedian(TradeType.SELL.getValue()))
                .setDeviation_buy(getDeviation(TradeType.BUY.getValue()))
                .setDeviation_sell(getDeviation(TradeType.SELL.getValue()));
    }

    private Stream<TradeItem> filterTrades(String tradeType) {
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
}
