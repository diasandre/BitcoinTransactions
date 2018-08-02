package model;

import constants.TradeType;

import java.util.Collection;
import java.util.Objects;

public class Trades {
    private Collection<TradeItem> largest_sell;
    private Collection<TradeItem> largest_buy;
    private Double average_sell;
    private Double average_buy;
    private Double median_sell;
    private Double median_buy;
    private Double deviation_sell;
    private Double deviation_buy;

    public Trades setLargest(String tradeType, Collection<TradeItem> largest) {
        if (tradeType.equals(TradeType.BUY.getValue())) {
            setLargest_buy(largest);
            return this;
        } else {
            setLargest_sell(largest);
            return this;
        }
    }

    public Trades setAverage(String tradeType, Double value) {
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

    public Double getAverage_sell() {
        return average_sell;
    }

    public Trades setAverage_sell(Double average_sell) {
        this.average_sell = average_sell;
        return this;
    }

    public Double getAverage_buy() {
        return average_buy;
    }

    public Trades setAverage_buy(Double average_buy) {
        this.average_buy = average_buy;
        return this;
    }

    public Double getMedian_sell() {
        return median_sell;
    }

    public Trades setMedian_sell(Double median_sell) {
        this.median_sell = median_sell;
        return this;
    }

    public Double getMedian_buy() {
        return median_buy;
    }

    public Trades setMedian_buy(Double median_buy) {
        this.median_buy = median_buy;
        return this;
    }

    public Double getDeviation_sell() {
        return deviation_sell;
    }

    public Trades setDeviation_sell(Double deviation_sell) {
        this.deviation_sell = deviation_sell;
        return this;
    }

    public Double getDeviation_buy() {
        return deviation_buy;
    }

    public Trades setDeviation_buy(Double deviation_buy) {
        this.deviation_buy = deviation_buy;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trades trades = (Trades) o;
        return Objects.equals(getLargest_sell(), trades.getLargest_sell()) &&
                Objects.equals(getLargest_buy(), trades.getLargest_buy()) &&
                Objects.equals(getAverage_sell(), trades.getAverage_sell()) &&
                Objects.equals(getAverage_buy(), trades.getAverage_buy()) &&
                Objects.equals(getMedian_sell(), trades.getMedian_sell()) &&
                Objects.equals(getMedian_buy(), trades.getMedian_buy()) &&
                Objects.equals(getDeviation_sell(), trades.getDeviation_sell()) &&
                Objects.equals(getDeviation_buy(), trades.getDeviation_buy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLargest_sell(), getLargest_buy(), getAverage_sell(), getAverage_buy(), getMedian_sell(), getMedian_buy(), getDeviation_sell(), getDeviation_buy());
    }
}
