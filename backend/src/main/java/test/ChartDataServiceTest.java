package test;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Before;
import org.junit.Test;
import service.ChartDataService;
import service.Impl.ChartDataServiceImpl;

public class ChartDataServiceTest {

    private ChartDataService chartDataService;

    @Before
    public void init() {
        chartDataService = new ChartDataServiceImpl(TradeTestData.tradeItens(1,10));
    }

    @Test
    public void getBiggestTrades() throws UnirestException {

    }
}
