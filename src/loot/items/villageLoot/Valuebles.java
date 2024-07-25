package loot.items.villageLoot;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;
import loot.items.ItemTemplate;
import spells.SpellIcons;
import translation.Translation;

public class Valuebles extends ItemTemplate{
	
	public int sellValue;
	
	private static BufferedImage ring;
	private static BufferedImage kette;
	private static BufferedImage armreif;
	
	public Valuebles(int dropChance) {
		super(6, dropChance, "", null);
		generateRandomVariant();
	}
	
	public Valuebles(int dropChance, int variant) {
		super(6, dropChance, "", null);
		generateVariant(variant);
	}
	
	private void generateVariant(int variant) {
		setImage();
		switch (variant) {
		case 0: 
			itemName = Translation.get("item.ring");
			itemImage = ring;
			this.variant = 0;
			maxStackSize = 20;
			sellValue = 10;
			break;
		case 1:
			itemName = Translation.get("item.kette");
			itemImage = kette;
			this.variant = 1;
			maxStackSize = 10;
			sellValue = 20;
			break;
		case 2:
			itemName = Translation.get("item.armreif");
			itemImage = armreif;
			this.variant = 2;	
			maxStackSize = 5;
			break;
		default:
			Logger.logError("invalid Valuebles variant: " + variant, new IllegalArgumentException());
	        Logger.logWarning("Valuebles Variant has been set to ring");
	        itemName = Translation.get("item.ring");
	        itemImage = ring;
	        this.variant = 0;
	        maxStackSize = 20;
	        sellValue = 50;
			break;
		}
		
	}

	private void generateRandomVariant() {
	    setImage();
	    int random = (int) (Math.random() * 100);
	    
	    if (random >= 0 && random <= 50) {
	        itemName = Translation.get("item.ring");
	        itemImage = ring;
	        variant = 0;
	        maxStackSize = 20;
	        sellValue = 10;
	    } else if (random >= 51 && random <= 90) {
	        itemName = Translation.get("item.kette");
	        itemImage = kette;
	        variant = 1;
	        maxStackSize = 10;
	        sellValue = 20;
	    } else if (random >= 91) {
	        itemName = Translation.get("item.armreif");
	        itemImage = armreif;
	        variant = 2;
	        maxStackSize = 5;
	    } else {
	        Logger.logError("invalid Valuebles variant: " + random, new IllegalArgumentException());
	        Logger.logWarning("Valuebles Variant has been set to ring");
	        itemName = Translation.get("item.ring");
	        itemImage = ring;
	        variant = 0;
	        maxStackSize = 5;
	        sellValue = 50;
	    }
	}

	
	private static void setImage() {
		try {
			ring = ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/Ring.png"));
			kette = ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/Kette.png"));
			armreif = ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/Armreif.png"));
		} catch (IOException e) {
			Logger.logError("Food Image Error: ", e);
		}
	}
	
}
