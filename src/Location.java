public class Location {
    public final double lat;
    public final double lon;

    public Location(double lat, double lon) { 
        this.lat = lat; this.lon = lon; 
    }

    @Override public String toString() { 
        return lat + ", " + lon; 
    }
}
