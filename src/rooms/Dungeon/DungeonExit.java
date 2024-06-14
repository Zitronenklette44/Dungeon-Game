package rooms.Dungeon;

import game.GameLogic;
import rooms.RoomTemplate;

public class DungeonExit extends RoomTemplate{

	public DungeonExit(String name) {
		super(name);
		GameLogic.createDungeonExit(1000, 700);
	}

}
