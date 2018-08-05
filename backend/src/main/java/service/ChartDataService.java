package service;

import service.Impl.ChartDataServiceImpl;

import java.util.Map;

public interface ChartDataService {
    Map<String, Object> getChartData(String tradeType);
    ChartDataServiceImpl onChangeTime(Long fromDate, Long toDate);
}
