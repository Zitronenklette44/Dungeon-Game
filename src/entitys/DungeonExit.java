package entitys;

import game.GameLogic;
import gui.GameScreen;
import rooms.DungeonCore;

public class DungeonExit extends InteractableTemplate{

	public DungeonExit(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, "Dungeon verlassen");
	}
	
	@Override
	public void performAction() {
		super.performAction();
		DungeonCore.currentRoom = 0;
		GameScreen.updateRoomNr(0);
		GameLogic.vertikalAxis=false;
		GameScreen.updateRoomNr(1);
		DungeonCore.dungeonType = 0;
		DungeonCore.homeVillageBuild = true;
		GameLogic.player.breite = 50;
		GameLogic.player.hoehe = 50;
		
		
	}

}
