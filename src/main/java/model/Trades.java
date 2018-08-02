package model;

import constants.TradeType;

import java.util.Collection;

public class Trades {
    private Collection<TradeItem> largest_sell;
    private Collection<TradeItem> largest_buy;
    private double average_sell;
    private double average_buy;
    private double median_sell;
    private double median_buy;
    private double deviation_sell;
    private double deviation_buy;

    public Trades setLargest(String tradeType, Collection<TradeItem> largest) {
        if (tradeType.equals(TradeType.BUY.getValue())) {
            setLargest_buy(largest);
            return this;
        } else {
            setLargest_sell(largest);
            return this;
        }
    }

    public Trades setAverage(String tradeType, double value) {
        if (tradeType.equals(TradeType.BUY.getValue())) {
            setAverage_buy(value);
            return this;
        } else {
            setAverage_sell(value);
            return this;
        }
    }

    // GENERATED

    public Collection<TradeItem> getLargest_sell() {
        return largest_sell;
    }

    public Trades setLargest_sell(Collection<TradeItem> largest_sell) {
        this.largest_sell = largest_sell;
        return this;
    }

    public Collection<TradeItem> getLargest_buy() {
        return largest_buy;
    }

    public Trades setLargest_buy(Collection<TradeItem> largest_buy) {
        this.largest_buy = largest_buy;
        return this;
    }

    public double getAverage_sell() {
        return average_sell;
    }

    public Trades setAverage_sell(double average_sell) {
        this.average_sell = average_sell;
        return this;
    }

    public double getAverage_buy() {
        return average_buy;
    }

    public Trades setAverage_buy(double average_buy) {
        this.average_buy = average_buy;
        return this;
    }

    public double getMedian_sell() {
        return median_sell;
    }

    public Trades setMedian_sell(double median_sell) {
        this.median_sell = median_sell;
        return this;
    }

    public double getMedian_buy() {
        return median_buy;
    }

    public Trades setMedian_buy(double median_buy) {
        this.median_buy = median_buy;
        return this;
    }

    public double getDeviation_sell() {
        return deviation_sell;
    }

    public Trades setDeviation_sell(double deviation_sell) {
        this.deviation_sell = deviation_sell;
        return this;
    }

    public double getDeviation_buy() {
        return deviation_buy;
    }

    public Trades setDeviation_buy(double deviation_buy) {
        this.deviation_buy = deviation_buy;
        return this;
    }
}
