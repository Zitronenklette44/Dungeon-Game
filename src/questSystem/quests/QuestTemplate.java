package questSystem.quests;

import action.Logger;
import loot.GenerateLoot;
import loot.LootTableTemplate;
import loot.items.ItemTemplate;
import loot.items.villageLoot.Food;
import loot.items.villageLoot.Herbs;
import loot.items.villageLoot.Valuebles;

public class QuestTemplate implements Cloneable{

	public String name;
	public String nameGroup;
	public int Numbers;
	public int questType;
	public String description;
	public LootTableTemplate reward;
	public char difficulty;
	public int rarity;
	public int MobID;
	public ItemTemplate item;
	public ItemTemplate[] itemGroup;
	public String specialRewardString;
	public boolean forceQuest = false;
	
	public int currentKilledMobs = 0;
	public int currentCollectedItems = 0;

	public QuestTemplate(String name, int Numbers, String description, LootTableTemplate reward, char difficulty, int rarity, int MobID) {
		this.name = name;
		this.Numbers = Numbers;
		this.questType = 0;
		this.description = description;
		this.reward = reward;
		this.difficulty = difficulty;
		this.rarity = rarity;
		this.MobID = MobID;
	}

	public QuestTemplate(String name, int Numbers, String description, LootTableTemplate reward, char difficulty, int rarity, ItemTemplate item) {
		this.name = name;
		this.Numbers = Numbers;
		this.questType = 1;
		this.description = description;
		this.reward = reward;
		this.difficulty = difficulty;
		this.rarity = rarity;
		this.item = item;
	}

	public QuestTemplate(String name, int Numbers, String description, LootTableTemplate reward, char difficulty, int rarity, ItemTemplate[] itemGroup, String groupName) {
		this.name = name;
		this.Numbers = Numbers;
		this.questType = 2;
		this.description = description;
		this.reward = reward;
		this.difficulty = difficulty;
		this.rarity = rarity;
		this.itemGroup = itemGroup;
		this.nameGroup = groupName;
	}
	
	public QuestTemplate(String name, int Numbers, String description, String reward, char difficulty, int rarity, int MobID) {
		this(name, Numbers, description,(LootTableTemplate) null, difficulty, rarity, MobID);
		this.specialRewardString = reward;
	}

	public QuestTemplate(String name, int Numbers, String description, String reward, char difficulty, int rarity, ItemTemplate item) {
		this(name, Numbers, description,(LootTableTemplate) null, difficulty, rarity, item);
		this.specialRewardString = reward;
	}

	public QuestTemplate(String name, int Numbers, String description, String reward, char difficulty, int rarity, ItemTemplate[] itemGroup, String groupName) {
		this(groupName, Numbers, description,(LootTableTemplate) null, difficulty, rarity, itemGroup, groupName);
		this.specialRewardString = reward;
	}
	
	


	@Override
	public QuestTemplate clone() {
		try {
			QuestTemplate questTemplate;
			questTemplate = (QuestTemplate) super.clone();
			return questTemplate;
		} catch (Exception e) {
			Logger.logError("Error cloning Quest: ", e);
		}
		return null;
	}

	public boolean isCompleted() {
        boolean isCompleted = false;

        switch (questType) {
        case 0: {
            if(currentKilledMobs == Numbers) {
                isCompleted = true;
            }
        }break;
        case 1: {
            if(currentCollectedItems == Numbers) {
                isCompleted = true;
            }
        }break;
        case 2: {
            if(currentCollectedItems == Numbers) {
                isCompleted = true;
            }
        }break;
        default:
            Logger.logError("invalid QuestType: " + questType, new IllegalArgumentException());
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

	public void getReward() {GenerateLoot.intoIntentory(reward);}
}
