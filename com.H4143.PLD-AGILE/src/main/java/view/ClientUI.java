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
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.CityMap;
import model.FileLoader;
import model.Intersection;
import model.Road;

/**
 *
 * @author 
 */
public class ClientUI extends JFrame implements ActionListener ,WindowListener
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//panel1
	private Button loadMap;
    private Button loadRequest;
    //panel2
    private TextArea textArea;
    //panel3
    private TextField msgField;
    private Button send;
    private Button vide;
    
    private FileLoader fileLoader = new FileLoader();
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier .xml","xml");
    
    private JFileChooser fileChooser = new JFileChooser();
    
    public ClientUI()
    {
    	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    	fileChooser.setFileFilter(filter);
    	
        setTitle("ClientUI");
        setSize(1020, 800);
        setLocationRelativeTo(null);
        setResizable(false);
        this.addWindowListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        //div map
        Panel divMap=new Panel();
        divMap.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.add(divMap,BorderLayout.WEST);
        Panel divMapSVG = new Panel();
        divMapSVG.setPreferredSize(new Dimension(450, 250));
        divMap.setPreferredSize(new Dimension(450, 700));
        divMap.add(divMapSVG);
        
        loadMap = new Button("Load Map");
        divMap.add(loadMap);
        
        loadMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int result = fileChooser.showOpenDialog(divMapSVG);
            	chooseFile(result, "map");
            	}
            }
        );
        
        //div request
        Panel divRequest = new Panel();
        //divRequest.setBorder(BorderFactory.createLineBorder(Color.black));
        divRequest.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.add(divRequest,BorderLayout.EAST);
        
        Panel divRequestBox = new Panel();
        divRequestBox.setPreferredSize(new Dimension(450, 250));
        divRequest.setPreferredSize(new Dimension(450, 700));
        
        divRequest.add(divRequestBox);
        
        loadRequest = new Button("Load Request");
        divRequest.add(loadRequest);
                
        loadRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int result = fileChooser.showOpenDialog(divRequestBox);
            	chooseFile(result, "request");
            	}
            }
        );
        
       
        
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
    	    	List<Intersection> intersections = fileLoader.loadIntersection(selectedFile.getAbsolutePath());
        	    List<Road> roads = fileLoader.loadRoad(selectedFile.getAbsolutePath());
        	    CityMap map = new CityMap(roads,intersections);
        	    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
    	    	break;
    	    case "request":
    	    	fileLoader.loadRequest(selectedFile.getAbsolutePath());
    	    	break;
    	    }
    	    
    	}
    }
    
    public synchronized void write(String msg) {
        synchronized(textArea) {
            if(!msg.isEmpty()) {
                textArea.append(msg + "\n");
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        }
    }
    
    public synchronized void clear() {
        synchronized(textArea) {
            textArea.setText("");
        }
    }
    
    public static void main(String[] args) {
        try{
            new ClientUI().setVisible(true);
        }
        catch(Exception e){
        }
    }
    
    public void actionPerformed(ActionEvent e){
    String message;
   
    }
    

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
     
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}

