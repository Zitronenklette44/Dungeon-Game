package loot;

import inventory.InventoryManager;

public class GenerateLoot {
	
	public static void intoIntentory(LootTableTemplate loot) {
		loot.generateLoot();
		for(int i = 0; i < loot.lastGeneratedLoot.size(); i++) {
			InventoryManager.add(loot.lastGeneratedLoot.get(i), 1);
		}
	}
	
}
