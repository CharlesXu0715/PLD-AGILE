/*import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;*/

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class fileLoader{
    private List<intersection> intersections;
    private List<road> roads;
    private List<Request> requests;
    public fileLoader(){
        /* type=1:intersections & roads
        type=2:requests */
        intersections=new ArrayList<intersection>();
        roads=new ArrayList<road>();
        requests=new ArrayList<Request>();
    }

    public List<intersection> loadIntersection(String filename){
        File f=new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
        try { 
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            Document document = db.parse(f);
            NodeList nl = document.getElementsByTagName("intersection"); 
            for(int i = 0; i < nl.getLength(); i++) { 
                Node intersec = nl.item(i); 
                NamedNodeMap nnm = intersec.getAttributes();
                intersection in=new intersection(Double.parseDouble(nnm.item(2).getNodeValue()), Double.parseDouble(nnm.item(1).getNodeValue()), nnm.item(0).getNodeValue());
                intersections.add(in);
            } 
        } 
        catch (ParserConfigurationException e) { 
            e.printStackTrace(); 
        } catch (SAXException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
        return intersections; 
    }

    public List<road> loadRoad(String filename){
        File f=new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(f);
            NodeList nl = document.getElementsByTagName("segment"); 
            for(int i = 0; i < nl.getLength(); i++) {
                Node segment = nl.item(i);
                NamedNodeMap nnm = segment.getAttributes();
                road r=new road(nnm.item(3).getNodeValue(), nnm.item(0).getNodeValue(), nnm.item(2).getNodeValue(), Double.parseDouble(nnm.item(1).getNodeValue()));
                roads.add(r);
            } 
        } 
        catch (ParserConfigurationException e) { 
            e.printStackTrace(); 
        } catch (SAXException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
        return roads; 
    }

    public List<Request> loadRequest(String filename){
        File f=new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
        try { 
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(f);
            NodeList nl = document.getElementsByTagName("request"); 
            for(int i = 0; i < nl.getLength(); i++) {
                Node req = nl.item(i);
                NamedNodeMap nnm = req.getAttributes();
                Request r=new Request(Integer.parseInt(nnm.item(2).getNodeValue()), Integer.parseInt(nnm.item(3).getNodeValue()), nnm.item(0).getNodeValue(), nnm.item(1).getNodeValue());
                requests.add(r);
            } 
        } 
        catch (ParserConfigurationException e) { 
            e.printStackTrace(); 
        } catch (SAXException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        }
        return requests; 
    }

}
