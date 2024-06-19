package rooms.Village;

import game.GameLogic;
import rooms.RoomTemplate;

public class Goal extends RoomTemplate{

	public Goal(String name) {
		super(name);
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		GameLogic.createDungeonExit(1000, 700);
		
		
		
	}
}
