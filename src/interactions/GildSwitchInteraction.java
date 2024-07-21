package interactions;

import action.Logger;
import game.GameLogic;
import gui.GameScreen;
import rooms.DungeonCore;
import translation.Translation;

public class GildSwitchInteraction extends InteractableTemplate{
	public static boolean isInGuild= false;
	
	public GildSwitchInteraction(int posX, int posY) {
		super(10, 10, posX, posY, 100, Translation.get("interaction.guildEnter"));
	}
	
	
	@Override
	public void performAction() {
		super.performAction();
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
			GameScreen.updateRoomNr(true);
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
			GameScreen.paintRoomNum = false;
			GameScreen.updateRoomNr(DungeonCore.currentRoom);
			
		}
		Logger.logInfo(interactionString);
	}
	
	@Override
	public void update() {
		super.update();
		if(!isInGuild) {
			interactionString = Translation.get("interaction.guildEnter");
			range = 100;
		}else {
			interactionString = Translation.get("interaction.guildExit");
			range = 25;
		}
	}
	
}
