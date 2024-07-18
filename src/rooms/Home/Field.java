package rooms.Home;

import java.awt.Color;

import action.Logger;
import entitys.MobTemplate;
import game.GameLogic;
import gameObject.CreateObjects;
import rooms.RoomTemplate;

public class Field extends RoomTemplate{

	public Field(String name) {
		super(name, "/resources/rooms/backgrounds/Feld.png");
		createEntitys();
	}
	private int wave  = 1;
	


	@Override
	public void setSpawns(int currentRoom) {
		super.setSpawns(currentRoom);
		GameLogic.resetPos[0] = 50;
		GameLogic.resetPos[1] = 700;
		GameLogic.resetPos1[0] = 1100;
		GameLogic.resetPos1[1] = 700;
		GameLogic.vertikalAxis = true;
	}

	@Override
	protected void createEntitys() {
		Entitys = new MobTemplate[7];

		Entitys[0] = CreateObjects.createTestMob(25, 25, 0.5F, 1035, 300, 1, 1);
		Entitys[0].typeColor = Color.blue;
		Entitys[0].maxHitCooldown = 200;

		Entitys[1] = CreateObjects.createTestMob(25, 25, 0.7F, 1035, 400, 1, 1);
		Entitys[1].typeColor = Color.white;
		Entitys[1].maxHitCooldown = 200;

		Entitys[2] = CreateObjects.createTestMob(25, 25, 0.5F, 1035, 300, 1, 1);
		Entitys[2].typeColor = Color.blue;
		Entitys[2].maxHitCooldown = 200;

		Entitys[3] = CreateObjects.createTestMob(25, 25, 0.7F, 1035, 400, 1, 1);
		Entitys[3].typeColor = Color.white;
		Entitys[3].maxHitCooldown = 200;

		Entitys[4] = CreateObjects.createTestMob(25, 25, 0.5F, 1035, 300, 1, 1);
		Entitys[4].typeColor = Color.blue;
		Entitys[4].maxHitCooldown = 200;

		Entitys[5] = CreateObjects.createTestMob(25, 25, 0.7F, 1035, 400, 1, 1);
		Entitys[5].typeColor = Color.white;
		Entitys[5].maxHitCooldown = 200;

		Entitys[6] = CreateObjects.createTestMob(30, 30, 0.3F, 1035, 500, 1, 90);
		Entitys[6].typeColor = Color.white;
		Entitys[6].maxHitCooldown = 200;
		super.createEntitys();
	}

	public void spawnEntitys() {;
	super.spawnEntitys();	//Liste wird geleert
	switch (wave) {
	case 1: {
		for(int i=0; i<2;i++) {
			if(Entitys[i] == null) {
				break;
			}
			if(!Entitys[i].defeated) {
				GameLogic.mobs.add(Entitys[i]);		//der Liste für bewegungen hinzufügen
				Entitys[i].posX = Entitys[i].SpawnX;	//Position zurücksetzen
				Entitys[i].posY = Entitys[i].SpawnY;
			}
		}

	}break;
	case 2: {
		for(int i=2; i<4;i++) {
			if(Entitys[i] == null) {
				break;
			}
			if(!Entitys[i].defeated) {
				GameLogic.mobs.add(Entitys[i]);		//der Liste für bewegungen hinzufügen
				Entitys[i].posX = Entitys[i].SpawnX;	//Position zurücksetzen
				Entitys[i].posY = Entitys[i].SpawnY;
			}
		}

	}break;
	case 3: {
		for(int i=4; i<7;i++) {
			if(Entitys[i] == null) {
				break;
			}
			if(!Entitys[i].defeated) {
				GameLogic.mobs.add(Entitys[i]);		//der Liste für bewegungen hinzufügen
				Entitys[i].posX = Entitys[i].SpawnX;	//Position zurücksetzen
				Entitys[i].posY = Entitys[i].SpawnY;
			}
		}

	}break;

	default:
		Logger.logError("Unexpected value: "+ wave, new IllegalArgumentException());
	}


	}
	private boolean NextWave() {
		 boolean next = false;

		switch (wave) {
		case 1: {
			if(Entitys[0].defeated && Entitys[1].defeated) {
				next = true;
			}

		}break;
		case 2: {
			if(Entitys[2].defeated && Entitys[3].defeated) {
				next = true;
			}

		}break;
		case 3: {
			if(Entitys[4].defeated && Entitys[5].defeated && Entitys[6].defeated) {
			}

		}break;
		default:
			Logger.logError("Unexpected value: "+ wave, new IllegalArgumentException());
		}
	
		return next;
	}

	@Override
	public void update() {
		NextWave();
		if(NextWave()) {
			wave++;
			spawnEntitys();
			
		}
		super.update();
	}
	
	@Override
	public void createObjects(int currentRoom) {
		super.createObjects(currentRoom);
		GameLogic.FloorObject.isVisible=false;
	}

}
