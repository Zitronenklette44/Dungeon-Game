package loot;

import java.util.ArrayList;


import action.Logger;
import questSystem.quests.QuestTemplate;

public class QuestTable {
	private QuestTemplate[] possibleQuests;
	private ArrayList<QuestTemplate> itemPool = new ArrayList<QuestTemplate>();
	public QuestTemplate lastGeneratedQuest;

	public QuestTable(QuestTemplate[] possibleItems ) {
		this.possibleQuests = possibleItems;
		initPool();
	}

	private void initPool() {
		for(int i = 0; i<possibleQuests.length; i++) {
			for(int x = 0; x<possibleQuests[i].rarity; x++) {
				QuestTemplate questTemplate = possibleQuests[i];
				itemPool.add(questTemplate);
			}
		}
	}

	public void generateLoot() {
		lastGeneratedQuest = null;

		if (itemPool.size() == 0) {
			Logger.logWarning("Quest pool is empty!");
		}

		QuestTemplate item = itemPool.get((int) (Math.random() * itemPool.size())).clone();
		lastGeneratedQuest = item;
	}
}