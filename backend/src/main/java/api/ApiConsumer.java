package api;

import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.Trade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ApiConsumer {
    private final static String URL_BASE = "https://www.mercadobitcoin.net/api/BTC/trades";
    private final static Long FROM_DATE_DEFAULT = 1501871369L;
    private final static Long TO_DATE_DEFAULT = 1501891200L;

    public Collection<Trade> read(){
        return read(FROM_DATE_DEFAULT, TO_DATE_DEFAULT);
    }

    public Collection<Trade> read(Long fromDate, Long toDate) {
        String url = URL_BASE + "/" + fromDate.toString() + "/" + toDate.toString();
        String content = readJson(url);
        Gson gson = new Gson();
        ArrayList<Trade> trades = new ArrayList<>();
        trades.addAll(Arrays.asList(gson.fromJson(content, Trade[].class)));
        return trades;
    }

    private String readJson(String url) {
        try {
            return Unirest.get(url)
                    .asJson().getBody().toString();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return null;
    }
}
