import java.util.List;

class POIDetailView implements IProximityObserver{

    @Override
    public void onNearestChanged(List<POI> nearestSorted) {
        System.out.println("[UI] TOP-K nearest updated:\n");
        for (int i = 1; i < nearestSorted.size() + 1; i++){
            System.out.println("  # " +  i + " "+ nearestSorted.get(i).toString());
        }
    }

    @Override
    public void onEnter(POI poi, double distanceMeters) {
        System.out.print("[UI] ENTER range:");
        System.out.println(String.format("%s (%.1fm)", poi.name, distanceMeters));
    }

    @Override
    public void onExit(POI poi, double distanceMeters) {
        System.out.print("[UI] EXIT range: ");
        System.out.println(String.format("%s (%.1fm)", poi.name, distanceMeters));
    }

}
