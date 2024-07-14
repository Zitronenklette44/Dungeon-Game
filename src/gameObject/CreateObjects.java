package gameObject;

import java.awt.Color;

import entitys.Arrow;
import entitys.DungeonChooser;
import entitys.DungeonExit;
import entitys.MobTemplate;
import entitys.Player;
import entitys.ShopOpenPotions;
import entitys.ShopOpenTools;
import entitys.TestMob;
import game.GameLogic;

public class CreateObjects {		//Allgemeine Methoden zum erstellen verschiedener Objekte

	public static void createObjekts() {	//anfängliche initierung der globalen Objekte wie Spieler und Boden
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

	public static void createSwordAttack(int reach, float posX, float posY, int height , int damage, int duration, boolean damagePlayer, boolean damageMobs) {
		SwordAttack swordAttack = new SwordAttack(reach, posX, posY, height, damage, duration, damagePlayer, damageMobs);
		swordAttack.isVisible=false;
		GameLogic.swordAttacks.add(swordAttack);
	}

	public static MobTemplate createTestMob(int hoehe,int breite, float speed, int SpawnX, int SpawnY, int damage, int Hp) {
		MobTemplate template =new TestMob(hoehe, breite, SpawnX, SpawnY, 0, 0, speed, SpawnX, SpawnY, damage, Hp);
		GameLogic.mobs.add(template);
		return template;
	}

	public static void createArrow(int hoehe, int breite, float posX, float posY, float speed, int SpawnX, int SpawnY, int damage, float range) {
		Arrow bullet = new Arrow(hoehe, breite, posX, posY, 0, 0, speed, SpawnX, SpawnY, damage, range);
		bullet.rotateToPlayerDirection();
		GameLogic.arrows.add(bullet);
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
