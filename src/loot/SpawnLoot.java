package loot;

import action.Logger;
import loot.items.EmptyItem;

public class SpawnLoot {
	
	public static void at(float posX, float posY, LootTableTemplate loot){
		loot.generateLoot();
		for (int i = 0; i < loot.rolls; i++) {
			loot.lastGeneratedLoot.get(i).changeToObject(posX, posY);
		}
	}
	
	public static void around(float posX, float posY, int range, LootTableTemplate loot){
		loot.generateLoot();
		for (int i = 0; i < loot.rolls; i++) {
			if (!(loot.lastGeneratedLoot.get(i) instanceof EmptyItem)) {
				int addX = (int) (Math.random() * range) - range / 2;
	            int addY = (int) (Math.random() * range) - range / 2;
				loot.lastGeneratedLoot.get(i).changeToObject(posX+addX, posY+addY);
			}
		}
		Logger.logInfo("Loot spawned");
	}

}
