package rendering;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

import gui.SpellChooser;


public class DrawSpellCooser extends JLabel{
	private static final long serialVersionUID = 1L;

	public DrawSpellCooser() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void paintComponent(Graphics g) {		//ungenutzt
		super.paintComponent(g);
		if(!SpellChooser.needsUpdate) {
			return;
		}
		
		g.setColor(Color.white);
		//rahmen für bilder
		//g.fillRect(70, 150, 75, 75);	//Slot 1
		g.fillRect(70, 300, 75, 75);	//Slot 2
		g.fillRect(70, 450, 75, 75);	//Slot 3
		
		
		
		
	}
}
