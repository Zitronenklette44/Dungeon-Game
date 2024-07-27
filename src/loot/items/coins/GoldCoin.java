package loot.items.coins;

import game.GameLogic;
import rendering.Resources;
import translation.Translation;

public class GoldCoin extends CoinTemplate{

	public GoldCoin(int dropChance) {
		super(3, dropChance, Translation.get("item.goldCoin"), Resources.goldCoin, 10);
		maxStackSize = 10;
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
