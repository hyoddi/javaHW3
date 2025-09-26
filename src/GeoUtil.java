public final class GeoUtil {
    public static double distanceMeters(Location a, Location b) {// Haversine distance (m)
        final double R = 6371000.0;
        double dLat = Math.toRadians(b.lat - a.lat);
        double dLon = Math.toRadians(b.lon - a.lon);
        double la1 = Math.toRadians(a.lat), la2 = Math.toRadians(b.lat);
        double h = Math.sin(dLat/2)*Math.sin(dLat/2) + Math.cos(la1)*Math.cos(la2)*Math.sin(dLon/2)*Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(h),
        Math.sqrt(1-h));
        return R * c;
    }
}