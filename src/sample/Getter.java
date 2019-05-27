package sample;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Getter {

    public static ArrayList getter(String key,String station){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URLBuilder(key,station))).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(Getter::parse)
                .join();
    }

    private static String URLBuilder(String key, String station){
        String url = "https://api.rasp.yandex.net/v3.0/schedule/?apikey="+""+key+""+"&station="+""+station+""+"&transport_types=suburban";
        return url;
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

