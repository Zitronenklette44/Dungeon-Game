package rooms.Castle;

import gameObject.CreateObjects;
import rooms.RoomTemplate;

public class CastleExit extends RoomTemplate{

	public CastleExit(String name) {
		super(name);
		CreateObjects.createDungeonExit(1000, 700);
	}

}
