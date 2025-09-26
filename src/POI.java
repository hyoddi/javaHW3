public class POI {
    public final String name;
    public final Location location;

    public POI(String name, Location location) {
        this.name = name; 
        this.location = location;
    }

    @Override 
    public String toString() { 
        return name + " @ " + location; 
    }
}
