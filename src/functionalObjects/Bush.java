package functionalObjects;

import loot.LootTableTemplate;
import loot.SpawnLoot;
import rendering.Resources;
import translation.Translation;

public class Bush extends FunctionalTemplate{
	
	private boolean permanent;

	public Bush(int posX, int posY, boolean isExisting, int spawnChance, LootTableTemplate loot) {
		super(75, 75, posX, posY, 50, Translation.get("interaction.grabBush"), Resources.bush, true, isExisting);
		this.lootTableTemplate = loot;
		this.name="Bush";
		if(!permanent) {
			int random = (int) (Math.random()*100);
			if(random>spawnChance) {
				this.isExisting = false;
			}else {
				this.isExisting = true;
			}
		}
	}
	
	@Override
	public void performAction() {
		super.performAction();
		SpawnLoot.around(posX, posY, 50, lootTableTemplate);		
	}
	
}
