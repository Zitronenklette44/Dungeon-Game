package loot.items;

import java.awt.image.BufferedImage;

import action.Logger;
import game.GameLogic;

public class ItemTemplate implements Cloneable{
	
	public int ItemID;
	public boolean isObject;
	public String itemName;
	public int dropChance;
	
	public float posX;
	public float posY;
	
	public boolean isVisible = true;
	public BufferedImage itemImage;
	
	public ItemTemplate(int ItemID, int dropChance, String itemName, BufferedImage itemImage) {
		this.ItemID = ItemID;
		this.dropChance = dropChance;
		this.itemName = itemName;
		this.itemImage = itemImage;
	}
	
	@Override
	public ItemTemplate clone() {
		try {
			return (ItemTemplate) super.clone();
		} catch (CloneNotSupportedException e) {
			Logger.logError("ItemClone error: ",e);
		}
		return null;
	}
	
	public ItemTemplate(int ItemID, int dropChance, String itemName, BufferedImage itemImage, int posX, int posY) {
		this(ItemID, dropChance, itemName, itemImage);
		this.posX = posX;
		this.posY = posY;
		isObject = true;
	}
	
	
	public void pickedUp() {
		isObject = false; 
		for(int i = 0; i<GameLogic.items.size();i++) {
			if(GameLogic.items.get(i) == this) {
				GameLogic.items.remove(i);
				break;
			}
		}
			
	
	}
	
	public void changeToObject(float posX, float posY) {isObject = true; this.posX = posX; this.posY = posY; GameLogic.items.add(this);}
	
	
	
	
}
