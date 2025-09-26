import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ProximityManager implements ILocationObserver, IProximitySubject {
    
    List<POI> all;
    int topK;
    double proximityMeters;
    List<IProximityObserver> observers;
    Set<String> nearestName;
    List<String> nearestOrder;
    Set<String> inside;
    

    public ProximityManager(List<POI> all, int topK, double proximityMeters) {
        this.all = Objects.requireNonNull(all);
        this.topK = Math.max(1, topK);
        this.proximityMeters = Math.max(0.0, proximityMeters);
    }

    @Override
    public void onLocationChanged(Location here) {
    // 거리 계산(GeoUtil사용) 및 정렬
    // 가장 근접한 Top-K 목록 작성
    // 변화가 있으면 onNearestChanged 통지
    // 근접 반경 enter/exit 통지
    }

    @Override
    public void addObserver(IProximityObserver o) {
        
    }

    @Override
    public void removeObserver(IProximityObserver o) {
        
    }
}