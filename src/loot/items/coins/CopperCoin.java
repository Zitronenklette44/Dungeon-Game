package loot.items.coins;

import game.GameLogic;
import rendering.Resources;
import translation.Translation;

public class CopperCoin extends CoinTemplate{
	
	public CopperCoin(int dropChance) {
		super(1, dropChance, Translation.get("item.copperCoin"), Resources.copperCoin, 1);
	}
	
	@Override
	public void pickedUp() {
		super.pickedUp();
		if(!wasCollected) {
			GameLogic.Coins++;
			wasCollected = true;
		}
	}

}
