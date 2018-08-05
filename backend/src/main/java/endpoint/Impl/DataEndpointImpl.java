package endpoint.Impl;

import api.ApiConsumer;
import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import endpoint.DataEndpoint;
import model.TradeItem;
import model.Trades;
import service.TradeDataService;
import service.Impl.TradeDataServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.Collection;

@Path("/data")
public class DataEndpointImpl implements DataEndpoint {
    private final static Gson gson = new Gson();

    private TradeDataService tradeDataService;


    public DataEndpointImpl() {
        try {
            Collection<TradeItem> trades = new ApiConsumer().read();
            this.tradeDataService = new TradeDataServiceImpl(trades);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData() {
        Trades trades = tradeDataService.getFormattedData();
        return ResponseBuilder().entity(gson.toJson(trades)).build();
    }

    @GET
    @Path("/status")
    public Response status() {
        return ResponseBuilder().entity("API is working :) :)").build();

    }

    @GET
    @Path("/{type}/largest/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLargestTrades(@PathParam("limit") int limit,
                                     @PathParam("type") String type) {
        return getLargestTradesResponse(limit, type);

    }

    @GET
    @Path("/{type}/largest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLargestTrades(@PathParam("type") String type) {
        return getLargestTradesResponse(5, type);

    }

    private Response getLargestTradesResponse(int limit, String type) {
        Collection<TradeItem> biggestTrades = tradeDataService.getBiggestTrades(type, limit);

        if (biggestTrades == null) {
            return Response.status(204).entity(null).build();
        }

        String content = gson.toJson(biggestTrades);
        return ResponseBuilder().entity(content).build();
    }

    @GET
    @Path("/{type}/average")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAverage(@PathParam("type") String type) {
        String content = gson.toJson(tradeDataService.getAverage(type));
        return ResponseBuilder().entity(content).build();

    }

    @GET
    @Path("/{type}/median")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedian(@PathParam("type") String type) {
        String content = gson.toJson(tradeDataService.getMedian(type));
        return ResponseBuilder().entity(content).build();

    }

    @GET
    @Path("/{type}/deviation")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDeviation(@PathParam("type") String type) {
        String content = gson.toJson(tradeDataService.getDeviation(type));
        return ResponseBuilder().entity(content).build();

    }

    private ResponseBuilder ResponseBuilder(){
        return Response.status(200).header("Access-Control-Allow-Origin", "*");
    }
}
