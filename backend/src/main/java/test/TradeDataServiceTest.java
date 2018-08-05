package test;

import constants.TradeType;
import model.Trade;
import org.junit.Before;
import org.junit.Test;
import service.Impl.TradeDataServiceImpl;
import service.TradeDataService;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TradeDataServiceTest {

    private TradeDataService tradeDataService;

    @Before
    public void init() {
        tradeDataService = new TradeDataServiceImpl(TradeTestData.tradeItens(1,10));
    }

    @Test
    public void getBiggestTrades() {
        Collection<Trade> actual = tradeDataService.getBiggestTrades(TradeType.BUY.getValue(), 5);
        Collection<Trade> expected = TradeTestData.tradeItens(5, 10);

        assertTrue(expected.containsAll(actual));
    }

    @Test
    public void getAverage() {
        assertThat(tradeDataService.getAverage(TradeType.BUY.getValue()), is(55.0));
    }

    @Test
    public void getMedian() {
        assertThat(tradeDataService.getMedian(TradeType.BUY.getValue()), is(55.0));
    }

    @Test
    public void getDeviation() {
        assertThat(tradeDataService.getDeviation(TradeType.BUY.getValue()), is(30.276503540974915));
    }
}
