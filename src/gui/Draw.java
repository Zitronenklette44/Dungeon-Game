package gui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;

import entitys.Bullet;
import entitys.InteractableTemplate;
import entitys.TestMob;
import game.Collisions;
import game.GameLogic;
import gameObject.CollisionRechteck;
import gameObject.Column;
import gameObject.DeathRechteck;
import gameObject.Rechteck;

public class Draw extends JLabel {
	private int screenwidth;
	private int screenheight;
	public static Rechteck player;
	static GameLogic spieLogic;
	public static ArrayList<Rechteck> floor;
	public static ArrayList<Column> columns;
	public static ArrayList<CollisionRechteck> collisionRectangles;
	public static ArrayList<DeathRechteck> deathRechtecks;
	public static ArrayList<TestMob> mobs;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<InteractableTemplate> interactables;

	public static Color backgroundColor = Color.DARK_GRAY;
	public static Color floorColor = Color.white;
	public static Color columsColor = Color.gray;
	public static Color playerColor = Color.white;
	public static Color collisionRectanglesColor = Color.white;
	public static Color deathRechteckColor = Color.red;
	public static Color mobsColor = Color.blue;
	public static Color bulletColor = Color.magenta;
	private int oldRoom = -1;
	private int oldDungeonType=-1;


	@SuppressWarnings("static-access")
	public Draw(int screenBreite, int screenHoehe, GameLogic spiellogik) {
		this.screenwidth =screenBreite;
		this.screenheight = screenHoehe;
		floor = spiellogik.floorObject;
		columns = spiellogik.columns;
		player= spiellogik.player;
		collisionRectangles = spiellogik.collisionRectangles;
		deathRechtecks = spiellogik.deathRechteck;
		mobs = spiellogik.mobs;
		bullets = spiellogik.bullets;
		this.spieLogic = spiellogik;
		interactables = spiellogik.interactables;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(GameLogic.paused) {
			return;
		}

		//versteckte Interactionen
		g.setColor(Color.green);
		for (int i = 0; i < interactables.size(); i++) {
			InteractableTemplate aktuellesObjekt = interactables.get(i);
			g.fillRect(aktuellesObjekt.posX, aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
		}


		//zeiche Hintergrund
		g.setColor(backgroundColor);
		g.fillRect(0, 0, screenwidth, screenheight);

		//zeiche Raum
		g.setColor(Color.white);
		spieLogic.dungeon.drawRoom(g2d);
		oldDungeonType = GameLogic.dungeon.dungeonType;
		oldRoom = GameLogic.dungeon.currentRoom;


		//Zeichne Objekte
		g.setColor(floorColor);
		for (int i = 0; i < floor.size(); i++) {
			if(GameLogic.dungeon.dungeonType == 0) {
				Rechteck aktuellesObjekt = floor.get(i);
				g.fillRect(aktuellesObjekt.posX, aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
			}
		}

		g.setColor(columsColor);
		for (int i = 0; i < columns.size(); i++) {
			Column aktuellesObjekt = columns.get(i);
			aktuellesObjekt.draw(g);
		}

		g.setColor(collisionRectanglesColor);
		for (int i = 0; i < collisionRectangles.size(); i++) {
			CollisionRechteck aktuellesObjekt = collisionRectangles.get(i);
			if(aktuellesObjekt.isVisible) {
				g.fillRect(aktuellesObjekt.posX, aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
			}else if(GameLogic.debug){
				g.setColor(Color.red);
				g2d.drawString(aktuellesObjekt.breite+"", aktuellesObjekt.posX+(aktuellesObjekt.breite/2), aktuellesObjekt.posY-5);
				g2d.drawString(aktuellesObjekt.hoehe+"", aktuellesObjekt.posX-40, aktuellesObjekt.posY+(aktuellesObjekt.hoehe/2));
				g.drawRect(aktuellesObjekt.posX, aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
			}
		}

		g.setColor(deathRechteckColor);
		for (int i = 0; i < deathRechtecks.size(); i++) {
			DeathRechteck aktuellesObjekt = deathRechtecks.get(i);
			g.fillRect(aktuellesObjekt.posX, aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
		}

		g.setColor(mobsColor);
		for (int i = 0; i < mobs.size(); i++) {
			TestMob aktuellesObjekt = mobs.get(i);
			g.fillRect(aktuellesObjekt.posX, aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
		}

		g.setColor(bulletColor);
		for (int i = 0; i < bullets.size(); i++) {
			Bullet aktuellesObjekt = bullets.get(i);
			aktuellesObjekt.rotateToPlayerAndUpdate(player);
			aktuellesObjekt.draw(g2d);
		}


		g.setColor(playerColor);
		g.fillRect(player.posX, player.posY, player.breite, player.hoehe);
		if(GameLogic.debug) {
			g2d.drawString(player.posX+"", player.posX, player.posY-5);
			g2d.drawString(player.posY+"", player.posX-40, player.posY+(player.hoehe/2));
		}


		Collisions.checkInteractable(g2d, Color.white);
		repaint();
	}


	public static void clearObjects() {
		Draw.collisionRectangles.clear();
		Draw.deathRechtecks.clear();
		Draw.columns.clear();
		Draw.mobs.clear();
		Draw.interactables.clear();
	}

	public static void resetColor() {
		backgroundColor = Color.DARK_GRAY;
		floorColor = Color.white;
		columsColor = Color.gray;
		playerColor = Color.white;
		collisionRectanglesColor = Color.white;
		deathRechteckColor = Color.red;
		mobsColor = Color.blue;
		bulletColor = Color.magenta;
	}
}
