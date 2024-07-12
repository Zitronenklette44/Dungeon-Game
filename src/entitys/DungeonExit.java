package entitys;

import game.GameLogic;
import gui.GameScreen;
import rooms.DungeonCore;
import translation.Translation;

public class DungeonExit extends InteractableTemplate{

	public DungeonExit(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, Translation.get("interaction.dungeonExit"));
	}
	
	@Override
	public void performAction() {
		super.performAction();
		DungeonCore.thisRooms.clear();
		DungeonCore.init();
		DungeonCore.currentRoom = 0;
		GameScreen.updateRoomNr(0);
		GameLogic.vertikalAxis=false;
		GameScreen.updateRoomNr(1);
		DungeonCore.dungeonType = 0;
		DungeonCore.homeVillageBuild = true;
		GameLogic.player.breite = 50;
		GameLogic.player.hoehe = 50;
		GameLogic.resetLevel();
		GameLogic.player.Hp = GameLogic.player.maxHp;
		
		
	}

}
