package questSystem;

import java.util.ArrayList;

import game.GameLogic;
import loot.LootTabels;
import questSystem.quests.QuestTemplate;

public class QuestManager {
	
	private static ArrayList<QuestTemplate> quests = new ArrayList<QuestTemplate>();
	
	public static void init(){
		
		quests.add(new QuestTemplate("test", 7, "Das ist eine Lustige Quest", LootTabels.createValuebles(2), 0, 1, 1));		//Test
		
		
	}
	
	
	public static void setNewQuest(int questId) {
		GameLogic.currentQuest = quests.get(questId).clone();
	}
	
	

}
