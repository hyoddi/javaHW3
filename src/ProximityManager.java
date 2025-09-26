import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

//LocationObserver로서 위치 변화를 수신
// 하면, 거리 정렬을 통해 Top-K 근접 목록 변화

class ProximityManager implements ILocationObserver, IProximitySubject {
    
    List<POI> all; // 핫플 위치 리스트
    int topK; // top K번 까지 출력해줄거임
    double proximityMeters; // 들어와 있다고 인정되는 거리
    List<IProximityObserver> observers; // 옵저버 리스트 (느슨한 결합 : 정확히 뭐하는 앤지는 모르겠지만 IProximityObserver 규칙을 지킨다는 것만 알면 충분하다!)
    
    Set<String> nearestName; // 걍 top-k 안에 들어있는지 빨리 확인하려고 넣어둔거 같다
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

        // nereastChanged 확인용 플래그
        List<String> currentOrder = nearestOrder;

        for (POI spot : all) {
            double checkInRanged = GeoUtil.distanceMeters(spot.location, here);
            

            // 조건 거리 내라면 inside 집합에 추가
            if (checkInRanged <= proximityMeters) {
                inside.add(spot.name);

                // 그리고 서브젝트한테 드갔다고 알림
                notifyEnter(spot, checkInRanged);
            }

            else if (checkInRanged > proximityMeters && inside.contains(spot)){ // 거리가 조건 거리 넘었는데, 원래 inside에 있던거다? => 밖으로 나갔다!
                inside.remove(spot); // 제거하고
                notifyExit(spot, checkInRanged); // 서브젝트에게 나갔다고 알림
            }


            
        }



        // 가장 근접한 Top-K 목록 작성

        // 변화가 있으면 onNearestChanged 통지


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