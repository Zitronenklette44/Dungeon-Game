package gameObject;

import java.awt.Color;

import entitys.DungeonChooser;
import entitys.DungeonExit;
import entitys.Player;
import entitys.ShopOpenPotions;
import entitys.ShopOpenTools;
import entitys.TestMob;
import game.GameLogic;

public class CreateObjects {

	public static void createObjekts() {
		GameLogic.player = new Player(50, 50, GameLogic.screenBreite/2, GameLogic.floor-2, 0, 0, GameLogic.playerSpeed, 0, 0, 3, 20);
	
		GameLogic.FloorObject = new Rechteck(50, GameLogic.screenBreite, 0, GameLogic.screenHoehe-50);
		GameLogic.floorObject.add(GameLogic.FloorObject);
	}

	public static void createColumn(int breite, int hoehe , Color farbe,Color farbe2, int posX, int posY) {
		GameLogic.columns.add(new Column(breite, hoehe, posX, posY, farbe, farbe2));
	}

	public static void createCollisionRechteck(int hoehe,int breite,int posX, int posY) {
		GameLogic.collisionRectangles.add(new CollisionRechteck(hoehe, breite, posX, posY, true));
	}

	public static void createHitbox(int hoehe,int breite,int posX, int posY) {
		GameLogic.collisionRectangles.add(new CollisionRechteck(hoehe, breite, posX, posY, false));
	}

	public static void createDeathReckteck(int hoehe,int breite,int posX, int posY) {
		GameLogic.deathRechteck.add(new DeathRechteck(hoehe, breite, posX, posY));
	}

	public static void createTestMob(int hoehe,int breite,int posX, int posY, int Dx, int Speed, int SpawnX, int SpawnY, int damage, int Hp) {
		GameLogic.mobs.add(new TestMob(hoehe, breite, posX, posY, Dx, 0, Speed, SpawnX, SpawnY, damage, Hp));
	}

	public static void createBullet() {
	
	}

	public static void createDungeonChooser(int posX, int posY) {
		GameLogic.interactables.add(new DungeonChooser(10, 10, posX, posY));
	}

	public static void createShopToolsOpen(int posX, int posY) {
		GameLogic.interactables.add(new ShopOpenTools(10, 10, posX, posY));
	}

	public static void createShopPotionsOpen(int posX, int posY) {
		GameLogic.interactables.add(new ShopOpenPotions(10, 10, posX, posY));
	}

	public static void createDungeonExit(int posX, int posY) {
		GameLogic.interactables.add(new DungeonExit(10, 10, posX, posY));
	}

}
