package model;

import java.math.BigDecimal;

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
}
