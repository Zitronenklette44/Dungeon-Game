package questSystem;

import java.util.ArrayList;

import action.Logger;
import game.GameLogic;
import loot.LootTabels;
import questSystem.quests.QuestTemplate;

public class QuestManager {
	
	private static ArrayList<QuestTemplate> quests = new ArrayList<QuestTemplate>();
	
	public static void init(){
		
		quests.add(new QuestTemplate("FieldMobs", 7, "Das ist eine Lustige Quest", LootTabels.createValuebles(2), 'S', 1, 1));		//Test
		
		
	}
	
	public static void setNewQuest(int questId) {
		Logger.logInfo("set New Quest");
		GameLogic.choosenQuest = quests.get(questId).clone();
	}
	
	public static void approveQuest() {
		Logger.logInfo("approved New Quest");
		GameLogic.currentQuest = GameLogic.choosenQuest;
		GameLogic.choosenQuest = null;
	}
	
	public static QuestTemplate get(int quest){
		return quests.get(quest);
	}
	
	

}
