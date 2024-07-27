package loot.items.villageLoot;

import action.Logger;
import loot.items.ItemTemplate;
import rendering.Resources;
import translation.Translation;

public class Herbs extends ItemTemplate{
	public Herbs(int dropChance) {
		super(5, dropChance, "", null);
		generateRandomVariant();
	}
	
	public Herbs(int dropChance, int variant) {
		super(5, dropChance, "", null);
		generateVariant(variant);
	}
	
	private void generateVariant(int variant) {
		switch (variant) {
		case 0: 
			itemName = Translation.get("item.mondtau");
			itemImage = Resources.mondtau;
			this.variant = 0;
			break;
		case 1:
			itemName = Translation.get("item.daemerungswurz");
			itemImage = Resources.daemmerungswurz;
			this.variant = 1;
			break;
		case 2:
			itemName = Translation.get("item.schattenkraut");
			itemImage = Resources.schattenkraut;
			this.variant = 2;	
			break;
		default:
			Logger.logError("invalid Herb variant: "+variant, new IllegalArgumentException());
			Logger.logWarning("Herb Variant has been set to Mondtau");
			itemName = Translation.get("item.mondtau");
			itemImage = Resources.mondtau;
			this.variant = 0;
			break;
		}
		
	}

	private void generateRandomVariant() {
	    int random = (int) (Math.random() * 100);
	    if (random >= 0 && random <= 50) {
	        itemName = Translation.get("item.mondtau");
	        itemImage = Resources.mondtau;
	        variant = 0;
	    } else if (random >= 51 && random <= 90) {
	        itemName = Translation.get("item.daemerungswurz");
	        itemImage = Resources.daemmerungswurz;
	        variant = 1;
	    } else if (random >= 91) {
	        itemName = Translation.get("item.schattenkraut");
	        itemImage = Resources.schattenkraut;
	        variant = 2;
	    } else {
	        Logger.logError("invalid Herb variant: " + random, new IllegalArgumentException());
	        Logger.logWarning("Herb Variant has been set to Mondtau");
	        itemName = Translation.get("item.mondtau");
	        itemImage = Resources.mondtau;
	        variant = 0;
	    }
	}

}
