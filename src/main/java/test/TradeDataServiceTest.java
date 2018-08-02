package test;

import com.mashape.unirest.http.exceptions.UnirestException;
import constants.TradeType;
import model.TradeItem;
import org.junit.Before;
import org.junit.Test;
import service.TradeDataService;
import service.TradeDataServiceImpl;

import java.util.Collection;

import static org.junit.Assert.assertTrue;

public class TradeDataServiceTest {

    private TradeDataService tradeDataService;

    @Before
    public void init() {
        tradeDataService = new TradeDataServiceImpl(TradeTestData.tradeItens(1,10));
    }

    @Test
    public void getBiggestTrades() throws UnirestException {
        Collection<TradeItem> biggestTrades = tradeDataService.getBiggestTrades(TradeType.BUY.getValue(), 5);
        Collection<TradeItem> expectedTrades = TradeTestData.tradeItens(5, 10);

        assertTrue(biggestTrades.containsAll(expectedTrades));
    }
}
