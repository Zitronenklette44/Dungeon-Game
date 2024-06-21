package rooms.Dungeon;

import java.awt.Color;

import gui.Draw;
import rooms.RoomTemplate;

public class DungeonEntrance extends RoomTemplate{

	public DungeonEntrance(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void changeColors() {
		Draw.backgroundColor = Color.gray;
		super.changeColors();
	}
}
