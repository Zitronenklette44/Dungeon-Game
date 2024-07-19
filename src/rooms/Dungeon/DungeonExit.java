package rooms.Dungeon;

import gameObject.CreateObjects;
import rooms.RoomTemplate;

public class DungeonExit extends RoomTemplate{

	public DungeonExit(String name) {
		super(name);
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		CreateObjects.createDungeonExit(1000, 700);
	}
}
