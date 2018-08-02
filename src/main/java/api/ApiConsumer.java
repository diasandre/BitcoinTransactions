package api;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.TradeItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ApiConsumer {
    public Collection<TradeItem> read() throws UnirestException {
        String content = readJson();
        Gson gson = new Gson();
        ArrayList<TradeItem> tradeItems = new ArrayList<>();
        tradeItems.addAll(Arrays.asList(gson.fromJson(content, TradeItem[].class)));
        return tradeItems;
    }

    private String readJson() throws UnirestException {
        HttpResponse<JsonNode> jsonNodeHttpResponse = Unirest.get("https://www.mercadobitcoin.net/api/BTC/trades/1501871369/1501891200/")
                .asJson();
        return jsonNodeHttpResponse.getBody().toString();
    }
}
