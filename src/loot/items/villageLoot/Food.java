package loot.items.villageLoot;

import action.Logger;
import game.GameLogic;
import loot.items.ItemTemplate;
import rendering.Resources;
import translation.Translation;

public class Food extends ItemTemplate{

	public int healthRegeneration = 0;

	public Food(int dropChance) {
		super(4, dropChance, "", null);
		interactable = true;
		generateRandomVariant();
	}

	public Food(int dropChance, int variant) {
		super(4, dropChance, "", null);
		interactable = true;
		generateVariant(variant);
	}

	private void generateVariant(int variant) {
		switch (variant) {
		case 0: 
			itemName = Translation.get("item.bread");
			itemImage = Resources.bread;
			healthRegeneration = 5;
			this.variant = 0;
			break;
		case 1:
			itemName = Translation.get("item.apple");
			itemImage = Resources.apple;
			healthRegeneration = 3;
			this.variant = 1;
			break;
		case 2:
			itemName = Translation.get("item.potato");
			itemImage = Resources.potato;
			healthRegeneration = 2;
			this.variant = 2;	
			break;
		default:
			Logger.logError("invalid food variant: "+variant, new IllegalArgumentException());
			Logger.logWarning("Food Variant has been set to bread");
			itemName = Translation.get("item.bread");
			itemImage = Resources.bread;
			this.variant = 0;
			break;
		}

	}

	private void generateRandomVariant() {
		int random = (int) (Math.random()*100);
		if (random >= 0 && random <= 50) {
			itemName = Translation.get("item.potato");
			itemImage = Resources.potato;
			healthRegeneration = 2;
			variant = 0;
		} else if (random >= 51 && random <= 90) {
			itemName = Translation.get("item.apple");
			itemImage = Resources.apple;
			healthRegeneration = 3;
			variant = 1;
		} else if (random >= 91) {
			itemName = Translation.get("item.bread");
			itemImage = Resources.bread;
			healthRegeneration = 5;

			variant = 2;	
		} else {
			Logger.logError("invalid food variant: "+random, new IllegalArgumentException());
			Logger.logWarning("Food Variant has been set to potato");
			itemName = Translation.get("item.potato");
			itemImage = Resources.potato;
			variant = 0;
		}


	}

	@Override
	public void onInteraction() {
		super.onInteraction();

		if(GameLogic.player.Hp+healthRegeneration <= GameLogic.player.maxHp)
			GameLogic.player.Hp+=healthRegeneration;
		else 
			GameLogic.player.Hp= GameLogic.player.maxHp;

	}
	
	@Override
	public boolean canInteract() {
		boolean canInteract = false;
		if(GameLogic.player.Hp < GameLogic.player.maxHp) canInteract = true;
		return canInteract;
	}
	
}
