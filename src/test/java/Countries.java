import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Countries {
    public static void main(String[] args) throws IOException, JSONException {

        System.out.println("Please enter country name");
        Scanner s1 = new Scanner(System.in);
        String countryName = s1.next();
        // use OKHttp client to create the connection and retrieve data
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://restcountries.com/v3.1/name/" + countryName)
                .build();
        Response response = client.newCall(request).execute();
        String jsonData = response.body().string();
        // parse JSON
        JSONArray mainJsonArray = new JSONArray(jsonData);
        JSONObject mainJsonObject = (JSONObject) mainJsonArray.get(0);
        String region = mainJsonObject.getString("region");
        JSONArray borders = mainJsonObject.getJSONArray("borders");
        JSONObject currencies = mainJsonObject.getJSONObject("currencies");
        JSONObject ils = currencies.getJSONObject("ILS");
        String symbol = ils.getString("symbol");
        System.out.println(region + " " + borders + " " + symbol);
    }
}
