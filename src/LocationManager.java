import java.util.List;

class LocationManager implements ILocationSubject {
    List<ILocationObserver> observers;
    Location last;
    long lastTsMs;
    double distanceThresholdMeters;
    long minUpdateIntervalMs;

    public LocationManager(double distanceThresholdMeters, long minUpdateIntervalMs){
        this.distanceThresholdMeters = distanceThresholdMeters;
        this.minUpdateIntervalMs = minUpdateIntervalMs;
    }

    @Override
    public void addObserver(ILocationObserver o) {
        
    }

    @Override
    public void removeObserver(ILocationObserver o){

    }

    @Override
    public void updateLocation(Location cur){
        
    }
    
}
