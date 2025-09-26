interface ILocationSubject {
    // 특정 구현 클래스에 의존하는 것이 아닌, 인터페이스에 의존한다 (DIP)
    void addObserver(ILocationObserver o);
    void removeObserver(ILocationObserver o);
    void updateLocation(Location latlon);
}
