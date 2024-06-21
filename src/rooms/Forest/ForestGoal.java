package rooms.Forest;

import gameObject.CreateObjects;
import rooms.RoomTemplate;

public class ForestGoal extends RoomTemplate{

	public ForestGoal(String name) {
		super(name);
		CreateObjects.createDungeonExit(1000, 700);
	}

}
