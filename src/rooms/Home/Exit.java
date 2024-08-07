package rooms.Home;

import game.CreateObjects;
import game.GameLogic;
import interactions.GildSwitchInteraction;
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
		GameLogic.player.hoehe = 50;
		GameLogic.player.breite = 50;
		GameLogic.vertikalAxis = false;
		GildSwitchInteraction.isInGuild = false;
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		GameLogic.FloorObject.isVisible=true;
		CreateObjects.createGildSwitchInteraction(500, 700);
	}
}
