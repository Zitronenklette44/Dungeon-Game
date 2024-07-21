package loot.items.coins;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;
import spells.SpellIcons;
import translation.Translation;

public class CopperCoin extends CoinTemplate{
	
	public CopperCoin(int dropChance) {
		super(1, dropChance, Translation.get("item.copperCoin"), getImage(), 1);
	}
	
	
	private static BufferedImage getImage() {
		try {
			return ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/CopperCoin.png"));
		} catch (IOException e) {
			Logger.logError("CopperCoin Image Error: ", e);
		}
		return null;
	}
	
	@Override
	public void pickedUp() {
		super.pickedUp();
		Logger.logInfo("added "+ value +" to coins");
	}

}
