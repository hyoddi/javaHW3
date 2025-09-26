import java.util.List;
import java.util.Objects;
import java.util.Set;

//LocationObserver로서 위치 변화를 수신
// 하면, 거리 정렬을 통해 Top-K 근접 목록 변화

class ProximityManager implements ILocationObserver, IProximitySubject {
    
    List<POI> all;
    int topK; // 가까운 순위
    double proximityMeters; // 들어와 있다고 인정되는 거리
    List<IProximityObserver> observers; // 옵저버 리스트
    Set<String> nearestName; 
    List<String> nearestOrder; // 거리순 정렬 리스트
    Set<String> inside; // 인정 거리 내 집합 (안에 있냐 없냐 확인용인듯)
    

    public ProximityManager(List<POI> all, int topK, double proximityMeters) {
        this.all = Objects.requireNonNull(all);
        this.topK = Math.max(1, topK);
        this.proximityMeters = Math.max(0.0, proximityMeters);
    }

    @Override
    public void onLocationChanged(Location here) {
        // 거리 계산(GeoUtil사용) 및 정렬

        for (POI spot : all){
            double distance = GeoUtil.distanceMeters(spot.location, here);

            if (distance <= )
            nearestName.add(spot.name);
        }



        // 가장 근접한 Top-K 목록 작성
        // 변화가 있으면 onNearestChanged 통지
        // 근접 반경 enter/exit 통지

    }


    @Override
    public void addObserver(IProximityObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(IProximityObserver o) {
        observers.remove(o);
    }
    
    // 알림 메소드들
    private void notifyNearestChanged(List<POI> nearestSorted) {
        for (IProximityObserver o : observers) {
            o.onNearestChanged(nearestSorted);
        }
    }

    private void notifyEnter(POI poi, double d) {
        for (IProximityObserver o : observers) {
            o.onEnter(poi, d);
        }
    }

    private void notifyExit(POI poi, double d) {
        for (IProximityObserver o : observers) {
            o.onExit(poi, d);
        }
    }

}