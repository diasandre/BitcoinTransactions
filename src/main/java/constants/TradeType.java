package constants;

public enum TradeType {
    BUY("buy"), SELL("sell");

    private String value;

    TradeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
