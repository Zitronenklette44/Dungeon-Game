package components;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class SpellButton extends JButton {
    private static final long serialVersionUID = 1L;
    private BufferedImage spellIcon;	//image für Button
    private boolean isEquipSlot;	//Wenn Spezieller Button ist

    public SpellButton(BufferedImage spellIcon, boolean isEquipSlot) {
        this.spellIcon = spellIcon;
        this.isEquipSlot = isEquipSlot;
        initButton();
    }

    public SpellButton(BufferedImage spellIcon) {
        this(spellIcon, false);
    }

    private void initButton() {
        setBorderPainted(true); 
        setFocusPainted(false); 

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPress();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {	//Malen des Images in den Button
    	g.setColor(Color.lightGray.darker());	//Hintergrund
    	g.fillRect(0, 0, getWidth(), getHeight());
        if (spellIcon != null) {        	
            g.drawImage(spellIcon, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    protected void paintBorder(Graphics g) {	//Borders um Button wenn kein Image vorhanden
        if (isEquipSlot|| isEquipSlot && spellIcon == null) {
            super.paintBorder(g); // Zeichne den Rahmen nur, wenn isEquipSlot true ist
            if(spellIcon == null)
            	setBorder(new LineBorder(Color.orange, 3));
        }
    }

    public void onPress() {
        if (!isEquipSlot) {
            return;		//wenn kein besonderer Button
        }
        spellIcon = null;	//image löschen
        setBorder(new LineBorder(Color.orange, 3));	//Border hinzufügen
        repaint(); // Button neu zeichnen
    }
}
