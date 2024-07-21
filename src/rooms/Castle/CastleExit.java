package rooms.Castle;

import game.CreateObjects;
import rooms.RoomTemplate;

public class CastleExit extends RoomTemplate{

	public CastleExit(String name) {
		super(name);
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		CreateObjects.createDungeonExit(1000, 700);
	}

}
