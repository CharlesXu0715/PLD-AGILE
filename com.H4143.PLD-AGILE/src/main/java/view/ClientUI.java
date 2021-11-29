package view;

import view.widgets.Button;
import view.widgets.Label;
import view.widgets.Panel;
import view.widgets.TextArea;
import view.widgets.TextField;

       
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.CityMap;
import model.FileLoader;
import model.Intersection;
import model.Road;

/**
*
* @author 
*/
public class ClientUI extends JFrame implements ActionListener
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//panel1
	private Button loadMap;
   private Button loadRequest;
   private Button calculateTour;
   Panel divRequest = new Panel();
   Panel divMap=new Panel();

   
   private FileLoader fileLoader = new FileLoader();
   private FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier .xml","xml");
   
   private JFileChooser fileChooser = new JFileChooser();
   
   private MapUI map;
   
   public ClientUI()
   {
   	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
   	fileChooser.setFileFilter(filter);
   	
       setTitle("ClientUI");
       setSize(1400, 858);
       setLocationRelativeTo(null);
       setResizable(false);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       setLayout(new BorderLayout());
       getContentPane().setBackground(Color.WHITE);
       this.divRequest.setLayout(new BorderLayout());
       this.divMap.setLayout(new BorderLayout());
       this.divRequest.setBackground(Color.GRAY);
       this.divMap.setBackground(Color.DARK_GRAY);
       this.divMap.setPreferredSize(new Dimension(810,825));
       add(this.divMap);

       loadMap = new Button("Load Map");
       this.divMap.add(loadMap,BorderLayout.SOUTH);
       
       loadMap.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
           	int result = fileChooser.showOpenDialog(divMap);
           	chooseFile(result, "map");
           	}
           }
       );
       
       
       //div request
       this.divMap.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
       this.divRequest.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
       this.add(divRequest);
       
       Panel divRequestBox = new Panel();
       divRequestBox.setPreferredSize(new Dimension(545, 250));
       
       this.divRequest.add(divRequestBox);
       
       loadRequest = new Button("Load Request");
       calculateTour = new Button("Calculate Tour");
       this.divRequest.add(loadRequest, BorderLayout.SOUTH);
       this.divRequest.add(calculateTour, BorderLayout.NORTH);
               
       loadRequest.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
           	int result = fileChooser.showOpenDialog(divRequestBox);
           	chooseFile(result, "request");
           	}
           }
       );
       
       calculateTour.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
           	calculateTour();
           	}
           }
       );
       
       
       JSplitPane splitContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.divMap,this.divRequest);
	   splitContainer.setResizeWeight(0.7);
	   
	   this.add(splitContainer);
   }
   
   public void chooseFile(int result, String loadWhat) {
   	if (result == JFileChooser.APPROVE_OPTION) {
   	    File selectedFile = fileChooser.getSelectedFile();
   	    switch(loadWhat) {
   	    case "map":
   	    	fileLoader.loadMap(selectedFile.getAbsolutePath());
			List<Intersection> intersections = fileLoader.getIntersections();
			List<Road> roads = fileLoader.getRoads();
       	    CityMap map = new CityMap(roads,intersections);
       	    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
       	    this.map = new MapUI(map);
       	    this.map.setPreferredSize(new Dimension(800,660));
       	    this.divMap.add(this.map);
       	    pack();
       		this.setVisible(true);
   	    	break;
   	    case "request":
   	    	this.map.setRequests(fileLoader.loadRequest(selectedFile.getAbsolutePath()));
       	    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
       	    this.map.paintRequests(this.map.getGraphics());
   	    	break;
   	    }
   	    
   	}
   }
   
   public void calculateTour() {
   	
   }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
   


}



