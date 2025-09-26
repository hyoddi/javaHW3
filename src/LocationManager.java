import java.util.List;

class LocationManager implements ILocationSubject {
    List<ILocationObserver> observers; // 옵저버 리스트
    Location last; // 마지막 위치
    long lastTsMs; // 마지막 업뎃 시간 (의미없는 업뎃 거르기용)
    double distanceThresholdMeters; // 위치가 이전 위치에서 이 값 이상 움직였을 때만 업데이트 발생
    long minUpdateIntervalMs; // 업데이트 사이 최소 시간 간격 (아무리 위치가 자주 바뀌어도, 이 시간보다 짧으면 무시)

    public LocationManager(double distanceThresholdMeters, long minUpdateIntervalMs){
        this.distanceThresholdMeters = distanceThresholdMeters;
        this.minUpdateIntervalMs = minUpdateIntervalMs;
    }

    @Override
    public void addObserver(ILocationObserver o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    @Override
    public void removeObserver(ILocationObserver o){
        observers.remove(o);
    }

    @Override
    public void updateLocation(Location cur){
        long now = System.currentTimeMillis();
        if (now - lastTsMs < minUpdateIntervalMs && GeoUtil.distanceMeters(cur, last) >= distanceThresholdMeters) { return; } // 최소 업뎃 조건 아니면 무시

        // 아니면 업데이트
        this.last = cur;
        lastTsMs = now; // 마지막 업뎃 시간 최신화

        // 서브젝트에게 알림
        for (ILocationObserver observer: observers){
            observer.onLocationChanged(last);
        }
    }
}
