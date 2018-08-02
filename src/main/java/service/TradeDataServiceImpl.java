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

public class TradeDataServiceImpl implements TradeDataService {
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
        return tradeType.equals(TradeType.SELL.getValue()) || tradeType.equals(TradeType.SELL.getValue());
    }

    public double getAverage(String tradeType) {
        return trades.stream()
                .filter((item -> item.getType().equals(tradeType)))
                .mapToDouble(item -> item.getValue().doubleValue())
                .average().orElse(Double.NaN);
    }

    public double getMedian(String tradeType) {
        List<BigDecimal> collect = trades.stream()
                .filter((item -> item.getType().equals(tradeType)))
                .map(TradeItem::getValue)
                .collect(Collectors.toList());

        BigDecimal[] values = collect.toArray(new BigDecimal[collect.size()]);

        Arrays.sort(values);
        int middle = values.length / 2;
        BigDecimal medianValue;
        if (values.length % 2 == 1)
            medianValue = values[middle];
        else
            medianValue = (values[middle - 1].add(values[middle])).divideToIntegralValue(new BigDecimal(2));

        return medianValue.doubleValue();
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
}
