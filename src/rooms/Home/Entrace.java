package rooms.Home;

import java.awt.Color;

import game.GameLogic;
import gameObject.CreateObjects;
import gui.Draw;
import rooms.RoomTemplate;

public class Entrace extends RoomTemplate{

	public Entrace(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void changeColors() {
		super.changeColors();
		Draw.backgroundColor= Color.black;
	}
	@Override
	public void createObjects(int currentRoom) {
		// TODO Auto-generated method stub
		super.createObjects(currentRoom);
		CreateObjects.createShopPotionsOpen(300, 700);
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
