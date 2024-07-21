package rooms.Forest;

import game.CreateObjects;
import rooms.RoomTemplate;

public class ForestGoal extends RoomTemplate{

	public ForestGoal(String name) {
		super(name);
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		CreateObjects.createDungeonExit(1000, 700);
	}
}
