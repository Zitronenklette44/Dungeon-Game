package questSystem.quests;

import action.Logger;
import loot.LootTableTemplate;
import loot.items.ItemTemplate;
import loot.items.villageLoot.Food;
import loot.items.villageLoot.Herbs;
import loot.items.villageLoot.Valuebles;

public class QuestTemplate implements Cloneable{

	public String name;
	public int Numbers;
	public int questType;
	public String description;
	public LootTableTemplate reward;
	public int difficulty;
	public int rarity;
	public int MobID;
	public ItemTemplate item;
	public ItemTemplate[] itemGroup;
	
	public int currentKilledMobs = 0;
	public int currentCollectedItems = 0;

	public QuestTemplate(String name, int Numbers, String description, LootTableTemplate reward, int difficulty, int rarity, int MobID) {
		this.name = name;
		this.Numbers = Numbers;
		this.questType = 0;
		this.description = description;
		this.reward = reward;
		this.difficulty = difficulty;
		this.rarity = rarity;
		this.MobID = MobID;
	}

	public QuestTemplate(String name, int Numbers, String description, LootTableTemplate reward, int difficulty, int rarity, ItemTemplate item) {
		this.name = name;
		this.Numbers = Numbers;
		this.questType = 1;
		this.description = description;
		this.reward = reward;
		this.difficulty = difficulty;
		this.rarity = rarity;
		this.item = item;
	}

	public QuestTemplate(String name, int Numbers, String description, LootTableTemplate reward, int difficulty, int rarity, ItemTemplate[] itemGroup) {
		this.name = name;
		this.Numbers = Numbers;
		this.questType = 2;
		this.description = description;
		this.reward = reward;
		this.difficulty = difficulty;
		this.rarity = rarity;
		this.itemGroup = itemGroup;
	}


	@Override
	public QuestTemplate clone() {
		try {
			QuestTemplate questTemplate;
			questTemplate = (QuestTemplate) clone();
			return questTemplate;
		} catch (Exception e) {
			Logger.logError("Error cloning Quest: ", e);
		}
		return null;
	}

	public boolean isCompleted() {
		boolean isCompleted = false;

		switch (questType) {
		case 0:{
			
			

		}break;
		case 1:{

		}break;
		case 2:{

		}break;

		default:
			Logger.logError("invalid QuestType: "+ questType, new IllegalArgumentException());
		}



		return isCompleted;
	}
	
	public boolean isRequiredItem(ItemTemplate item) {
		boolean isItem = false;
		if(questType == 1) {
			if(item.getClass() == this.item.getClass()) {
				if(item instanceof Food || item instanceof Herbs || item instanceof Valuebles) {
					if(this.item.variant == item.variant) {
						isItem = true;
					}
				}else {
					isItem = true;
				}
			}
		}else if(questType == 2){
			for (int i = 0; i < itemGroup.length; i++) {
				if(item.getClass() == this.item.getClass()) {
					if(item instanceof Food || item instanceof Herbs || item instanceof Valuebles) {
						if(this.item.variant == item.variant) {
							isItem = true;
						}
					}else {
						isItem = true;
					}
				}
			}
		}
		
		
		return isItem;
	}


}
