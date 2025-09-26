import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class POIGsonFileLoader {
    public static List<POI> load(String filePath) throws java.io.IOException {
        List<POI> result = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)) {
            // Parse the JSON file
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            JsonArray poisArray = jsonObject.getAsJsonArray("pois");

            // Iterate over pois array in JSON
            for (JsonElement element : poisArray) {

                JsonObject obj = element.getAsJsonObject();
                // name
                String name = obj.get("name").getAsString();
                // location
                JsonObject locObj = obj.getAsJsonObject("location");
                if (locObj == null) continue;

                Double lat = readDouble(locObj, "latitude", "lat");
                Double lon = readDouble(locObj, "longitude", "lon");
                if (lat == null || lon == null) continue;

                result.add(new POI(name, new Location(lat, lon)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Allow a number or number string (if none, then null)
    private static Double readDouble(JsonObject o, String primary, String alternate) {
        JsonElement e = o.get(primary);
        if (e == null || e.isJsonNull()) e = o.get(alternate);
        if (e == null || e.isJsonNull()) return null;

        if (e.isJsonPrimitive()) {
            try {
                if (e.getAsJsonPrimitive().isNumber()) return e.getAsDouble();
                if (e.getAsJsonPrimitive().isString())
                    return Double.parseDouble(e.getAsString().trim());
            } catch (NumberFormatException ignore) {}
        }
        return null;
    }
}
