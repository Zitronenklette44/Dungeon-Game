package rooms.Home;

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
	

}
