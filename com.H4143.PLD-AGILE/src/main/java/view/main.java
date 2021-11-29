package view;

import controller.*;

public class main {
    public static void main(String[] args)
    {
        Controller controller=new Controller();
        ClientUI clientui=new ClientUI(controller);
        clientui.setVisible(true);
        
    }
}
