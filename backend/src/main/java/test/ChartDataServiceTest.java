package test;

import me.andrz.builder.map.MapBuilder;
import org.junit.Before;
import org.junit.Test;
import service.ChartDataService;
import service.Impl.ChartDataServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChartDataServiceTest {

    private ChartDataService chartDataService;

    @Before
    public void init() {
        chartDataService = new ChartDataServiceImpl(TradeTestData.tradeItens(1, 10));
    }

    @Test
    public void getFormattedData() {
        Map<String, Object> actual = chartDataService.getChartData("buy");

        Map<String, Object> expected = new MapBuilder<String, Object>()
                .p("labels", Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L))
                .p("datasets", Collections.singletonList(
                        new MapBuilder<String, Object>()
                                .p("label", "Price")
                                .p("data", Arrays.asList(
                                        new BigDecimal(10),
                                        new BigDecimal(20),
                                        new BigDecimal(30),
                                        new BigDecimal(40),
                                        new BigDecimal(50),
                                        new BigDecimal(60),
                                        new BigDecimal(70),
                                        new BigDecimal(80),
                                        new BigDecimal(90),
                                        new BigDecimal(100)))
                                .p("backgroundColor", "#FBBA16")
                                .build()
                ))
                .build();

        assertThat(actual, is(expected));

    }
}
