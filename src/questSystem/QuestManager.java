package questSystem;

import java.util.ArrayList;

import action.Logger;
import game.GameLogic;
import loot.LootTabels;
import loot.items.CreateItem;
import loot.items.ItemTemplate;
import questSystem.quests.DefeatFirstBoss;
import questSystem.quests.GuildEntryQuest;
import questSystem.quests.QuestTemplate;

public class QuestManager {

	private static ArrayList<QuestTemplate> quests = new ArrayList<QuestTemplate>();
	public static final int LAST_SLOT_FORCE_QUESTS = 1;

	public static void init(){

		quests.add(new GuildEntryQuest());
		
		quests.add(new DefeatFirstBoss());

		quests.add(new QuestTemplate("FieldMobs", 7, "Das ist eine Lustige Quest die eine extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem extrem lange beschreibung hat", LootTabels.createValuebles(2), 'F', 1, CreateItem.createCopperCoin(0)));		//Test
		
		quests.add(new QuestTemplate("NichtFieldMobs", 3, "Das ist keine Lustige Quest mit keiner langen beschreibung", LootTabels.createValuebles(2), 'E', 1, 1));		//Test
		
		quests.add(new QuestTemplate("Etwas komplett anderes", 1, "Braucht es umbedingt ein Beschriebung?", LootTabels.createVillagerLoot(10), 'A', 1, new ItemTemplate[] {CreateItem.createCopperCoin(0),CreateItem.createSilverCoin(0),CreateItem.createGoldCoin(0)}, "Coins"));		//Test
		
		quests.add(new QuestTemplate("Das selbe nocheinmal", 15, "Alle meine Entchen schwimmen auf dem See, schwimmen auf dem See...", LootTabels.createValuebles(200), 'S', 1, CreateItem.createCopperCoin(0)));		//Test
		
		
		GameLogic.currentQuest.add(quests.get(0));
		GameLogic.currentQuest.add(quests.get(1));
		GameLogic.currentQuest.add(quests.get(2));
		GameLogic.currentQuest.add(quests.get(3));
		GameLogic.currentQuest.add(quests.get(4));
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
		if(GameLogic.current_Accapted_Quests >= GameLogic.MAX_ACCAPTABLE_QUESTS && !GameLogic.choosenQuest.forceQuest) return;
		Logger.logInfo("approved New Quest");
		GameLogic.currentQuestDone.set(GameLogic.currentQuest.size(), true);
		GameLogic.currentQuest.add(GameLogic.choosenQuest);
		if(!GameLogic.choosenQuest.forceQuest) GameLogic.current_Accapted_Quests++;
		GameLogic.choosenQuest = null;
	}

	public static QuestTemplate get(int quest){
		return quests.get(quest);
	}



}
