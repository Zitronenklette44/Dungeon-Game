package spells.Water;

import java.awt.image.BufferedImage;

import spells.SpellTemplate;

public class WaterTemplate extends SpellTemplate{

	public WaterTemplate(float posX, float posY, float dx, float dy, String Type, int Stage, int damage,
			int manaConsume, boolean damagePlayer, BufferedImage[] images, String spellName) {
		super(posX, posY, dx, dy, Type, Stage, damage, manaConsume, damagePlayer, images, spellName);
		isSlowing = true;
	}

}
