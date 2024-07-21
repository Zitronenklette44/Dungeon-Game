package loot.items.coins;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;
import game.GameLogic;
import spells.SpellIcons;
import translation.Translation;

public class SilverCoin extends CoinTemplate{

	public SilverCoin(int dropChance) {
		super(2, dropChance, Translation.get("item.silverCoin"), getImage(), 5);
		// TODO Auto-generated constructor stub
	}
	
	private static BufferedImage getImage() {
		try {
			return ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/SilverCoin.png"));
		} catch (IOException e) {
			Logger.logError("SilverCoin Image Error: ", e);
		}
		return null;
	}
	
	
	@Override
	public void pickedUp() {
		super.pickedUp();
		if(!wasCollected) {
			GameLogic.Coins += 5;
			wasCollected = true;
		}
	}
}
