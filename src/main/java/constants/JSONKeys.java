package constants;

public enum JSONKeys {
    LARGEST_SELL("largest_sell"),
    LARGEST_BUY("largest_buy"),
    AVERAGE_SELL("average_sell"),
    AVERAGE_BUY("average_buy"),
    MEDIAN_SELL("median_sell"),
    MEDIAN_BUY("media_buy"),
    DEVIATION_SELL("deviation_sell"),
    DEVIATION_BUY("deviation_buy");

    public String key;

    JSONKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
