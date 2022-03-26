import java.util.ArrayList;
import java.util.List;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class Splits {

	// test
	public static void main(String[] args) {

		System.out.println("[");
		String GPXFileName = "testTrack.gpx";

		List<CoordFull> cf = GPXtoArray.convert(GPXFileName);

		List<CoordFull> splitTime = generateDistanceSplit(cf, 20);
		List<CoordFull> splitDist = generateDistanceSplit(cf, 300);
		
		Coord.coordArr(splitTime);
		Coord.coordArr(splitDist);
		
	}

	public static List<CoordFull> generateTimeSplit(List<CoordFull> l, int seconds) {
		// Time format is ISO 8601. Example: "2021-07-10T13:33:52.562Z"
		OffsetDateTime startTime = OffsetDateTime.parse(l.get(0).time);
		OffsetDateTime endTime;
		List<CoordFull> split = new ArrayList<CoordFull>();

		for (int i = 0; i < l.size(); i++) {
			endTime = OffsetDateTime.parse(l.get(i).time);
			if (ChronoUnit.SECONDS.between(startTime, endTime) >= seconds) {
				return split;
			} else {
				split.add(l.get(i));
			}
		}

		return split;

	}

	public static List<CoordFull> generateDistanceSplit(List<CoordFull> l, int m){
		
		List<CoordFull> split = new ArrayList<CoordFull>();
		
		double startPointLat = l.get(0).lat;
		double startPointLon = l.get(0).lon;
		double endPointLat;
		double endPointLon;
		double accumulatedDistance = 0;
		
		for(int i=1; i<l.size(); i++) {
			endPointLat = l.get(i).lat;
			endPointLon = l.get(i).lon;
			double distanceBtw2Pts = FlatEarthDist.distance(startPointLat, startPointLon, endPointLat, endPointLon);
			accumulatedDistance += distanceBtw2Pts;
			if(accumulatedDistance>=m) {
				return split;
			}else {
				split.add(l.get(i));
			}
			startPointLat = l.get(i).lat;
			startPointLon = l.get(i).lon;
		}
		
		return split;
		
		
	}

	// reference this !!!!!!!!!!!!!!!!!:
	// https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude/20410612#20410612
	// This method was taken from an answer by user zahmde (Dec 5, 2013 at 21:01)
	private static class FlatEarthDist {
		// returns distance in meters
		public static double distance(double lat1, double lng1, double lat2, double lng2) {
			double a = (lat1 - lat2) * FlatEarthDist.distPerLat(lat1);
			double b = (lng1 - lng2) * FlatEarthDist.distPerLng(lat1);
			return Math.sqrt(a * a + b * b);
		}

		private static double distPerLng(double lat) {
			return 0.0003121092 * Math.pow(lat, 4) + 0.0101182384 * Math.pow(lat, 3) - 17.2385140059 * lat * lat
					+ 5.5485277537 * lat + 111301.967182595;
		}

		private static double distPerLat(double lat) {
			return -0.000000487305676 * Math.pow(lat, 4) - 0.0033668574 * Math.pow(lat, 3) + 0.4601181791 * lat * lat
					- 1.4558127346 * lat + 110579.25662316;
		}
	}

}
