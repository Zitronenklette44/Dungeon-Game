package loot;

import loot.items.CreateItem;
import loot.items.ItemTemplate;

public class LootTabels {
	
	public static void init(){
		
	}
	
	public static LootTableTemplate createChestCoinLoot(int rolls){
		return new LootTableTemplate(rolls, new ItemTemplate[] {CreateItem.createCopperCoin(5), CreateItem.createEmpty(0), CreateItem.createSilverCoin(0), CreateItem.createGoldCoin(0)});
	}

}
