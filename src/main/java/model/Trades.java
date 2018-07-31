package model;

import java.util.Collection;

public class Trades {
    private Collection<TradeItem> trade;

    public Collection<TradeItem> getTrade() {
        return trade;
    }

    public Trades setTrade(Collection<TradeItem> trade) {
        this.trade = trade;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trades trades = (Trades) o;

        return getTrade() != null ? getTrade().equals(trades.getTrade()) : trades.getTrade() == null;
    }

    @Override
    public int hashCode() {
        return getTrade() != null ? getTrade().hashCode() : 0;
    }
}
