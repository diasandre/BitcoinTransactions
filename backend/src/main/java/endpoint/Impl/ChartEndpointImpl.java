package endpoint.Impl;

import api.ApiConsumer;
import com.google.gson.Gson;
import com.mashape.unirest.http.exceptions.UnirestException;
import endpoint.DataEndpoint;
import model.Trade;
import service.ChartDataService;
import service.Impl.ChartDataServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Map;

@Path("/chart")
public class ChartEndpointImpl implements DataEndpoint {
    private final static Gson gson = new Gson();
    private ChartDataService chartDataService;

    public ChartEndpointImpl() {
        try {
            Collection<Trade> trades = new ApiConsumer().read();
            this.chartDataService = new ChartDataServiceImpl(trades);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrice(@PathParam("type") String type) {
        Map<String, Object> chartData = chartDataService.getChartData(type);

        String content = gson.toJson(chartData);

        return ResponseBuilder()
                .entity(content).build();

    }


    private Response.ResponseBuilder ResponseBuilder() {
        return Response.status(200).header("Access-Control-Allow-Origin", "*");
    }
}