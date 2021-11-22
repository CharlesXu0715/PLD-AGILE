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
import java.io.IOException;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 *
 * @author wuchenya
 */
public class ClientUI extends JFrame implements ActionListener ,WindowListener
{
    //panel1
    private TextField serverHost;
    private TextField serverPort;
    private Button connexion;
    //panel2
    private TextArea textArea;
    //panel3
    private TextField msgField;
    private Button send;
    private Button vide;
    

	
    
    public ClientUI()
    {
        setTitle("ClientUI");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        this.addWindowListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        
        Panel panel1=new Panel();
        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(panel1,BorderLayout.NORTH);
        
        Panel panel2=new Panel();
        panel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(panel2,BorderLayout.CENTER);
        
        Panel panel3=new Panel();
        panel3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.add(panel3,BorderLayout.SOUTH);
	
        serverHost = new TextField();
        serverHost.setText("localhost");
        serverHost.setPreferredSize(new Dimension(180, 25));
        panel1.add(new Label("Host :"));
        panel1.add(serverHost);
        
        serverPort = new TextField();
        serverPort.setText("1025");
        serverPort.setPreferredSize(new Dimension(150, 25));
        panel1.add(new Label("Port : "));
        panel1.add(serverPort);
        
        connexion=new Button("Connecter");
        connexion.addActionListener(this);
        panel1.add(connexion);
        
        textArea = new TextArea();
        panel2.setPreferredSize(new Dimension(600, 330));
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
        panel3.add(vide,BorderLayout.EAST);
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

