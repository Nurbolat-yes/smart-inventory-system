package by.nurbolat.smartinventoryapplication.config;


import com.uber.h3core.H3Core;
import java.io.IOException;

public class H3Helper {

    private static H3Core h3;

    static {
        try {
            h3 = H3Core.newInstance();
        } catch (IOException e) {
            throw new RuntimeException("Failed to init H3Core", e);
        }
    }

    public static Long latLngToH3(double lat, double lng, int resolution) {
        return h3.latLngToCell(lat, lng, resolution);
    }

    public static H3Core getInstance() {
        return h3;
    }
}