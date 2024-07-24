package inventory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class Inventory extends JLabel{
	private static final long serialVersionUID = 1L;
	private boolean craftingTab = false;
	public int maxInventarSlots;
	
	public Inventory(int maxInventarSlots) {
		this.maxInventarSlots = maxInventarSlots;
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(!isVisible()) {return;}
		paintUniversal((Graphics2D)g);
		if(craftingTab) {
			paintSimpleCrafting((Graphics2D)g);
		}else {
			paintInventory((Graphics2D)g);
		}
		
	}
	
	
	private void paintInventory(Graphics2D g) {
		int posX = (getWidth()/100)*15;
		int posY = (getHeight()/100)*16;
		int currentInventarSlots = 0;
		
		//slots background
		g.setColor(Color.gray.darker());
		g.fillRoundRect((getWidth()/100)*14, (getHeight()/100)*14, 373, 575, 20, 20);
		g.setColor(Color.black);
		g.drawRoundRect((getWidth()/100)*14, (getHeight()/100)*14, 373, 575, 20, 20);
		
		//Inventar Slots
		for(int i = 0; i<8;i++) {
			for (int j = 0; j < 5; j++) {
				if(currentInventarSlots>=maxInventarSlots) {break;}
				g.setColor(Color.gray);
				g.fillRect(posX, posY, 60, 60);
				g.setColor(Color.black);
				g.drawRect(posX, posY, 60, 60);
				g.drawImage(InventoryManager.getItemImage(currentInventarSlots), posX+5, posY+5, 50, 50, null);
				if(!InventoryManager.isSlotEmpty(currentInventarSlots)) {
					g.setColor(Color.white);
					g.setFont(new Font("Tahoma", Font.BOLD, 12));
					g.drawString(InventoryManager.getItemCount(currentInventarSlots)+"", posX+40, posY+50);
				}
				posX += 70;
				currentInventarSlots++;
			}
			posY += 70;
			posX = (getWidth()/100)*15;
		}
		
		//player space
		g.setColor(Color.gray.darker());
		g.fillRoundRect((getWidth()/100)*55, (getHeight()/100)*14, 425, 575, 20, 20);
		g.setColor(Color.black);
		g.drawRoundRect((getWidth()/100)*55, (getHeight()/100)*14, 425, 575, 20, 20);
		g.fillRoundRect((getWidth()/100)*70, (getHeight()/100)*35, 250, 250, 20, 20);
		
		//player
		g.setColor(Color.white);
		g.fillRect((getWidth()/100)*80, (getHeight()/100)*60, 50, 50);
		
		
		
	}
	
	private void paintSimpleCrafting(Graphics2D g) {
		
		
	}
	
	
	private void paintUniversal(Graphics2D g) {
		//background
		g.setColor(new Color(0,0,0,150));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//basic Frame
//		g.setColor(Color.gray.darker().darker());
		g.setColor(new Color(62,62,62,150));
		g.fillRoundRect(getWidth()/10, getHeight()/10, (getWidth()/10)*8, (getHeight()/10)*8, 40, 40);
		g.setColor(Color.black);
		g.setStroke(new BasicStroke(5));
		g.drawRoundRect(getWidth()/10, getHeight()/10, (getWidth()/10)*8, (getHeight()/10)*8, 40, 40);
	}
	
	
	public void setMaxInventarSlots(int maxInventarSlots) {
		this.maxInventarSlots = maxInventarSlots;
	}
	

}
