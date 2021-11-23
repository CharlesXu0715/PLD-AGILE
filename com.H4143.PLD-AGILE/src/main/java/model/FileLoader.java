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
                int originIndex=0;
                int destinationIndex=0;
                String originId=nnm.item(3).getNodeValue();
                String destinationId=nnm.item(0).getNodeValue();
                for (int j=0;j<intersections.size();j++) {
                	if (intersections.get(j).getId().equals(originId)) {
                		originIndex=j;
                	}
                	if (intersections.get(j).getId().equals(destinationId)) {
                		destinationIndex=j;
                	}
                }
                Road r=new Road(originIndex, destinationIndex, nnm.item(2).getNodeValue(), Double.parseDouble(nnm.item(1).getNodeValue()));
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
//                System.out.println(nnm.item(0).getNodeValue());
//                System.out.println(nnm.item(1).getNodeValue());
//                System.out.println(nnm.item(2).getNodeValue());
//                System.out.println(nnm.item(3).getNodeValue());

                String pickId=nnm.item(2).getNodeValue();
                String delivId=nnm.item(0).getNodeValue();
                int pickDur= Integer.parseInt(nnm.item(3).getNodeValue());
                int delivDur=Integer.parseInt(nnm.item(1).getNodeValue());
                System.out.println("delivDur:"+delivDur);
                System.out.println("delivId:"+delivId);
                System.out.println("pickDur:"+pickDur);
                System.out.println("pickId:"+pickId);
                Request r=new Request(delivDur, pickDur, pickId, delivId);
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
