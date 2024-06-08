package rooms;

import java.awt.Color;

import game.GameLogic;
import gui.Draw;

public class CreateRooms {

	public static void createRoom(int number) {
		switch (number) {
		case 0: Nr0(); break;
		case 1: Nr1(); break;








		default:
			throw new IllegalArgumentException("Unexpected value: " + number);
		}
	}

	private static void Nr0() {
		//Farben festsetzen
		Draw.backgroundColor = Color.black;
		Draw.columsColor = Color.gray;
		Draw.gameObjectsColor = Color.white;
		Draw.playerColor = Color.blue;
		//zusätzliche Objekte erstellen/löschen
		Draw.columns.clear();
		Draw.collisionRectangles.clear();
		for(int i = 0;i<10;i++) {
			GameLogic.createColumn(50, 750, Color.gray,Color.lightGray ,(1150/10)*i+50, 0);
		}

	}

	private static void Nr1() {
		//Farben festsetzen
		Draw.backgroundColor = Color.black;
		Draw.gameObjectsColor = Color.red;
		Draw.playerColor = Color.cyan;
		Draw.collisionRectanglesColor = Color.white;
		//zusätzliche Objekte erstellen/löschen
		Draw.columns.clear();
		Draw.collisionRectangles.clear();
		GameLogic.createCollisionRechteck(50, 50, 300, 700);
		
		
	}
}
