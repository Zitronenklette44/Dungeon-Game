package rooms.Home;

import game.GameLogic;
import gameObject.CreateObjects;
import rooms.RoomTemplate;

public class Center extends RoomTemplate{

	public Center(String name) {
		super(name);
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		CreateObjects.createShopToolsOpen(300, 700);   
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
