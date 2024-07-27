package rendering;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class DrawQuests extends JLabel {

	public DrawQuests() {

	}

	@Override
	public void paintComponent(Graphics g) {
		int width = (getWidth() / 10) * 8;
		int heigt = (getHeight() / 100) * 91;
		
		g.setColor(new Color(0,0,0, 150));
		g.fillRect(0, 0, getWidth() , getHeight());
		
		
		g.setColor(Color.black);
		g.fillRoundRect(getWidth() / 10, getHeight() / 16, width , heigt, 40, 40);
		
		g.setColor(new Color(42.4F, 28.2F, 18.8F));
		g.fillRoundRect(getWidth() / 10, getHeight() / 16, width - 3 , heigt - 3, 40, 40);
		
		
		super.paintComponent(g);
	}

}
