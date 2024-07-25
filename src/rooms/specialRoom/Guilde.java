package rooms.specialRoom;

import java.awt.Color;

import action.Logger;
import entitys.MobTemplate;
import game.CreateObjects;
import game.GameLogic;
import interactions.GildSwitchInteraction;
import rooms.RoomTemplate;
import translation.Translation;

public class Guilde extends RoomTemplate{

	public Guilde(String name) {
		super(name, "/resources/rooms/backgrounds/Gilde.png");
		createEntitys();
	}
	
	@Override
	public void setSpawns(int currentRoom) {
		super.setSpawns(currentRoom);
		GameLogic.resetPos[0] = 550;
		GameLogic.resetPos[1] = 725;
		GameLogic.resetPos1[0] = 550;
		GameLogic.resetPos1[1] = 725;
		GameLogic.vertikalAxis = true;
		GameLogic.player.hoehe = 25;
		GameLogic.player.breite = 25;
		GildSwitchInteraction.isInGuild = true;
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		CreateObjects.createGildSwitchInteraction(550, 750);
//		GameLogic.functionalObjects.add(CreateObjects.createPernamentChest(600, 400, true));
		CreateObjects.createDialogInteraction(581, 103, Translation.get("interaction.testDialog") , 2, Translation.get("dialogs.gildenSender"));
	}
	
	@Override
	protected void createFunctionable() {
		Logger.logInfo("new Functionable");
		super.createFunctionable();
		functional.add(CreateObjects.createChestPoint(600, 400, true, 100));
	}
	protected void createEntitys() {
		Entitys = new MobTemplate[1];

		Entitys[0] = CreateObjects.createTestMob(25, 25, 0.0F, 581, 103, 0, 1);
		Entitys[0].typeColor = Color.blue;
		Entitys[0].invulnerable = true;
		super.createEntitys();
	}
	public void spawnEntitys() {
		for(int i=0; i<1;i++) {
			if(Entitys[i] == null) {
				break;
			}
			if(!Entitys[i].defeated) {
				GameLogic.mobs.add(Entitys[i]);		//der Liste für bewegungen hinzufügen
				Entitys[i].posX = Entitys[i].SpawnX;	//Position zurücksetzen
				Entitys[i].posY = Entitys[i].SpawnY;
			}
		}
			}
			
}
