package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Trade {
    private Long date;
    private BigDecimal amount;
    private BigDecimal price;
    private String type;
    private Long tid;

    public Long getDate() {
        return date;
    }

    public Trade setDate(Long date) {
        this.date = date;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Trade setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Trade setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getValue(){
        return getAmount().multiply(getPrice());
    }

    public String getType() {
        return type;
    }

    public Trade setType(String type) {
        this.type = type;
        return this;
    }

    public Long getTid() {
        return tid;
    }

    public Trade setTid(Long tid) {
        this.tid = tid;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Objects.equals(getDate(), trade.getDate()) &&
                Objects.equals(getAmount(), trade.getAmount()) &&
                Objects.equals(getPrice(), trade.getPrice()) &&
                Objects.equals(getType(), trade.getType()) &&
                Objects.equals(getTid(), trade.getTid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getAmount(), getPrice(), getType(), getTid());
    }
}
