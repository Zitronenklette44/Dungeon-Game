package rooms.specialRoom;

import action.Logger;
import game.CreateObjects;
import game.GameLogic;
import interactions.GildSwitchInteraction;
import rooms.RoomTemplate;

public class Guilde extends RoomTemplate{

	public Guilde(String name) {
		super(name, "/resources/rooms/backgrounds/Gilde.png");
	}
	
	@Override
	public void setSpawns(int currentRoom) {
		super.setSpawns(currentRoom);
		GameLogic.resetPos[0] = 550;
		GameLogic.resetPos[1] = 725;
		GameLogic.resetPos1[0] = 550;
		GameLogic.resetPos1[1] = 725;
		GameLogic.vertikalAxis = true;
		GameLogic.player.hoehe = 25;
		GameLogic.player.breite = 25;
		GildSwitchInteraction.isInGuild = true;
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		CreateObjects.createGildSwitchInteraction(550, 750);
//		GameLogic.functionalObjects.add(CreateObjects.createPernamentChest(600, 400, true));
		for (int i = 0; i < functional.size(); i++) {
			GameLogic.functionalObjects.add(functional.get(i));
		}
		
	}
	
	@Override
	protected void createFunctionable() {
		super.createFunctionable();
		functional.add(CreateObjects.createChestPoint(600, 400, true, 100));
	}
	
}
