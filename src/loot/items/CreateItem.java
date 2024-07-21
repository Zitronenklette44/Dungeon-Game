package loot.items;

import loot.items.coins.CopperCoin;

public class CreateItem {
	
	 public static ItemTemplate createEmpty(int dropChance) {	//ID = 0
		 return new EmptyItem(dropChance);
	}
	
	public static ItemTemplate createCopperCoin(int dropChance) {	//ID = 1
		 return new CopperCoin(dropChance);
	}
	
	

}
