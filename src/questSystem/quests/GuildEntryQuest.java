package questSystem.quests;

import game.GameLogic;

public class GuildEntryQuest extends QuestTemplate{

	public GuildEntryQuest() {
		super("Guild Entry Quest", 7, "asdasdasdasdasdasdasdasd", "rank up", '-', 1, 0);
		forceQuest = true;
	}
	
	@Override
	public void getReward() {
		GameLogic.player.rank = 'F';
		
	}
	
	
}
