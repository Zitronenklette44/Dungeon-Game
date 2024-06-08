package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gameObject.CollisionRechteck;
import gameObject.Column;
import gameObject.Rechteck;
import gui.TestScreen;

public class GameLogic {

	public boolean moveLeft = false;
	public boolean moveRight = false;
	public boolean jump = false;
	public static ArrayList<Rechteck> spielObjekte;
	public static ArrayList<Column> columns;
	public static ArrayList<CollisionRechteck> collisionRectangles;
	public static Rechteck player;
	public static Rechteck FloorObject;
	public static int screenHoehe;
	public static int screenBreite;
	public static int floor = 700;
	public static int jumpHight = 100;
	public static int playerSpeed = 2;
	private static int jumpStart;
	private static boolean jumpStartInitialized = false;
	private static boolean moveUp=false;
	private static boolean moveDown=false;

	public GameLogic() {
		Timer gameTimer = new Timer();
		spielObjekte = new ArrayList<Rechteck>();
		columns = new ArrayList<Column>();
		collisionRectangles = new ArrayList<>();
		screenBreite =TestScreen.getScreenBreite();
		screenHoehe = TestScreen.getScreenHoehe();

		createObjekts();








		gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				
				//bewegen Spieler
				if (moveLeft && !checkCollision(-2, 0)) {
					player.posX -= 2;
				}
				if (moveRight && !checkCollision(2, 0)) {
					player.posX += 2;
				}

				// Springen des Spielers
				if (jump) {
				    // Wenn der Sprung beginnt, setzen wir jumpStart auf die aktuelle Position des Spielers
				    if (!jumpStartInitialized) {
				        jumpStart = player.posY;
				        jumpStartInitialized = true;
				    }
				    
				    // Spieler springt nach oben
				    player.posY--;

				    // Überprüfe, ob der Sprung enden soll
				    if (player.posY <= jumpStart - jumpHight || player.posY == jumpStart) {
				        jump = false; // Sprung beenden
				        jumpStartInitialized = false; // Zurücksetzen für den nächsten Sprung
				    }
				} else {
				    // Spieler fällt, bis er mit einem Kollisionsrechteck kollidiert oder den Boden erreicht
				    if (!checkCollision(0, 1) && player.posY < floor) {
				        player.posY++;
				        System.out.println("Down we go");
				    }
				}

				// Überprüfe, ob der Spieler auf einem Kollisionsrechteck steht und nach oben oder unten bewegt werden kann
				if (!jump && !checkCollision(0, -1) && player.posY < floor && player.posY > 0) {
				    if (moveUp) {
				        player.posY--;
				    } else if (moveDown) {
				        player.posY++;
				    }
				}

				// Überprüfe, ob der Spieler von einem Hindernis abspringen kann
				if (!jump && !checkCollision(0, -1) && player.posY < floor && player.posY > 0) {
				    if (moveUp) {
				        jump = true; // Spieler springt nach oben
				    }
				}





				
				/*if (jump) {
				    // Wenn der Sprung beginnt, speichern wir die Startposition des Sprungs
				    if (player.posY == floor) {
				        jumpStart = player.posY;
				    }
				    
				    // Spieler springt nach oben
				    player.posY--;

				    // Überprüfe, ob der Sprung enden soll
				    if (player.posY <= jumpStart - jumpHight || checkCollision(0, -1)) {
				        jump = false; // Sprung beenden
				    }

				} else {
				    // Spieler fällt, bis er mit einem Kollisionsrechteck kollidiert oder den Boden erreicht
				    if (!checkCollision(0, 1) && player.posY < floor) {
				        player.posY++;
				        System.out.println("Down we go");
				    }
				}*/
				System.out.println("playerPos: "+player.posY+" playerJUmpStart: "+jumpStart+ " Variable Jump: "+jump+ " variable JumpIni:" +jumpStartInitialized);
			}
		}, 0, 5);
	}

	private static void createObjekts() {
		player = new Rechteck(50, 50, screenBreite/2, floor);

		FloorObject = new Rechteck(50, screenBreite, 0, screenHoehe-50);
		spielObjekte.add(FloorObject);
	}

	public static void createColumn(int breite, int hoehe , Color farbe,Color farbe2, int posX, int posY) {
		columns.add(new Column(breite, hoehe, posX, posY, farbe, farbe2));
	}

	public static void createCollisionRechteck(int hoehe,int breite,int posX, int posY) {
		collisionRectangles.add(new CollisionRechteck(hoehe, breite, posX, posY));
	}

	private boolean checkCollision(int deltaX, int deltaY) {
		// Berechne die zukünftige Position des Spielers
		int futurePosX = player.posX + deltaX;
		int futurePosY = player.posY + deltaY;

		// Überprüfe, ob eine Kollision auftreten würde
		for (CollisionRechteck collisionRect : collisionRectangles) {
			if (futurePosX < collisionRect.posX + collisionRect.breite &&
					futurePosX + player.breite > collisionRect.posX &&
					futurePosY < collisionRect.posY + collisionRect.hoehe &&
					futurePosY + player.hoehe > collisionRect.posY) {
				// Kollision gefunden
				return true;
			}
		}
		// Keine Kollision
		return false;
	}
}
