package entitys;

import game.GameLogic;
import gui.GameScreen;
import rooms.CreateDungeon;

public class DungeonExit extends InteractableTemplate{

	public DungeonExit(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, "Dungeon verlassen");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void performAction() {
		super.performAction();
		GameLogic.dungeon.currentRoom = 0;
		GameScreen.updateRoomNr(0);
		CreateDungeon.dungeonType = 0;
		CreateDungeon.homeVillageBuild = true;
		
		
		
	}

}
