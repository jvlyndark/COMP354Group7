import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Coord {
	double lat;
	double lon;
	
	public Coord(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}
	
	public Coord(CoordFull cf) {
		this.lat = cf.getLat();
		this.lon = cf.getLon();
	}
	
	public static Coord[] coordArr(List<CoordFull> cf) {
		List<Coord> c = new ArrayList<Coord>();
		for (int i = 0; i < cf.size(); i++) {
			c.add(new Coord(cf.get(i)));
		}
		Coord[] result = c.toArray(new Coord[c.size()]);
		return result;
	}
}
