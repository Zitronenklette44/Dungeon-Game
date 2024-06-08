package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import gameObject.Column;
import gameObject.Rechteck;
import gui.TestScreen;

public class GameLogic {

	public boolean moveLeft = false;
	public boolean moveRight = false;
	public boolean jump = false;
	public static ArrayList<Rechteck> spielObjekte;
	public static ArrayList<Column> columns;
	public static Rechteck player;
	public static Rechteck FloorObject;
	public static int screenHoehe;
	public static int screenBreite;
	public static int floor = 700;
	public static int jumpHight = 100;

	public GameLogic() {
		Timer gameTimer = new Timer();
		spielObjekte = new ArrayList<Rechteck>();
		columns = new ArrayList<Column>();
		screenBreite =TestScreen.getScreenBreite();
		screenHoehe = TestScreen.getScreenHoehe();
		
		createObjekts();








		gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				if(moveLeft) {
					player.posX--;
				}
				if(moveRight) {
					player.posX++;
				}

				if(player.posY>=floor-jumpHight && jump) {
					player.posY--;
				}else if(player.posY<floor && !jump){
					player.posY++;
				}
				
				if(player.posY <= floor-jumpHight) {
					jump = false;
				}




			}
		}, 0, 5);
	}

	private static void createObjekts() {
		player = new Rechteck(50, 50, screenBreite/2, floor);
		
		FloorObject = new Rechteck(50, screenBreite, 0, screenHoehe-50);
		spielObjekte.add(FloorObject);
	}
	
	public static void createColumn(int breite, int hoehe , Color farbe, int posX, int posY) {
		columns.add(new Column(breite, hoehe, farbe, posX, posY));
	}
}
