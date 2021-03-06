package endpoint.Impl;

import api.ApiConsumer;
import com.google.gson.Gson;
import endpoint.DataEndpoint;
import model.Trade;
import service.Impl.TradeDataServiceImpl;
import service.TradeDataService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import java.util.Collection;
import java.util.Map;

@Path("/data")
public class DataEndpointImpl implements DataEndpoint {
    private final static Gson gson = new Gson();

    private TradeDataService tradeDataService;

    public DataEndpointImpl() {
        this.tradeDataService = new TradeDataServiceImpl(new ApiConsumer().read());
    }

    @GET
    @Path("/status")
    public Response status() {
        return ResponseBuilder().entity("API is working :) :)").build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataDefault() {
        Map<String, Object> content = tradeDataService.getFormattedData();
        return ResponseBuilder()
                .entity(gson.toJson(content))
                .build();
    }

    @GET
    @Path("/{fromDate}/{toDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData(@PathParam("fromDate") Long fromDate,
                            @PathParam("toDate") Long toDate) {
        tradeDataService.onChangeTime(fromDate, toDate);
        Map<String, Object> content = tradeDataService.getFormattedData();
        return ResponseBuilder()
                .entity(gson.toJson(content))
                .build();
    }

    @GET
    @Path("/{type}/largest/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLargestTrades(@PathParam("limit") int limit,
                                     @PathParam("type") String type) {
        return getLargestTradesResponse(limit, type);

    }

    @GET
    @Path("/{fromDate}/{toDate}/{type}/largest/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLargestTradesCustom(
            @PathParam("fromDate") Long fromDate,
            @PathParam("toDate") Long toDate,
            @PathParam("limit") int limit,
            @PathParam("type") String type) {
        tradeDataService.onChangeTime(fromDate, toDate);
        return getLargestTradesResponse(limit, type);
    }

    @GET
    @Path("/{type}/largest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLargestTrades(@PathParam("type") String type) {
        return getLargestTradesResponse(5, type);

    }

    private Response getLargestTradesResponse(int limit, String type) {
        Collection<Trade> biggestTrades = tradeDataService.getBiggestTrades(type, limit);

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

    private ResponseBuilder ResponseBuilder() {
        return Response.status(200).header("Access-Control-Allow-Origin", "*");
    }
}
