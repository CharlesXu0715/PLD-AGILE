/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.widgets;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 *
 * @author wuchenya
 */
public class TextField extends JTextField {
    private static Color DarkBleu = new Color(59,69,89);
    private static Color LightBleu = new Color(176,224,230);
    private static Color White = new Color(255,255,255);
    private static Color LightRed = new Color(237,217,205);
    private static Color DarkRed = new Color(182,83,62);
    
    public TextField() {
        this.setForeground(Color.BLACK);
        this.setBackground(White);
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    }
}
