package rooms.Village;

import game.CreateObjects;
import rooms.RoomTemplate;

public class Goal extends RoomTemplate{

	public Goal(String name) {
		super(name);
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		CreateObjects.createDungeonExit(1000, 700);
		
		
		
	}
}
