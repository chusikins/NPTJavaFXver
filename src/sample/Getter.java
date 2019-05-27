package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Getter {
    public static ArrayList getter(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.rasp.yandex.net/v3.0/schedule/?apikey=b6bc2c85-21ce-4c6a-884a-52eb303dd916&station=s9603770&transport_types=suburban")).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Getter::parse)
                .join();
    }

    private static ArrayList parse(String responseBody){
        ArrayList<String> traininfo = new ArrayList<String>();
        JSONObject mainobj = new JSONObject(responseBody);
        JSONObject pagination = mainobj.getJSONObject("pagination");
        JSONArray schedule = mainobj.getJSONArray("schedule");
        System.out.println(pagination);
        for (int i = 0; i < schedule.length(); i++ ){
            JSONObject train = (schedule).getJSONObject(i);
            String arrival = train.getString("arrival");
            String  direction = train.getString("direction");
            JSONObject thread = train.getJSONObject("thread");
            String title = thread.getString("title");
            String templine = title + "/" + arrival + "/" + direction;
            traininfo.add(templine);
        }
        return traininfo;
    }
}

