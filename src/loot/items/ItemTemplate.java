package loot.items;

import java.awt.image.BufferedImage;
import java.util.UUID;

import action.Logger;
import game.GameLogic;

public class ItemTemplate implements Cloneable{
	
	public UUID id;
	public int ItemID;
	public boolean isObject;
	public String itemName;
	public int dropChance;
	
	public float posX;
	public float posY;
	
	public boolean isVisible = true;
	public BufferedImage itemImage;
	
	public ItemTemplate(int ItemID, int dropChance, String itemName, BufferedImage itemImage) {
		this.id = UUID.randomUUID(); // Eindeutige UUID generieren
		this.ItemID = ItemID;
		this.dropChance = dropChance;
		this.itemName = itemName;
		this.itemImage = itemImage;
	}
	
	@Override
	public ItemTemplate clone() {
		try {
			ItemTemplate clone = (ItemTemplate) super.clone();
            clone.id = UUID.randomUUID(); // Neuen UUID für den Klon generieren
            return clone;
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
				isObject = false;
				posX = 100;
				break;
			}
		}
			
	
	}
	
	// Equals und HashCode überschreiben, wenn UUID verwendet wird
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ItemTemplate that = (ItemTemplate) obj;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
	
	public void changeToObject(float posX, float posY) {isObject = true; this.posX = posX; this.posY = posY; GameLogic.items.add(this);}
	
	
	
	
}
