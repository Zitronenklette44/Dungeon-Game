package loot.items.villageLoot;

import action.Logger;
import loot.items.ItemTemplate;
import rendering.Resources;
import translation.Translation;

public class Valuebles extends ItemTemplate{
	
	public int sellValue;
	
	public Valuebles(int dropChance) {
		super(6, dropChance, "", null);
		generateRandomVariant();
	}
	
	public Valuebles(int dropChance, int variant) {
		super(6, dropChance, "", null);
		generateVariant(variant);
	}
	
	private void generateVariant(int variant) {
		switch (variant) {
		case 0: 
			itemName = Translation.get("item.ring");
			itemImage = Resources.ring;
			this.variant = 0;
			maxStackSize = 20;
			sellValue = 10;
			break;
		case 1:
			itemName = Translation.get("item.kette");
			itemImage = Resources.kette;
			this.variant = 1;
			maxStackSize = 10;
			sellValue = 20;
			break;
		case 2:
			itemName = Translation.get("item.armreif");
			itemImage = Resources.armreif;
			this.variant = 2;	
			maxStackSize = 50;
			break;
		default:
			Logger.logError("invalid Valuebles variant: " + variant, new IllegalArgumentException());
	        Logger.logWarning("Valuebles Variant has been set to ring");
	        itemName = Translation.get("item.ring");
	        itemImage = Resources.ring;
	        this.variant = 0;
	        maxStackSize = 20;
	        sellValue = 10;
			break;
		}
		
	}

	private void generateRandomVariant() {
	    int random = (int) (Math.random() * 100);
	    
	    if (random >= 0 && random <= 50) {
	        itemName = Translation.get("item.ring");
	        itemImage = Resources.ring;
	        variant = 0;
	        maxStackSize = 20;
	        sellValue = 10;
	    } else if (random >= 51 && random <= 90) {
	        itemName = Translation.get("item.kette");
	        itemImage = Resources.kette;
	        variant = 1;
	        maxStackSize = 10;
	        sellValue = 20;
	    } else if (random >= 91) {
	        itemName = Translation.get("item.armreif");
	        itemImage = Resources.armreif;
	        variant = 2;
	        maxStackSize = 5;
	        sellValue = 50;
	    } else {
	        Logger.logError("invalid Valuebles variant: " + random, new IllegalArgumentException());
	        Logger.logWarning("Valuebles Variant has been set to ring");
	        itemName = Translation.get("item.ring");
	        itemImage = Resources.ring;
	        variant = 0;
	        maxStackSize = 5;
	        sellValue = 10;
	    }
	}
	
}
