package client;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;

public class DataFactory {
    public DataFactory(){}

    // given a xml string return a document for the next step
    Document xml2doc(String xml) {
        try {
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputSource input = new InputSource();
            input.setCharacterStream(new StringReader(xml));
            return docBuilder.parse(input);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //return a hash map according to the given airport xml, where code maps airport, xml is from accessor.getAirport().
    public HashMap<String, Airport> xml2Airports(String xml) {
        Document doc = xml2doc(xml);
        NodeList nodes = doc.getElementsByTagName("Airport");
        HashMap<String, Airport> airports = new HashMap<>();
        for(int i=0;i<nodes.getLength();i++){
            Element node= (Element) nodes.item(i);
            String code = node.getAttributeNode("Code").getValue();
            Airport ap = new Airport(node.getAttributeNode("Name").getValue(),
                    code,
                    node.getElementsByTagName("Latitude").item(0).getTextContent(),
                    node.getElementsByTagName("Longitude").item(0).getTextContent());
            airports.put(code,ap);
        }
        return airports;
    }

    // return a hash map according to the given airplane xml, model maps airplane, xml is from accessor.getAirplane()
    public HashMap<String, Airplane> xml2Airplanes(String xml){
        Document doc = xml2doc(xml);
        NodeList nodes = doc.getElementsByTagName("Airplane");
        HashMap<String, Airplane> airplanes = new HashMap<>();
        for(int i=0;i<nodes.getLength();i++){
            Element node= (Element) nodes.item(i);
            String model = node.getAttributeNode("Model").getValue();
            Airplane ap = new Airplane(model,
                    node.getAttributeNode("Manufacturer").getValue(),
                    node.getElementsByTagName("FirstClassSeats").item(0).getTextContent(),
                    node.getElementsByTagName("CoachSeats").item(0).getTextContent());
            airplanes.put(model,ap);
        }
        return airplanes;
    }

    // return a hash map according to the given flight xml, flightNumber maps flight,
    // flight hash map could be all the flights from the same departure on the same day, or to the same destination.
    // xml is from accessor.getDepartingFlight or getArrivingFlight
    public HashMap<String, Flight> xml2Flights(String xml){
        Document doc = xml2doc(xml);
        NodeList nodes = doc.getElementsByTagName("Flight");
        HashMap<String, Flight> flights = new HashMap<>();
        for(int i=0;i<nodes.getLength();i++){
            Element node= (Element) nodes.item(i);
            String fno = node.getAttributeNode("Number").getValue();
            Element departure = (Element)node.getElementsByTagName("Departure").item(0),
                    arrival=(Element)node.getElementsByTagName("Arrival").item(0),
                    seat=(Element)node.getElementsByTagName("Seating").item(0);
            Flight ap = new Flight(fno,
                    departure.getElementsByTagName("Code").item(0).getTextContent(),
                    arrival.getElementsByTagName("Code").item(0).getTextContent(),
                    departure.getElementsByTagName("Time").item(0).getTextContent(),
                    arrival.getElementsByTagName("Time").item(0).getTextContent(),
                    node.getAttributeNode("FlightTime").getValue(),
                    node.getAttributeNode("Airplane").getValue(),
                    seat.getElementsByTagName("FirstClass").item(0).getTextContent(),
                    seat.getElementsByTagName("Coach").item(0).getTextContent(),
                    ((Element)seat.getElementsByTagName("FirstClass").item(0)).getAttributeNode("Price").getValue(),
                    ((Element)seat.getElementsByTagName("Coach").item(0)).getAttributeNode("Price").getValue()
            );
            flights.put(fno,ap);
        }
        return flights;
    }
}
