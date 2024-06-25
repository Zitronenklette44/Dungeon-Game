package rooms.Home;

import game.GameLogic;
import rooms.RoomTemplate;

public class Exit extends RoomTemplate{

	public Exit(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void setSpawns(int currentRoom) {
		super.setSpawns(currentRoom);
		GameLogic.resetPos[0] = 50;
		GameLogic.resetPos[1] = 700;
		GameLogic.resetPos1[0] = 1100;
		GameLogic.resetPos1[1] = 700;
		
		
		
	}
}
