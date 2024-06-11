package rooms.Home;

import java.awt.Color;

import game.GameLogic;
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
	public void createObjects() {
		// TODO Auto-generated method stub
		super.createObjects();
		GameLogic.createShopPotionsOpen(300, 700);
	}

}
