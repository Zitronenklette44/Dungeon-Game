package entitys;

import action.Logger;
import game.GameLogic;
import gui.GameScreen;
import rooms.DungeonCore;

public class GildSwitchInteraction extends InteractableTemplate{
	public static boolean isInGuild= false;
	
	public GildSwitchInteraction(int posX, int posY) {
		super(10, 10, posX, posY, 100, "Gilde betreten");
	}
	
	
	@Override
	public void performAction() {
		super.performAction();
		Logger.logInfo("isInGilde: "+isInGuild);
		if(!isInGuild) {
			DungeonCore.homeVillageBuild = false;
			DungeonCore.specialRoomBuild = true;
			DungeonCore.thisRooms.clear();
			DungeonCore.currentRoom = 0;
			DungeonCore.dungeonType = 5;
			DungeonCore.init();
			GameScreen.updateRoomNr(0);
			GameLogic.vertikalAxis=true;
			GameScreen.updateRoomNr(1);
			GameLogic.resetLevel();
		}else {
			DungeonCore.homeVillageBuild = true;
			DungeonCore.specialRoomBuild = false;
			DungeonCore.thisRooms.clear();
			DungeonCore.currentRoom = 3;
			DungeonCore.dungeonType = 0;
			DungeonCore.init();
			GameScreen.updateRoomNr(0);
			GameLogic.vertikalAxis=false;
			GameScreen.updateRoomNr(4);
			GameLogic.resetLevel();
			GameLogic.player.posX = 490;
		}
	}
	
}
