/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.widgets;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author wuchenya
 */
public class Button extends JButton {
    private static Color DarkBleu = new Color(59,69,89);
    private static Color LightBleu = new Color(176,224,230);
    private static Color White = new Color(255,255,255);
    private static Color LightRed = new Color(237,217,205);
    private static Color DarkRed = new Color(182,83,62);
    
    public Button(String text) {
        super(text);
        this.setBackground(DarkBleu);
        this.setForeground(White);
        this.setFont(new Font("Arial", Font.BOLD, 16));
        this.setFocusable(false);
        this.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        this.addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent e) {
                    setBorder(BorderFactory.createLoweredSoftBevelBorder());
            }
            public void mouseReleased(MouseEvent e) {
                    setBorder(BorderFactory.createRaisedSoftBevelBorder());
            }
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mouseClicked(MouseEvent e) {}
        });
    }
}
