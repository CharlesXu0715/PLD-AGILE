package singleton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.Intersection;
import model.CityMap;
import model.Model;
import model.Request;
import model.RequestList;
import model.Segment;
import view.View;

public class XMLFileLoader {

	private static XMLFileLoader xMLFileLoader;

	private JFileChooser fileChooser;
	private FileNameExtensionFilter filter;
	
	/**
	 * constructor of XMLFileLoader
	 */
	private XMLFileLoader() {

		fileChooser = new JFileChooser();
		filter = new FileNameExtensionFilter("Fichier .xml", "xml");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		fileChooser.setFileFilter(filter);
	}
	
	/**
	 * create a XMLFileLoader
	 * @return XMLFileLoader
	 */
	public static XMLFileLoader getInstance() {
		if (xMLFileLoader == null) {
			xMLFileLoader = new XMLFileLoader();
		}
		return xMLFileLoader;
	}

	/**
	 * select the file to read
	 * @param view: the current interface
	 * @return the opened file
	 * @throws Exception: file not selected
	 */
	private File selectFile(View view) throws Exception {
		int result = fileChooser.showOpenDialog(view);
		if (result == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			throw new Exception("File is not selected");
		}
	}

	/**
	 * load a map
	 * @param view: the current interface
	 * @return the CityMap loaded
	 * @throws Exception: no segments or no intersections
	 */
	public CityMap loadMap(View view) throws Exception  {
		ArrayList<Intersection> intersections = new ArrayList<Intersection>();
		ArrayList<Segment> segments = new ArrayList<Segment>();
		
		File f = this.selectFile(view);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		org.w3c.dom.Document document = db.parse(f);

		NodeList nl = document.getElementsByTagName("intersection");
		if (nl.getLength() == 0)
			throw new Error("No intersection");
		for (int i = 0; i < nl.getLength(); i++) {
			Node intersec = nl.item(i);
			NamedNodeMap nnm = intersec.getAttributes();
			Intersection in = new Intersection(Double.parseDouble(nnm.item(2).getNodeValue()),
					Double.parseDouble(nnm.item(1).getNodeValue()), nnm.item(0).getNodeValue(), i);
			intersections.add(in);
		}

		nl = document.getElementsByTagName("segment");
		if (nl.getLength() == 0)
			throw new Error("No segment");

		for (int i = 0; i < nl.getLength(); i++) {
			Node segmentNode = nl.item(i);
			NamedNodeMap nnm = segmentNode.getAttributes();
			int originIndex = 0;
			int destinationIndex = 0;
			String originId = nnm.item(3).getNodeValue();
			String destinationId = nnm.item(0).getNodeValue();
			for (int j = 0; j < intersections.size(); j++) {
				if (intersections.get(j).getId().equals(originId)) {
					originIndex = j;
				}
				if (intersections.get(j).getId().equals(destinationId)) {
					destinationIndex = j;
				}
			}
			Segment segment = new Segment(originIndex, destinationIndex, nnm.item(2).getNodeValue(),
					Double.parseDouble(nnm.item(1).getNodeValue()));
			segments.add(segment);
		}

		for (Segment segment : segments) {
			intersections.get(segment.getOriginIndex()).addAdjacence(segment);
		}
		
		return new CityMap(segments, intersections);
	}

	/**
	 * load the requests
	 * @param view: the current interface
	 * @param model: the tool of settings of model
	 * @return the list of requests
	 * @throws Exception: no requests
	 */
	public RequestList loadRequest(View view, Model model) throws Exception {
		List<Intersection> intersections = model.getMap().getIntersections();
		List<Request> rl = new ArrayList<Request>();
		
		
		File f = this.selectFile(view);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		org.w3c.dom.Document document = db.parse(f);
		NodeList nl = document.getElementsByTagName("request");
		if (nl.getLength() == 0) throw new Error("No Requests");
			
		for (int i = 0; i < nl.getLength(); i++) {
			Node req = nl.item(i);
			NamedNodeMap nnm = req.getAttributes();

			String pickId = nnm.item(2).getNodeValue();
			String delivId = nnm.item(0).getNodeValue();

			Intersection pickPoint = null;
			Intersection delivPoint = null;
			for (int j = 0; j < intersections.size(); j++) {
				if (intersections.get(j).getId().equals(pickId)) {
					pickPoint = intersections.get(j);
				}
				if (intersections.get(j).getId().equals(delivId)) {
					delivPoint = intersections.get(j);
				}
			}
			if (pickPoint == null || delivPoint == null) {
				return null;
			}
			int pickDur = Integer.parseInt(nnm.item(3).getNodeValue());
			int delivDur = Integer.parseInt(nnm.item(1).getNodeValue());

			Request r = new Request(delivDur, pickDur, pickPoint, delivPoint);
			rl.add(r);
		}
		nl = ((org.w3c.dom.Document) document).getElementsByTagName("depot");
		Node d = nl.item(0);
		NamedNodeMap nnm = d.getAttributes();
		String departTime = nnm.item(1).getNodeValue();
		String departPointId = nnm.item(0).getNodeValue();
		Intersection departPoint = new Intersection();
		int departIndex = 0;
		for (int j = 0; j < intersections.size(); j++) {
			if (intersections.get(j).getId().equals(departPointId)) {
				departPoint = intersections.get(j);
				departIndex = j;
			}
		}
		return new RequestList(departTime, departPoint, departIndex, rl);

	}
}
