package loot.items.villageLoot;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;
import game.GameLogic;
import loot.items.ItemTemplate;
import spells.SpellIcons;

public class Food extends ItemTemplate{
	private static BufferedImage bread;
	private static BufferedImage apple;
	private static BufferedImage potato;
	
	public int healthRegeneration = 0;

	public Food(int dropChance) {
		super(4, dropChance, "", null);
		interactable = true;
		generateRandomVariant();
	}
	
	private void generateRandomVariant() {
		setImage();
		int random = (int) (Math.random()*3);
		switch (random) {
		case 0: 
			itemName = "Bread";
			itemImage = bread;
			healthRegeneration = 5;
			variant = 0;
			break;
		case 1:
			itemName = "Apple";
			itemImage = apple;
			healthRegeneration = 3;
			variant = 1;
			break;
		case 2:
			itemName = "Potato";
			itemImage = potato;
			healthRegeneration = 2;
			variant = 2;	
			break;
		default:
			Logger.logError("invalid food variant: "+random, new IllegalArgumentException());
			Logger.logWarning("Food Variant has been set to bread");
			itemName = "Bread";
			itemImage = bread;
			variant = 0;
			break;
		}
		
		
	}
	
	private static void setImage() {
		try {
			bread = ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/Bread.png"));
			apple = ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/Apple.png"));
			potato = ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/Potato.png"));
		} catch (IOException e) {
			Logger.logError("Food Image Error: ", e);
		}
	}
	
	@Override
	public void onInteraction() {
		super.onInteraction();
		if(GameLogic.player.Hp+healthRegeneration <= GameLogic.player.maxHp)
			GameLogic.player.Hp+=healthRegeneration;
		else 
			GameLogic.player.Hp= GameLogic.player.maxHp;
	}

}
