package loot.items.villageLoot;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;
import loot.items.ItemTemplate;
import spells.SpellIcons;
import translation.Translation;

public class Herbs extends ItemTemplate{
	
	private static BufferedImage mondtau;
	private static BufferedImage daemmerungswurz;
	private static BufferedImage Schattenkraut;

	public Herbs(int dropChance) {
		super(5, dropChance, "", null);
		generateRandomVariant();
	}
	
	public Herbs(int dropChance, int variant) {
		super(5, dropChance, "", null);
		generateVariant(variant);
	}
	
	private void generateVariant(int variant) {
		setImage();
		switch (variant) {
		case 0: 
			itemName = Translation.get("item.mondtau");
			itemImage = mondtau;
			this.variant = 0;
			break;
		case 1:
			itemName = Translation.get("item.daemerungswurz");
			itemImage = daemmerungswurz;
			this.variant = 1;
			break;
		case 2:
			itemName = Translation.get("item.schattenkraut");
			itemImage = Schattenkraut;
			this.variant = 2;	
			break;
		default:
			Logger.logError("invalid Herb variant: "+variant, new IllegalArgumentException());
			Logger.logWarning("Herb Variant has been set to Mondtau");
			itemName = Translation.get("item.mondtau");
			itemImage = mondtau;
			this.variant = 0;
			break;
		}
		
	}

	private void generateRandomVariant() {
	    setImage();
	    int random = (int) (Math.random() * 100);
	    if (random >= 0 && random <= 50) {
	        itemName = Translation.get("item.mondtau");
	        itemImage = mondtau;
	        variant = 0;
	    } else if (random >= 51 && random <= 90) {
	        itemName = Translation.get("item.daemerungswurz");
	        itemImage = daemmerungswurz;
	        variant = 1;
	    } else if (random >= 91) {
	        itemName = Translation.get("item.schattenkraut");
	        itemImage = Schattenkraut;
	        variant = 2;
	    } else {
	        Logger.logError("invalid Herb variant: " + random, new IllegalArgumentException());
	        Logger.logWarning("Herb Variant has been set to Mondtau");
	        itemName = Translation.get("item.mondtau");
	        itemImage = mondtau;
	        variant = 0;
	    }
	}
	
	private static void setImage() {
		try {
			mondtau = ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/Mondtau.png"));
			daemmerungswurz = ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/Daemerungswurz.png"));
			Schattenkraut = ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/Schattenkraut.png"));
		} catch (IOException e) {
			Logger.logError("Food Image Error: ", e);
		}
	}

}
