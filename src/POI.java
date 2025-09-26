public class POI {
    public final String name;
    public final Location location;


    // 이름이랑 위치 가지고 있음
    public POI(String name, Location location) {
        this.name = name; 
        this.location = location;
    }

    @Override 
    public String toString() { 
        return name + " @ " + location; 
    }
}
