package rooms.specialRoom;

import entitys.GildSwitchInteraction;
import game.GameLogic;
import gameObject.CreateObjects;
import rooms.RoomTemplate;

public class Guilde extends RoomTemplate{

	public Guilde(String name) {
		super(name);
	}
	
	@Override
	public void setSpawns(int currentRoom) {
		super.setSpawns(currentRoom);
		GameLogic.resetPos[0] = 490;
		GameLogic.resetPos[1] = 700;
		GameLogic.resetPos1[0] = 490;
		GameLogic.resetPos1[1] = 700;
		GameLogic.vertikalAxis = true;
		GameLogic.player.hoehe = 25;
		GameLogic.player.breite = 25;
		GildSwitchInteraction.isInGuild = true;
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		CreateObjects.createGildSwitchInteraction(500, 700);
		
		
	}
	
}
