package loot;

import loot.items.CreateItem;
import loot.items.ItemTemplate;

public class LootTabels {
	
	public static void init(){
		
	}
	
	public static LootTableTemplate createChestCoinLoot(int rolls){
		return new LootTableTemplate(rolls, new ItemTemplate[] {CreateItem.createCopperCoin(15), CreateItem.createEmpty(0), CreateItem.createSilverCoin(5), CreateItem.createGoldCoin(1)});
	}
	
	public static LootTableTemplate createVillagerLoot(int rolls){
		return new LootTableTemplate(rolls, new ItemTemplate[] {CreateItem.createFood(1) ,CreateItem.createFood(1) ,CreateItem.createFood(1) ,CreateItem.createFood(1) ,CreateItem.createFood(1) ,CreateItem.createEmpty(3)});
	}
	
	public static LootTableTemplate createHerbs(int rolls){
		return new LootTableTemplate(rolls, new ItemTemplate[] {CreateItem.createHerbs(1) ,CreateItem.createHerbs(1) ,CreateItem.createHerbs(1) ,CreateItem.createHerbs(1) ,CreateItem.createHerbs(1) ,CreateItem.createEmpty(3)});
	}
	
	public static LootTableTemplate createValuebles(int rolls){
		return new LootTableTemplate(rolls, new ItemTemplate[] {CreateItem.createValuebles(1) ,CreateItem.createValuebles(1) ,CreateItem.createValuebles(1) ,CreateItem.createValuebles(1) ,CreateItem.createValuebles(1) ,CreateItem.createEmpty(3)});
	}

}
