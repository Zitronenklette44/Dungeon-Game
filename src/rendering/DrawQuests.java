package rendering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

import loot.GenerateLoot;
import loot.LootTabels;
import questSystem.quests.QuestTemplate;

public class DrawQuests extends JLabel {
	private static final long serialVersionUID = 1L;
	private static QuestTemplate quest;
	
	public DrawQuests() {
		quest = GenerateLoot.generateQuest(LootTabels.createNewFQuest());
	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2D = (Graphics2D) g;
		
		int width = (getWidth() / 10) * 8;
		int heigt = (getHeight() / 100) * 91;
		
		g2D.setColor(new Color(0,0,0, 150));
		g2D.fillRect(0, 0, getWidth() , getHeight());
		
		g2D.setColor(new Color(42, 28, 18));
		g2D.fillRect(getWidth() / 10, getHeight() / 16, width , heigt);
		
		g2D.setStroke(new BasicStroke(5));
		
		g2D.setColor(new Color(62, 48, 38));
		g2D.fillRect(getWidth() / 10 + 31, getHeight() / 16 + 31, width - 62 , heigt - 62);							
		
		g2D.setColor(Color.black);
		g2D.drawRect(getWidth() / 10, getHeight() / 16, width , heigt);															//Äußere Rahmen
		
		g2D.drawRect(getWidth() / 10 + 30, getHeight() / 16 + 30, width - 60 , heigt - 60);										//Innere Rahmen
		
		g2D.drawLine(getWidth() / 10 + 30, getHeight() / 16,getWidth() / 10 + 30, getHeight() / 16 + heigt); 					// Linke linie
		
		g2D.drawLine(getWidth() / 10 + width - 30, getHeight() / 16,getWidth() / 10 + width - 30, getHeight() / 16 + heigt);   	// Rechte Linie
		
		g2D.setColor(new Color(217,170,135));
		g2D.fillRect(getWidth() / 3 - 20, (int) (getHeight() / 7.5), 400, 525);
		
		g2D.setColor(Color.black);
		g2D.drawRect(getWidth() / 3 - 20, (int) (getHeight() / 7.5), 400, 525);
		
		g2D.setColor(new Color(201,61,78));
		g2D.fillOval(getWidth() / 3 + 280,  (int) (getHeight() / 7.5 + 20) , 80, 80);
		
		g2D.setStroke(new BasicStroke(3));
		
		g2D.setColor(Color.black);
		g2D.drawOval(getWidth() / 3 + 280,  (int) (getHeight() / 7.5 + 20) , 80, 80);
		
		g2D.setStroke(new BasicStroke(5));
		g2D.setFont(new Font("Tahoma", Font.BOLD, 60));
		g2D.drawString(""+quest.difficulty,getWidth() / 3 + 303,  (int) (getHeight() / 7.5 + 80));
		g2D.setFont(new Font("Tahoma", Font.BOLD, 50));
		g2D.drawString(""+quest.name,getWidth() / 3 + 3,  (int) (getHeight() / 7.5 + 75));
		g2D.setFont(new Font("Tahoma", Font.BOLD, 25));
		g2D.drawString(""+quest.description,getWidth() / 3 + 3,  (int) (getHeight() / 7.5 + 150));
	}

}
