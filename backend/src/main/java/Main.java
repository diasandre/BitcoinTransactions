import api.ApiConsumer;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {
    public static void main(String[] args) {
        ApiConsumer consumer = new ApiConsumer();
        try {
            System.out.println(consumer.read());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
