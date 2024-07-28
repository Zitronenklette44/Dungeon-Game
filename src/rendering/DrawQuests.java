package rendering;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JLabel;

import action.Logger;
import loot.GenerateLoot;
import loot.LootTabels;
import loot.QuestTable;
import questSystem.QuestManager;
import questSystem.quests.QuestTemplate;

public class DrawQuests extends JLabel {
	private static final long serialVersionUID = 1L;
	private static final int MAX_CHARACTER_LIMIT = 30;
	private static final int DESCRIPTION_OFFSET = 17;
	private static final int DESCRIPTION_LIMIT = 10;

	private static QuestTemplate quest;
	private static QuestTable questTable;
	private static ArrayList<String> description = new ArrayList<String>();

	private int btn1X, btn1Y, btn1width, btn1hight;
	private int btn2X, btn2Y, btn2width, btn2hight;

	public DrawQuests() {
		questTable = LootTabels.createNewFQuest();
		quest = GenerateLoot.generateQuest(questTable);
		splitDescription(quest.description);
		initMouseListener();
	}

	private void initMouseListener() {
		addMouseListener(new MouseAdapter() {			
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					boolean triggerButton1 = false;
					boolean triggerButton2 = false;

					
					if(e.getX()>=btn1X&&e.getX()<=btn1X+btn1width && e.getY()>=btn1Y && e.getY() <=btn1Y+btn1hight) triggerButton1 = true;

					if(e.getX()>=btn2X&&e.getX()<=btn2X+btn2width && e.getY()>=btn2Y && e.getY()<=btn2Y+btn2hight) triggerButton2 = true;
					
					if(triggerButton1) {			//annhemen
						QuestManager.setNewQuest(quest);
					}

					if(triggerButton2) {		//ablehnen
						quest = GenerateLoot.generateQuest(questTable);
						splitDescription(quest.description);
					}
				}

			}
		});

	}


	private void splitDescription(String description) {
		String[] descriptionWord = description.split(" ");
		ArrayList<String> words = new ArrayList<String>();
		for(int i = 0;i<descriptionWord.length;i++) {
			words.add(descriptionWord[i]);
		}

		ArrayList<String> descriptionLine = new ArrayList<String>();
		int currentCharacters = 0;
		int currentLines = 0;
		while (words.size() > 0) {
			StringBuilder lineBuilder = new StringBuilder();
			currentCharacters = 0;


			while(words.size() > 0 && currentCharacters + words.getFirst().length() <= MAX_CHARACTER_LIMIT) {
				if(currentCharacters >0 ) {
					lineBuilder.append(" ");
					currentCharacters++;
				}

				lineBuilder.append(words.getFirst());
				currentCharacters+=words.getFirst().length();
				words.removeFirst();
			}
			if(currentLines < DESCRIPTION_LIMIT) {
				descriptionLine.add(lineBuilder.toString());
				currentLines ++;
			}
		}
		DrawQuests.description = descriptionLine;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!isVisible()) return;

		Graphics2D g2D = (Graphics2D) g;

		int width = (getWidth() / 10) * 8;
		int heigt = (getHeight() / 100) * 91;

		g2D.setColor(new Color(0,0,0, 150));
		g2D.fillRect(0, 0, getWidth() , getHeight());

		g2D.setColor(new Color(42, 28, 18).brighter().brighter());
		g2D.fillRect(getWidth() / 10, getHeight() / 16, width , heigt);

		g2D.setStroke(new BasicStroke(5));

		g2D.setColor(new Color(62, 48, 38).brighter().brighter());
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
		g2D.drawString(""+quest.difficulty,getWidth() / 3 + 303,  (int) (getHeight() / 7.5 + 80));				//Rang
		g2D.setFont(new Font("Tahoma", Font.BOLD, 50));
		g2D.drawString(""+quest.name,getWidth() / 3 + 3,  (int) (getHeight() / 7.5 + 75));						//Name

		g2D.setFont(new Font("Tahoma", Font.BOLD, 20));															//Beschribung

		for(int i = 0; i< description.size(); i++) {
			g2D.drawString(description.get(i),getWidth() / 3 + 3,  (int) (getHeight() / 7.5 + 150)+(DESCRIPTION_OFFSET*i));
		}

		switch (quest.questType){
		case 0: {
			g2D.drawString("Töte : ",getWidth() / 3 + 3,  (int) (getHeight() / 3 + 190));
			g.drawString(MobID(),(int) (getWidth() / 2.25 + 50),  (int) (getHeight() / 3 + 190));
		}break;
		case 1: {
			g2D.drawString("Sammel : ",getWidth() / 3 + 3,  (int) (getHeight() / 3 + 190));
			g.drawString(quest.item.itemName,(int) (getWidth() / 2.25 + 50),  (int) (getHeight() / 3 + 190));

		}break;
		case 2: {
			g2D.drawString("Sammel : ",getWidth() / 3 + 3,  (int) (getHeight() / 3 + 190));
			g.drawString(quest.nameGroup,(int) (getWidth() / 2.25 + 50),  (int) (getHeight() / 3 + 190));

		}break;
		default:
			Logger.logError("Unexpected value: "+ quest.questType, new IllegalArgumentException());

		}

		g2D.drawString(quest.Numbers+"x",(int) (getWidth() / 2.5 + 50),  (int) (getHeight() / 3 + 190));

		g2D.setFont(new Font("Tahoma", Font.BOLD, 20));

		g2D.drawString("Reward:", getWidth() / 3 + 3,  (int) (getHeight() / 2 + 90));
		g2D.drawString("- "+quest.reward.rolls+"x "+quest.reward.name, getWidth() / 3 + 20,  (int) (getHeight() / 2 + 115));

		btn1X =  getWidth() / 3 + 30;
		btn1Y = (int) (getHeight() / 2 + 170);
		btn1hight = 25;
		btn1width = 120;
		
		btn2X =  getWidth() / 3 + 210;
		btn2Y = (int) (getHeight() / 2 + 170);
		btn2hight = 25;
		btn2width = 120;
		
		g2D.setColor(Color.gray);
		g2D.fillRect(btn1X,btn1Y, btn1width, btn1hight);
		g2D.fillRect(btn2X,btn2Y, btn2width, btn2hight);
		
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g2D.setColor(Color.black);
        g.drawString("Annehmen", (int) (btn1X+btn1width/5+1), (int) (btn1Y+btn1hight/1.5));
        g.drawString("Ablehnen", (int) (btn2X+btn2width/4), (int) (btn2Y+btn2hight/1.5));
	
		
	}

	public String MobID() {
		String name = "Invalid Mob";
		switch (quest.MobID) {
		case 1: 
			name = "Test Mob";
			break;
		case 3: 
			name = "Swordmen";
			break;
		default:
			Logger.logError("Unexpected value: "+ quest.MobID, new IllegalArgumentException());
		}
		return name;
	}


}
