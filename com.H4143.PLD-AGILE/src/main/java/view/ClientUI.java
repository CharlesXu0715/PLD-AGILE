/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        setSize(1420, 820);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        this.divRequest.setLayout(new BorderLayout());
        this.divMap.setLayout(new BorderLayout());
        this.divRequest.setBackground(Color.BLUE);
        this.divMap.setBackground(Color.RED);
        this.divMap.setMinimumSize(new Dimension(710,820));
        this.divRequest.setMinimumSize(new Dimension(710,820));
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
        
        
//        //div request
        this.divMap.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.divRequest.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.add(divRequest);
        
        Panel divRequestBox = new Panel();
        divRequestBox.setPreferredSize(new Dimension(450, 250));
        //this.divRequest.setPreferredSize(new Dimension(710, 820));
        
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
            	//int result = fileChooser.showOpenDialog(divRequestBox);
            	//chooseFile(result, "request");
            	map.drawTour(map.getGraphics());
            	}
            }
        );
        
        
        JSplitPane splitContainer = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.divMap,this.divRequest);
 	   splitContainer.setResizeWeight(0.7);
 	   
 	   this.add(splitContainer);
        
       /* textArea = new TextArea();
         
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(580, 330));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel2.add(scrollPane);
        
        msgField = new TextField();
        msgField.setPreferredSize(new Dimension(200, 25));
        panel3.add(msgField, BorderLayout.WEST);
        
        send = new Button("Send");
        send.setPreferredSize(new Dimension(80, 25));
        send.addActionListener(this);
        panel3.add(send, BorderLayout.CENTER);
        
        vide = new Button("Vider");
        vide.setPreferredSize(new Dimension(80, 25));
        vide.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                        clear();
                }
        });
        panel3.add(vide,BorderLayout.EAST);*/
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
        	    this.map.setMinimumSize(new Dimension(400,600));
        	   	this.divMap.add(this.map);
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

