import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GPXtoArray {
	
	public static List<CoordFull> convert(String fileLocation) {
		List<CoordFull> result = new ArrayList<CoordFull>();
		NodeList trkpt = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new File (fileLocation));	
			trkpt = document.getElementsByTagName("trkpt");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for(int i=0; i<trkpt.getLength(); i++) {
			String latStr = trkpt.item(i).getAttributes().getNamedItem("lat").getNodeValue();
			String lonStr = trkpt.item(i).getAttributes().getNamedItem("lon").getNodeValue();
			String time = trkpt.item(i).getChildNodes().item(3).getTextContent();
			String eleStr = trkpt.item(i).getChildNodes().item(1).getTextContent();
			double lat = Double.parseDouble(latStr);
			double lon = Double.parseDouble(lonStr);
			double ele = Double.parseDouble(eleStr);

			result.add(new CoordFull(lat, lon, time, ele));
			System.out.println("lat: "+lat+" lon:"+lon);
		}

		return result;
	}
}



