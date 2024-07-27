package loot.items.coins;

import game.GameLogic;
import rendering.Resources;
import translation.Translation;

public class SilverCoin extends CoinTemplate{

	public SilverCoin(int dropChance) {
		super(2, dropChance, Translation.get("item.silverCoin"), Resources.silverCoin, 5);
		maxStackSize = 50;
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
