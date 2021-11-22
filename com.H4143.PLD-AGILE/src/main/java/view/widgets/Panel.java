/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.widgets;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author wuchenya
 */
public class Panel extends JPanel {
    private static Color DarkBleu = new Color(59,69,89);
    private static Color LightBleu = new Color(176,224,230);
    private static Color White = new Color(255,255,255);
    private static Color LightRed = new Color(237,217,205);
    private static Color DarkRed = new Color(182,83,62);
    
    public Panel() {
        this.setBackground(DarkRed);
    }
	
    public Panel(Dimension dim) {
        this();
        this.setPreferredSize(dim);
    }
}
