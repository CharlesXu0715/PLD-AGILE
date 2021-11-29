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
    
    public void loadMap(String filename) {
    	loadIntersection(filename);
    	loadRoad(filename);
    	chargeRoad();
    }

    public List<Intersection> getIntersections() {
		return intersections;
	}

	public List<Road> getRoads() {
		return roads;
	}

	public RequestList getRequests() {
		return requests;
	}

	public void loadIntersection(String filename){
        File f=new File(filename);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
        try { 
            DocumentBuilder db = dbf.newDocumentBuilder(); 
            org.w3c.dom.Document document = db.parse(f);
            NodeList nl = document.getElementsByTagName("intersection"); 
            for(int i = 0; i < nl.getLength(); i++) { 
                Node intersec = nl.item(i); 
                NamedNodeMap nnm = intersec.getAttributes();
                Intersection in=new Intersection(Double.parseDouble(nnm.item(2).getNodeValue()), Double.parseDouble(nnm.item(1).getNodeValue()), nnm.item(0).getNodeValue(),i);
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
    }

    public void loadRoad(String filename){
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
//                int pickIndex=0;
//                int delivIndex=0;
                Intersection pickPoint=new Intersection();
                Intersection delivPoint=new Intersection();
                for (int j=0;j<intersections.size();j++) {
                	if (intersections.get(j).getId().equals(pickId)) {
                		pickPoint=intersections.get(j);
                	}
                	if (intersections.get(j).getId().equals(delivId)) {
                		delivPoint=intersections.get(j);
                	}
                }
                int pickDur= Integer.parseInt(nnm.item(3).getNodeValue());
                int delivDur=Integer.parseInt(nnm.item(1).getNodeValue());
                System.out.println("delivDur:"+delivDur);
                System.out.println("delivIndex:"+delivPoint.getIndex());
                System.out.println("pickDur:"+pickDur);
                System.out.println("pickIndex:"+pickPoint.getIndex());
                System.out.println("*******");
                Request r=new Request(delivDur, pickDur, pickPoint, delivPoint);
                rl.add(r);
            }
            nl=((org.w3c.dom.Document) document).getElementsByTagName("depot");
            Node d=nl.item(0);
            NamedNodeMap nnm = d.getAttributes();
            String departTime=nnm.item(1).getNodeValue();
            String departPointId=nnm.item(0).getNodeValue();
            Intersection departPoint=new Intersection();
            int departIndex=0;
            for (int j=0;j<intersections.size();j++) {
            	if (intersections.get(j).getId().equals(departPointId)) {
            		departPoint=intersections.get(j);
            		departIndex=j;
            	}
            }
            requests=new RequestList(departTime, departPoint, departIndex, rl);
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
    
    public void chargeRoad() {
    	for (Road r:roads) {
    		intersections.get(r.getOriginIndex()).addAdjacence(r);
    	}
    }
}
