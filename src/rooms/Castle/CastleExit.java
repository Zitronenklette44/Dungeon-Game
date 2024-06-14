package rooms.Castle;

import game.GameLogic;
import rooms.RoomTemplate;

public class CastleExit extends RoomTemplate{

	public CastleExit(String name) {
		super(name);
		GameLogic.createDungeonExit(1000, 700);
	}

}
