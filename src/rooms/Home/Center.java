package rooms.Home;

import game.GameLogic;
import rooms.RoomTemplate;

public class Center extends RoomTemplate{

	public Center(String name) {
		super(name);
	}
	
	@Override
	public void createObjects() {
		super.createObjects();
		GameLogic.createShopOpen(300, 700);
	}
	

}
