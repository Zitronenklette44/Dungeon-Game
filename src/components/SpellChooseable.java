package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import action.Logger;
import game.GameLogic;
import gui.GameScreen;
import gui.SpellChooser;
import spells.SpellManager;

public class SpellChooseable extends JButton {
    private static final long serialVersionUID = 1L;
    BufferedImage spell;
    String text;
    String spellName;
    JLabel lbText;
    JLabel lbSpell;
    boolean resizeImage;

    public SpellChooseable(BufferedImage spell, String text, String spellName, boolean resizeImage) {
        this.spell = spell;
        this.text = text;
        this.resizeImage = resizeImage;
        this.spellName = spellName;
        init();
        
    }
    
    public SpellChooseable(BufferedImage spell, String spellName, String text) {
    	this(spell, text, spellName, false);
    }

    private void init() {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPress();
            }
        });

        setLayout(null);
        text = text.replace("\n", "<br>");
        String finalText ="<html><div style='text-align: center;'>"+ text+"</div></html>";
        lbText = new JLabel(finalText);
        add(lbText);
        lbSpell = new JLabel();
        add(lbSpell);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        int spellWidth = spell.getWidth();
        int spellHeight = spell.getHeight();
        if (resizeImage) {
            spellWidth = getHeight();
            spellHeight = getHeight();
        }
        lbSpell.setBounds(0, 0, spellWidth, spellHeight);
        lbText.setBounds(spellWidth + 3, 0, getWidth() - spellWidth - 3, getHeight());
        lbSpell.setIcon(new ImageIcon(spell.getScaledInstance(spellWidth, spellHeight, BufferedImage.SCALE_SMOOTH)));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void onPress() {
    	for(int i = 0; i<3;i++) {
    		if(GameLogic.player.equipedSpells[i].isEmpty() && !SpellManager.spellExists(spellName)){
    			GameLogic.player.equipedSpells[i] = spellName;
    			SpellChooser.updateIcons();
    			break;
    		}else {
    			Logger.logWarning("No Space or Spell existing");
    		}
    	}
		GameScreen.updateSpells();
    }
}
