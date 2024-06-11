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
	public static ArrayList<Rechteck> spielObjekte;
	public static ArrayList<Column> columns;
	public static ArrayList<CollisionRechteck> collisionRectangles;
	public static ArrayList<DeathRechteck> deathRechtecks;
	public static ArrayList<TestMob> mobs;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<InteractableTemplate> interactables;
	
	public static Color backgroundColor = Color.DARK_GRAY;
	public static Color gameObjectsColor = Color.white;
	public static Color columsColor = Color.gray;
	public static Color playerColor = Color.white;
	public static Color collisionRectanglesColor = Color.white;
	public static Color deathRechteckColor = Color.red;
	public static Color mobsColor = Color.blue;
	public static Color bulletColor = Color.magenta;
	
	
	@SuppressWarnings("static-access")
	public Draw(int screenBreite, int screenHoehe, GameLogic spiellogik) {
		this.screenwidth =screenBreite;
		this.screenheight = screenHoehe;
		spielObjekte = spiellogik.spielObjekte;
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
		
		//Zeichne Objekte
		g.setColor(gameObjectsColor);
		for (int i = 0; i < spielObjekte.size(); i++) {
			Rechteck aktuellesObjekt = spielObjekte.get(i);
			g.fillRect(aktuellesObjekt.posX, aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
		}
		
		g.setColor(columsColor);
		for (int i = 0; i < columns.size(); i++) {
			Column aktuellesObjekt = columns.get(i);
			aktuellesObjekt.draw(g);
		}
		
		g.setColor(collisionRectanglesColor);
		for (int i = 0; i < collisionRectangles.size(); i++) {
			CollisionRechteck aktuellesObjekt = collisionRectangles.get(i);
			g.fillRect(aktuellesObjekt.posX, aktuellesObjekt.posY, aktuellesObjekt.breite, aktuellesObjekt.hoehe);
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
}
