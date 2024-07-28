package loot;

import loot.items.CreateItem;
import loot.items.ItemTemplate;
import questSystem.QuestManager;
import questSystem.quests.QuestTemplate;

public class LootTabels {
	
	public static void init(){
		
	}
	
	public static LootTableTemplate createChestCoinLoot(int rolls){
		return new LootTableTemplate(rolls, new ItemTemplate[] {CreateItem.createCopperCoin(15), CreateItem.createEmpty(0), CreateItem.createSilverCoin(5), CreateItem.createGoldCoin(1)}, "Coins");
	}
	
	public static LootTableTemplate createVillagerLoot(int rolls){
		return new LootTableTemplate(rolls, new ItemTemplate[] {CreateItem.createFood(1) ,CreateItem.createFood(1) ,CreateItem.createFood(1) ,CreateItem.createFood(1) ,CreateItem.createFood(1) ,CreateItem.createEmpty(3)}, "Food");
	}
	
	public static LootTableTemplate createHerbs(int rolls){
		return new LootTableTemplate(rolls, new ItemTemplate[] {CreateItem.createHerbs(1) ,CreateItem.createHerbs(1) ,CreateItem.createHerbs(1) ,CreateItem.createHerbs(1) ,CreateItem.createHerbs(1) ,CreateItem.createEmpty(3)}, "Herbs");
	}
	
	public static LootTableTemplate createValuebles(int rolls){
		return new LootTableTemplate(rolls, new ItemTemplate[] {CreateItem.createValuebles(1) ,CreateItem.createValuebles(1) ,CreateItem.createValuebles(1) ,CreateItem.createValuebles(1) ,CreateItem.createValuebles(1) ,CreateItem.createEmpty(3)}, "Valuebles");
	}
	
	public static QuestTable createNewFQuest() {
		int temp = QuestManager.LAST_SLOT_FORCE_QUESTS;
		return new QuestTable(new QuestTemplate[] {QuestManager.get(temp+1), QuestManager.get(temp+2), QuestManager.get(temp+3), QuestManager.get(temp+4)});
	}
	
	
	
}
