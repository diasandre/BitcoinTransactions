package test;

import constants.TradeType;
import model.Trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class TradeTestData {

    public static Collection<Trade> tradeItens(int initialTid, int numberOfItens){
        ArrayList<Trade> values = new ArrayList<>();
        for(int i = initialTid; i <= numberOfItens; i++){
            values.add(getTradeItem(i));
        }
        return values;
    }

    private static Trade getTradeItem(int i){
        return new Trade()
                .setAmount(BigDecimal.ONE)
                .setDate((long) i)
                .setPrice(new BigDecimal(i* 10))
                .setTid((long) i)
                .setType(TradeType.BUY.getValue());
    }


}
