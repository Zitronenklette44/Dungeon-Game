package loot.items.coins;

import java.awt.image.BufferedImage;

import loot.items.ItemTemplate;

public class CoinTemplate extends ItemTemplate{
	
	public int value;
	public boolean wasCollected = false;

	public CoinTemplate(int ItemID, int dropChance, String itemName, BufferedImage itemImage, int value) {
		super(ItemID, dropChance, itemName, itemImage);
		this.value = value;
	}

}
