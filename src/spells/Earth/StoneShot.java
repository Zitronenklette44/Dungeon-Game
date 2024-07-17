package spells.Earth;

import java.awt.image.BufferedImage;

public class StoneShot extends StoneTmeplate{

	public StoneShot(float posX, float posY, float dx, float dy, String Type, int Stage, int damage, int manaConsume,
			boolean damagePlayer, BufferedImage[] images, String spellName) {
		super(posX, posY, dx, dy, Type, Stage, damage, manaConsume, damagePlayer, images, spellName);
		stunChance = 1;
	}

}
