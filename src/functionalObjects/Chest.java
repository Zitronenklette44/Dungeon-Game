package functionalObjects;

import loot.LootTableTemplate;
import loot.SpawnLoot;
import rendering.Resources;
import translation.Translation;

public class Chest extends FunctionalTemplate{

	public Chest(int posX, int posY, boolean isExisting, LootTableTemplate lootTable) {
		super(50, 75, posX, posY, 50, Translation.get("interaction.chestOpen"), Resources.chest, false, isExisting);
		this.lootTableTemplate = lootTable;
		name = "chest";
	}
	public Chest(int posX, int posY, boolean isExisting, LootTableTemplate lootTable, boolean permanent, int spawnChance) {
		this(posX, posY, isExisting, lootTable);
		if(!permanent) {
			int random = (int) (Math.random()*100);
			if(random>spawnChance) {
				this.isExisting = false;
			}
		}
	}
	
	@Override
	public void performAction() {
		super.performAction();
		SpawnLoot.around(posX, posY, 50, lootTableTemplate);
	}

}
