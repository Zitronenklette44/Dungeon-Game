package rooms.Dungeon;

import gameObject.CreateObjects;
import rooms.RoomTemplate;

public class DungeonExit extends RoomTemplate{

	public DungeonExit(String name) {
		super(name);
		CreateObjects.createDungeonExit(1000, 700);
	}

}
