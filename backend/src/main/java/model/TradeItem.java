package model;

import java.math.BigDecimal;
import java.util.Objects;

public class TradeItem {
    private Long date;
    private BigDecimal amount;
    private BigDecimal price;
    private String type;
    private Long tid;

    public Long getDate() {
        return date;
    }

    public TradeItem setDate(Long date) {
        this.date = date;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TradeItem setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public TradeItem setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getValue(){
        return getAmount().multiply(getPrice());
    }

    public String getType() {
        return type;
    }

    public TradeItem setType(String type) {
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
        return Objects.equals(getDate(), tradeItem.getDate()) &&
                Objects.equals(getAmount(), tradeItem.getAmount()) &&
                Objects.equals(getPrice(), tradeItem.getPrice()) &&
                Objects.equals(getType(), tradeItem.getType()) &&
                Objects.equals(getTid(), tradeItem.getTid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getAmount(), getPrice(), getType(), getTid());
    }
}
