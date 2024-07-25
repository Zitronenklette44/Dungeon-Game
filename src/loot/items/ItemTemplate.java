package loot.items;

import java.awt.image.BufferedImage;
import java.util.UUID;

import action.Logger;
import game.GameLogic;
import inventory.InventoryManager;

public class ItemTemplate implements Cloneable {

	public UUID id;
	public int ItemID;
	public boolean isObject;
	public String itemName;
	public int dropChance;
	public int stackSize = 1;
	public int pickUpCooldown;
	public int maxStackSize = 99;
	public int variant = -1;

	public float posX;
	public float posY;

	public boolean isVisible = true;
	public BufferedImage itemImage;
	public boolean interactable = false;

	public ItemTemplate(int ItemID, int dropChance, String itemName, BufferedImage itemImage) {
		this.id = UUID.randomUUID(); // Eindeutige UUID generieren
		this.ItemID = ItemID;
		this.dropChance = dropChance;
		this.itemName = itemName;
		this.itemImage = itemImage;
	}

	public ItemTemplate(int ItemID, int dropChance, String itemName, BufferedImage itemImage, int stackSize) {
		this(ItemID, dropChance, itemName, itemImage);
		this.stackSize = stackSize;
	}

	@Override
	public ItemTemplate clone() {
		try {
			ItemTemplate clone = (ItemTemplate) super.clone();
			clone.id = UUID.randomUUID(); // Neuen UUID für den Klon generieren
			return clone;
		} catch (CloneNotSupportedException e) {
			Logger.logError("ItemClone error: ", e);
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
		if (pickUpCooldown <= 0) {
			pickUpCooldown = 0;
			isObject = false;
			for (int i = 0; i < GameLogic.items.size(); i++) {
				if (GameLogic.items.get(i) == this && InventoryManager.canStoreItem(this, stackSize)) {
					GameLogic.items.remove(i);
					isObject = false;
					posX = 100;
					InventoryManager.add(this, stackSize);
					break;
				}
			}
		}

	}

	// Equals und HashCode überschreiben, wenn UUID verwendet wird
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		ItemTemplate that = (ItemTemplate) obj;
		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	public void changeToObject(float posX, float posY) {
		isObject = true;
		this.posX = posX;
		this.posY = posY;
		pickUpCooldown = 2000;
		GameLogic.items.add(this);
	}
	
	public void onInteraction() {}

}
