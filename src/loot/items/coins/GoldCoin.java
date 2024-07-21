package loot.items.coins;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;
import game.GameLogic;
import spells.SpellIcons;
import translation.Translation;

public class GoldCoin extends CoinTemplate{

	public GoldCoin(int dropChance) {
		super(3, dropChance, Translation.get("item.goldCoin"), getImage(), 10);
		// TODO Auto-generated constructor stub
	}
	
	
	private static BufferedImage getImage() {
		try {
			return ImageIO.read(SpellIcons.class.getResource("/resources/Icons/items/GoldCoin.png"));
		} catch (IOException e) {
			Logger.logError("GoldCoin Image Error: ", e);
		}
		return null;
	}
	
	@Override
	public void pickedUp() {
		super.pickedUp();
		if(!wasCollected) {
			GameLogic.Coins += 10;
			wasCollected = true;
		}
	}

}
