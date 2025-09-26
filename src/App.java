import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // list of poi
        List<POI> pois = POIGsonFileLoader.load("POI.json");
        pois.forEach(System.out::println);

        // 사용자 이동 시뮬레이션
        Location[] path = new Location[] {
            new Location(37.5779, 126.9760),
            new Location(37.5784, 126.9766),
            new Location(37.5789, 126.9772), // 근정전 근접 진입 가능
            new Location(37.5795, 126.9787),
            new Location(37.5797, 126.9794), // 향원정 근접 진입 가능
            new Location(37.5800, 126.9800)  // 향원정 이탈 가능
        };

        for (Location g : path) {
            System.out.println("locationManager.updateLocation(" + g + ")");
            Thread.sleep(600);
        }
    }
}
