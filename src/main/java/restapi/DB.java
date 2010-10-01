package restapi;

import restapi.model.Measure;

import java.util.HashMap;

public class DB {
    private static HashMap<String, Measure> map = new HashMap<String, Measure>() {
        {
            put("one", new Measure("one"));
        }
    };

    public static void save(String key, Measure value) {
        map.put(key, value);
    }

    public static Measure get(String key) {
        return map.get(key);
    }
}
