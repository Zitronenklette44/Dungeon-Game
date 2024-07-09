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
    private BufferedImage spellIcon;
    private boolean isEquipSlot;

    public SpellButton(BufferedImage spellIcon, boolean isEquipSlot) {
        this.spellIcon = spellIcon;
        this.isEquipSlot = isEquipSlot;
        initButton();
    }

    public SpellButton(BufferedImage spellIcon) {
        this(spellIcon, false);
    }

    private void initButton() {
        setBorderPainted(true); // Button-Rahmen
        setFocusPainted(false); // Kein Fokus-Rahmen

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPress();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Zeichne nur das Icon
    	//g.setColor(new Color(0,0,0,0));
    	g.setColor(Color.lightGray.darker());
    	g.fillRect(0, 0, getWidth(), getHeight());
        if (spellIcon != null) {        	
            g.drawImage(spellIcon, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (isEquipSlot|| isEquipSlot && spellIcon == null) {
            super.paintBorder(g); // Zeichne den Rahmen nur, wenn isEquipSlot true ist
            if(spellIcon == null)
            	setBorder(new LineBorder(Color.orange, 3));
        }
    }

    public void onPress() {
        if (!isEquipSlot) {
            return;
        }
        System.out.println("run");
        spellIcon = null;
        setBorder(new LineBorder(Color.orange, 3));
        repaint(); // Button neu zeichnen
    }
}
