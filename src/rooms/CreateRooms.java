package rooms;

import java.awt.Color;

import game.GameLogic;
import gameObject.CreateObjects;
import gui.Draw;

public class CreateRooms {
	public static GameLogic spielLogic;

	public static void createRoom(int number) {
		switch (number) {
		case 0: Nr0(); break;
		case 1: Nr1(); break;
		case 2: Nr2(); break;








		default:
			throw new IllegalArgumentException("Unexpected value: " + number);
		}
	}

	private static void Nr0() {
		//Farben festsetzen
		Draw.backgroundColor = Color.black;
		Draw.columsColor = Color.gray;
		Draw.floorColor = Color.white;
		Draw.playerColor = Color.blue;
		spielLogic.resetPos = new int[] {50,701};
		//zusätzliche Objekte erstellen/löschen
		Draw.clearObjects();
		for(int i = 0;i<10;i++) {
			CreateObjects.createColumn(50, 750, Color.gray,Color.lightGray ,(1150/10)*i+50, 0);
		}

	}

	private static void Nr1() {
		//Farben festsetzen
		Draw.backgroundColor = Color.black;
		Draw.floorColor = Color.red;
		Draw.playerColor = Color.cyan;
		Draw.collisionRectanglesColor = Color.white;
		Draw.deathRechteckColor = Color.red;
		spielLogic.resetPos = new int[] {50,701};
		spielLogic.resetPos1 = new int[] {1150,651};
		//zusätzliche Objekte erstellen/löschen
		Draw.clearObjects();
		CreateObjects.createCollisionRechteck(50, 50, 100, 700);	//höhe brereite posX posY
		CreateObjects.createCollisionRechteck(100, 50, 150, 650);
		CreateObjects.createCollisionRechteck(50, 50, 200, 650);
		CreateObjects.createCollisionRechteck(50, 500, 200, 600);
		CreateObjects.createDeathReckteck(50, 50, 700, 600);
		CreateObjects.createDeathReckteck(50, 350, 250, 470);
		CreateObjects.createDeathReckteck(50, 250, 850, 470);
		CreateObjects.createCollisionRechteck(50, 200, 800, 600);
		CreateObjects.createCollisionRechteck(100, 50, 1000, 600);
		CreateObjects.createCollisionRechteck(100, 50, 1050, 650);
		CreateObjects.createCollisionRechteck(50, 50, 1100, 700);

	}

	private static void Nr2() {
		//Farben festsetzen
		Draw.backgroundColor = Color.cyan;
		Draw.floorColor = Color.green;
		Draw.playerColor = Color.black;
		Draw.collisionRectanglesColor = Color.orange;
		Draw.deathRechteckColor = Color.pink;
		spielLogic.resetPos = new int[] {50,701};
		//zusätzliche Objekte erstellen/löschen
		Draw.clearObjects();
		CreateObjects.createTestMob(30, 30, 1000, 720, 0, 1, 1000, 720, 2, 5); 	//höhe breite posX posY Dx Speed SpawnX SpawnY damage hp
		CreateObjects.createCollisionRechteck(50, 10, 200, 700);
		CreateObjects.createCollisionRechteck(50, 10, 1100, 700);
		//GameLogic.createDeathReckteck(50, 50, 1100, 700);
		
		


	}

	public static void setSpielLogic(GameLogic spielLogic) {
		CreateRooms.spielLogic = spielLogic;
	}
}
