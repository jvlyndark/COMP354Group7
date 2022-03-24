import java.util.List;

public class Driver {

	public static void main(String[] args) {
		String GPXFileName="testTrack.gpx";
		
		List<CoordFull> cf = GPXtoArray.convert(GPXFileName);
		
		Coord[] coordArr = Coord.coordArr(cf);
		//we pass this coordArr to the javascript to output the 
		
	}

}
