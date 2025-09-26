import java.util.List;

interface IProximityObserver {
    void onNearestChanged(List<POI> nearestSorted);
    void onEnter(POI poi, double distanceMeters);
    void onExit(POI poi, double distanceMeters);
}
