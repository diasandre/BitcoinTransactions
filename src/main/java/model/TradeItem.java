package model;

import constants.TradeType;

public class TradeItem {
    private Long date;
    private float amount;
    private float price;
    private TradeType type;
    private Long tid;

    public Long getDate() {
        return date;
    }

    public TradeItem setDate(Long date) {
        this.date = date;
        return this;
    }

    public float getAmount() {
        return amount;
    }

    public TradeItem setAmount(float amount) {
        this.amount = amount;
        return this;
    }

    public float getPrice() {
        return price;
    }

    public TradeItem setPrice(float price) {
        this.price = price;
        return this;
    }

    public TradeType getType() {
        return type;
    }

    public TradeItem setType(TradeType type) {
        this.type = type;
        return this;
    }

    public Long getTid() {
        return tid;
    }

    public TradeItem setTid(Long tid) {
        this.tid = tid;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeItem tradeItem = (TradeItem) o;

        if (Float.compare(tradeItem.getAmount(), getAmount()) != 0) return false;
        if (Float.compare(tradeItem.getPrice(), getPrice()) != 0) return false;
        if (getDate() != null ? !getDate().equals(tradeItem.getDate()) : tradeItem.getDate() != null) return false;
        if (getType() != tradeItem.getType()) return false;
        return getTid() != null ? getTid().equals(tradeItem.getTid()) : tradeItem.getTid() == null;
    }

    @Override
    public int hashCode() {
        int result = getDate() != null ? getDate().hashCode() : 0;
        result = 31 * result + (getAmount() != +0.0f ? Float.floatToIntBits(getAmount()) : 0);
        result = 31 * result + (getPrice() != +0.0f ? Float.floatToIntBits(getPrice()) : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getTid() != null ? getTid().hashCode() : 0);
        return result;
    }
}
