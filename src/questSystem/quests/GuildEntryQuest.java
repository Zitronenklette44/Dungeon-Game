package questSystem.quests;

import game.GameLogic;

public class GuildEntryQuest extends QuestTemplate{

	public GuildEntryQuest() {
		super("Guild Entry Quest", 7, "asdasdasdasdasdasdasdasd", "rank up", 'X', 1, 0);
	}
	
	@Override
	public void getReward() {
		GameLogic.player.rank = 'F';
		
	}
	
	
}
