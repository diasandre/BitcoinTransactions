package endpoint;

import api.ApiConsumer;
import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.TradeItem;
import model.Trades;
import service.TradeDataService;
import service.TradeDataServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Map;

@Path("/data")
public class DataEndpointImpl implements DataEndpoint{
    private final static Gson gson = new Gson();

    private TradeDataService tradeDataService;


    public DataEndpointImpl() {
        try {
            Trades read = new ApiConsumer().read();
            this.tradeDataService = new TradeDataServiceImpl(read);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces("application/json")
    public Response getData() {
        Map<String, Object> content = tradeDataService.getFormattedData();
        return Response.status(200).entity(gson.toJson(content)).build();
    }

    @GET
    @Path("/status")
    public Response status() {
        return Response.status(200).entity("API is working :) :)").build();

    }

    @GET
    @Path("/{type}/largest/{limit}")
    @Produces("application/json")
    public Response getLargestTrades(@PathParam("limit") int limit,
                                    @PathParam("type") String type) {
        Collection<TradeItem> biggestTrades = tradeDataService.getBiggestTrades(type, limit);
        String content = gson.toJson(biggestTrades);
        return Response.status(200).entity(content).build();

    }
}
