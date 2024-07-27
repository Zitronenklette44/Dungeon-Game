package rendering;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class DrawQuests extends JLabel {
	private static final long serialVersionUID = 1L;

	public DrawQuests() {

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int width = (getWidth() / 10) * 8;
		int heigt = (getHeight() / 100) * 91;
		
		g.setColor(new Color(0,0,0, 150));
		g.fillRect(0, 0, getWidth() , getHeight());
		
		
		g.setColor(Color.black);
		g.fillRoundRect(getWidth() / 10, getHeight() / 16, width , heigt, 40, 40);
		
		g.setColor(new Color(42, 28, 18));
		g.fillRoundRect(getWidth() / 10, getHeight() / 16, width - 3 , heigt - 3, 40, 40);
		
		
	}

}
