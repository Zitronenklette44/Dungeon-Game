package questSystem.quests;

import action.Logger;

public class DefeatFirstBoss extends QuestTemplate{

	public DefeatFirstBoss() {
		super("Free the Village", 1, "Am other boss to kill", "the village will love you", 'E', 1, 1);
		forceQuest = true;
	}
	
	
	@Override
	public void getReward() {
		Logger.logInfo("killed Boss and completed Quest");
	}
	
}
