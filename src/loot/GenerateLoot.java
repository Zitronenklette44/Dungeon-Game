package loot;

import inventory.InventoryManager;
import questSystem.quests.QuestTemplate;

public class GenerateLoot {
	
	public static void intoIntentory(LootTableTemplate loot) {
		loot.generateLoot();
		for(int i = 0; i < loot.lastGeneratedLoot.size(); i++) {
			InventoryManager.add(loot.lastGeneratedLoot.get(i), 1);
		}
	}
	
	
	public static QuestTemplate generateQuest(QuestTable quest) {
		quest.generateLoot();
		return quest.lastGeneratedQuest;
	}
	
}
