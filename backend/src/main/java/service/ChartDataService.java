package service;

import java.util.Map;

public interface ChartDataService {
    Map<String, Object> getChartData(String tradeType);
}
