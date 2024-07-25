package functionalObjects;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;
import loot.LootTableTemplate;
import loot.SpawnLoot;
import spells.SpellIcons;
import translation.Translation;

public class Bush extends FunctionalTemplate{
	
	private boolean permanent;

	public Bush(int posX, int posY, boolean isExisting, int spawnChance, LootTableTemplate loot) {
		super(75, 75, posX, posY, 50, Translation.get("interaction.grabBush"), getImage(), true, isExisting);
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
	
	
	private static BufferedImage getImage() {
		try {
			return ImageIO.read(SpellIcons.class.getResource("/resources/objects/Bush.png"));
		} catch (IOException e) {
			Logger.logError("Chest Image Error: ", e);
		}
		return null;
	}
	
	@Override
	public void performAction() {
		super.performAction();
		SpawnLoot.around(posX, posY, 50, lootTableTemplate);		
	}
	
}
