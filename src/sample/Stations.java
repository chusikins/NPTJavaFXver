package sample;

import java.util.ArrayList;
import java.util.HashMap;

public class Stations {
    public static HashMap<String,String> stationsconv(){
        HashMap<String, String> stations = new HashMap<>();
        stations.put("Университетская","s9603770");
        stations.put("Новый Петергоф","s9603887");
        stations.put("Балтийский Вокзал","с2");
        stations.put("Обнинск","s9601907");
        return stations;
    }
    public static final String[] stationlist = {
        "Университетская","Новый Петергоф","Балтийский Вокзал","Обнинск"
    };
}
