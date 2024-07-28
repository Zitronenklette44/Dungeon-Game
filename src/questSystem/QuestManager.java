package questSystem;

import java.util.ArrayList;

import action.Logger;
import game.GameLogic;
import loot.LootTabels;
import loot.items.CreateItem;
import questSystem.quests.GuildEntryQuest;
import questSystem.quests.QuestTemplate;

public class QuestManager {
	
	private static ArrayList<QuestTemplate> quests = new ArrayList<QuestTemplate>();
	
	public static void init(){
		
		quests.add(new GuildEntryQuest());
		
		quests.add(new QuestTemplate("FieldMobs", 7, "Das ist eine Lustige Quest die eine extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem lange beschreibung hat", LootTabels.createValuebles(2), 'F', 1, CreateItem.createCopperCoin(0)));		//Test
		
		
	}
	
	public static void setNewQuest(int questId) {
		Logger.logInfo("set New Quest");
		GameLogic.choosenQuest = quests.get(questId).clone();
	}
	
	public static void setNewQuest(QuestTemplate quest) {
		Logger.logInfo("set New Quest");
		GameLogic.choosenQuest = quest.clone();
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
