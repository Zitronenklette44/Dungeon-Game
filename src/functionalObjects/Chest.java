package functionalObjects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;
import loot.LootTableTemplate;
import loot.SpawnLoot;
import spells.SpellIcons;
import translation.Translation;

public class Chest extends FunctionalTemplate{
	LootTableTemplate lootTableTemplate;
	public boolean isPermanent;
	

	public Chest(int posX, int posY, boolean isExisting, LootTableTemplate lootTable) {
		super(50, 75, posX, posY, 50, Translation.get("interaction.chestOpen"), getImage(), false, isExisting);
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
	
	private static BufferedImage getImage() {
		try {
			return ImageIO.read(SpellIcons.class.getResource("/resources/objects/chest.png"));
		} catch (IOException e) {
			Logger.logError("Chest Image Error: ", e);
		}
		return null;
	}

}
