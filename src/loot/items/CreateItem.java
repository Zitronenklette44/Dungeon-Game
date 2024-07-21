package loot.items;

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
	
	

}
