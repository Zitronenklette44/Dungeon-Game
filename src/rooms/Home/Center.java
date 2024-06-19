package rooms.Home;

import game.GameLogic;
import rooms.RoomTemplate;

public class Center extends RoomTemplate{

	public Center(String name) {
		super(name);
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		GameLogic.createShopToolsOpen(300, 700);   
	}
	

}
