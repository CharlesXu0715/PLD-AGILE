package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileLoader {
	private List<Intersection> intersections;
    private List<Road> roads;
    private RequestList requests;
    public FileLoader(){
        /* type=1:intersections & roads
        type=2:requests */
        intersections=new ArrayList<Intersection>();
        roads=new ArrayList<Road>();
    }

    public List<Intersection> loadIntersection(String filename){
        File f=new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
        try { 
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            org.w3c.dom.Document document = db.parse(f);
            NodeList nl = document.getElementsByTagName("intersection"); 
            for(int i = 0; i < nl.getLength(); i++) { 
                Node intersec = nl.item(i); 
                NamedNodeMap nnm = intersec.getAttributes();
                Intersection in=new Intersection(Double.parseDouble(nnm.item(2).getNodeValue()), Double.parseDouble(nnm.item(1).getNodeValue()), nnm.item(0).getNodeValue());
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

    public List<Road> loadRoad(String filename){
        File f=new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document document = db.parse(f);
            NodeList nl = document.getElementsByTagName("segment"); 
            for(int i = 0; i < nl.getLength(); i++) {
                Node segment = nl.item(i);
                NamedNodeMap nnm = segment.getAttributes();
                Road r=new Road(nnm.item(3).getNodeValue(), nnm.item(0).getNodeValue(), nnm.item(2).getNodeValue(), Double.parseDouble(nnm.item(1).getNodeValue()));
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

    public RequestList loadRequest(String filename){
        List<Request> rl=new ArrayList<Request>();
        File f=new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
        try { 
            DocumentBuilder db = dbf.newDocumentBuilder();
            org.w3c.dom.Document document = db.parse(f);
            NodeList nl = document.getElementsByTagName("request"); 
            for(int i = 0; i < nl.getLength(); i++) {
                Node req = nl.item(i);
                NamedNodeMap nnm = req.getAttributes();
                Request r=new Request(Integer.parseInt(nnm.item(2).getNodeValue()), Integer.parseInt(nnm.item(3).getNodeValue()), nnm.item(0).getNodeValue(), nnm.item(1).getNodeValue());
                rl.add(r);
            }
            nl=((org.w3c.dom.Document) document).getElementsByTagName("depot");
            Node d=nl.item(0);
            NamedNodeMap nnm = d.getAttributes();
            requests=new RequestList(nnm.item(1).getNodeValue(), nnm.item(0).getNodeValue(), rl);
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
