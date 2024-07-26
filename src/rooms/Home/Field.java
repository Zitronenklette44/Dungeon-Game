package rooms.Home;

import java.awt.Color;

import action.Logger;
import entitys.MobTemplate;
import game.CreateObjects;
import game.GameLogic;
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
		GameLogic.player.hoehe = 30;
		GameLogic.player.breite = 30;
		GameLogic.resetPos[0] = 50;
		GameLogic.resetPos[1] = 700;
		GameLogic.resetPos1[0] = 1100;
		GameLogic.resetPos1[1] = 700;
		GameLogic.vertikalAxis = true;
		GameLogic.resetControlls();
	}

	@Override
	protected void createEntitys() {
		Entitys = new MobTemplate[7];

		getEntitys()[0] = CreateObjects.createTestMob(25, 25, 0.5F, 1035, 300, 1, 1);
		getEntitys()[0].typeColor = Color.blue;
		getEntitys()[0].maxHitCooldown = 200;

		getEntitys()[1] = CreateObjects.createTestMob(25, 25, 0.7F, 1035, 400, 1, 1);
		getEntitys()[1].typeColor = Color.white;
		getEntitys()[1].maxHitCooldown = 200;

		getEntitys()[2] = CreateObjects.createTestMob(25, 25, 0.5F, 1035, 300, 1, 1);
		getEntitys()[2].typeColor = Color.blue;
		getEntitys()[2].maxHitCooldown = 200;

		getEntitys()[3] = CreateObjects.createTestMob(25, 25, 0.7F, 1035, 400, 1, 1);
		getEntitys()[3].typeColor = Color.white;
		getEntitys()[3].maxHitCooldown = 200;

		getEntitys()[4] = CreateObjects.createTestMob(25, 25, 0.5F, 1035, 300, 1, 1);
		getEntitys()[4].typeColor = Color.blue;
		getEntitys()[4].maxHitCooldown = 200;

		getEntitys()[5] = CreateObjects.createTestMob(25, 25, 0.7F, 1035, 400, 1, 1);
		getEntitys()[5].typeColor = Color.white;
		getEntitys()[5].maxHitCooldown = 200;

		getEntitys()[6] = CreateObjects.createTestMob(30, 30, 0.3F, 1035, 500, 1, 90);
		getEntitys()[6].typeColor = Color.white;
		getEntitys()[6].maxHitCooldown = 200;
		super.createEntitys();
	}

	public void spawnEntitys() {;
	super.spawnEntitys();	//Liste wird geleert
	switch (wave) {
	case 1: {
		for(int i=0; i<2;i++) {
			if(getEntitys()[i] == null) {
				break;
			}
			if(!getEntitys()[i].defeated) {
				GameLogic.mobs.add(getEntitys()[i]);		//der Liste für bewegungen hinzufügen
				getEntitys()[i].posX = getEntitys()[i].SpawnX;	//Position zurücksetzen
				getEntitys()[i].posY = getEntitys()[i].SpawnY;
			}
		}

	}break;
	case 2: {
		for(int i=2; i<4;i++) {
			if(getEntitys()[i] == null) {
				break;
			}
			if(!getEntitys()[i].defeated) {
				GameLogic.mobs.add(getEntitys()[i]);		//der Liste für bewegungen hinzufügen
				getEntitys()[i].posX = getEntitys()[i].SpawnX;	//Position zurücksetzen
				getEntitys()[i].posY = getEntitys()[i].SpawnY;
			}
		}

	}break;
	case 3: {
		for(int i=4; i<7;i++) {
			if(getEntitys()[i] == null) {
				break;
			}
			if(!getEntitys()[i].defeated) {
				GameLogic.mobs.add(getEntitys()[i]);		//der Liste für bewegungen hinzufügen
				getEntitys()[i].posX = getEntitys()[i].SpawnX;	//Position zurücksetzen
				getEntitys()[i].posY = getEntitys()[i].SpawnY;
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
			if(getEntitys()[0].defeated && getEntitys()[1].defeated) {
				next = true;
			}

		}break;
		case 2: {
			if(getEntitys()[2].defeated && getEntitys()[3].defeated) {
				next = true;
			}

		}break;
		case 3: {
			if(getEntitys()[4].defeated && getEntitys()[5].defeated && getEntitys()[6].defeated) {
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
