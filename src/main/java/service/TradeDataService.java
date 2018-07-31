package service;

import constants.TradeType;
import model.TradeItem;
import model.Trades;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TradeDataService {

    private Trades trades;

    public TradeDataService(Trades trades) {
        this.trades = trades;
    }

    private Collection<TradeItem> getBiggestSells(){
        return trades.getTrade().stream()
                .filter((item -> item.getType().equals(TradeType.sell)))
                .sorted(Comparator.comparing(TradeItem::getAmount))
                .limit(5)
                .collect(Collectors.toList());
    }

    private Collection<TradeItem> getBiggestBuys(){
        return trades.getTrade().stream()
                .filter((item -> item.getType().equals(TradeType.buy)))
                .sorted(Comparator.comparing(TradeItem::getAmount))
                .limit(5)
                .collect(Collectors.toList());
    }
}
