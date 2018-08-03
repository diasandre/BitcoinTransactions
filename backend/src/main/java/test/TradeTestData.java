package test;

import constants.TradeType;
import model.TradeItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class TradeTestData {

    public static Collection<TradeItem> tradeItens(int initialTid, int numberOfItens){
        ArrayList<TradeItem> values = new ArrayList<>();
        for(int i = initialTid; i < numberOfItens; i++){
            values.add(getTradeItem(i));
        }
        return values;
    }

    private static TradeItem getTradeItem(int i){
        return new TradeItem()
                .setAmount(BigDecimal.ONE)
                .setDate((long) i)
                .setPrice(new BigDecimal(i* 10))
                .setTid((long) i)
                .setType(TradeType.BUY.getValue());
    }
}
