package rooms.Forest;

import game.GameLogic;
import rooms.RoomTemplate;

public class ForestGoal extends RoomTemplate{

	public ForestGoal(String name) {
		super(name);
		GameLogic.createDungeonExit(1000, 700);
	}

}
