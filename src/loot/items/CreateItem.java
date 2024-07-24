package loot.items;

import action.Logger;
import loot.items.coins.*;

public class CreateItem {	
	 public static ItemTemplate createEmpty(int dropChance) {	//ID = 0
		 return new EmptyItem(dropChance);
	}
	
	public static ItemTemplate createCopperCoin(int dropChance) {	//ID = 1
		 return new CopperCoin(dropChance);
	}
	
	public static ItemTemplate createSilverCoin(int dropChance) {	//ID = 2
		 return new SilverCoin(dropChance);
	}
	
	public static ItemTemplate createGoldCoin(int dropChance) {	//ID = 3
		 return new GoldCoin(dropChance);
	}
	
	
	
	public static ItemTemplate getItembyID(int ID) {
		switch (ID) {
		case 0: return new EmptyItem(0);
		case 1: return new CopperCoin(0);
		case 2: return new SilverCoin(0);
		case 3: return new GoldCoin(0);
		default:
			Logger.logError("Invalid Item ID in Configs:"+ID, new IllegalArgumentException());
			return null;
		}

	}
	
	

}
