import java.util.List;

public class ProximityLogger implements IProximityObserver {
    
    @Override
    public void onNearestChanged(List<POI> nearestSorted) {
        System.out.println("[LOG] nearestChanged ->" + nearestSorted);
    }
    
    @Override
    public void onEnter(POI poi, double d) {
    System.out.printf("[LOG] enter %s @ %.2fm%n", poi.name, d);
    }

    @Override
    public void onExit(POI poi, double d) {
    System.out.printf("[LOG] exit %s @ %.2fm%n",
    poi.name, d);
    }
}