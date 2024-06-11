package rooms.Home;

import java.awt.Color;

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

}
