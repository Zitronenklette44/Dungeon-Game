package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import action.Logger;
import entitys.Arrow;
import entitys.MobTemplate;
import entitys.Player;
import functionalObjects.FunctionalTemplate;
import gameObject.CollisionRechteck;
import gameObject.Column;
import gameObject.SwordAttack;
import gameObject.Rechteck;
import gui.GameScreen;
import interactions.InteractableTemplate;
import loot.items.ItemTemplate;
import rendering.Draw;
import rooms.DungeonCore;
import spells.SpellManager;

public class GameLogic {

	public static boolean moveLeft = false;
	public static boolean moveRight = false;
	public static boolean moveUp = false;
	public static boolean moveDown= false;
	public static boolean jump = false;
	public static boolean Interact = false;
	public static boolean fireSpell = false;
	public static ArrayList<Rechteck> floorObject;
	public static ArrayList<Column> columns;
	public static ArrayList<CollisionRechteck> collisionRectangles;
	public static ArrayList<SwordAttack> swordAttacks;
	public static ArrayList<MobTemplate> mobs;
	public static ArrayList<Arrow> arrows;
	public static ArrayList<InteractableTemplate> interactables;
	public static ArrayList<FunctionalTemplate> functionalObjects;
	public static ArrayList<ItemTemplate> items;
	
	public static Player player;
	public static Rechteck FloorObject;
	public static int screenHoehe;
	public static int screenBreite;
	public static int floor = 700;
	public static int jumpHight = 70;
	public static int[] resetPos = {50,700};
	public static int[] resetPos1 = {1100,700};
	public static int directionRoom = 0;
	public static float playerVelY = 0;
	public static float gravity = 0.1f;
	public static int dungeonKey=1;
	public static int counterInteraction = 0;
	public static float BaseSpeed = 2F;
	public  static float VerticalSpeed = 1F;
	public static boolean onGround=false;
	static boolean jumpInitialized = false;
	public static boolean isSpacePressed = false;
	public static boolean vertikalAxis = false;
	public static boolean debug = false;
	public static boolean paused = false;
	public static boolean musicEnabled = true;
	public static int Coins = 0;
	public static int[] unlockedSpells = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

	public static DungeonCore dungeon;
	public static float playerSpeed = 1F;

	public GameLogic() {
		Timer gameTimer = new Timer();
		Timer OnesTimer = new Timer();
		floorObject = new ArrayList<Rechteck>();
		columns = new ArrayList<Column>();
		swordAttacks = new ArrayList<SwordAttack>();
		mobs =new ArrayList<MobTemplate>();
		arrows = new ArrayList<Arrow>();
		interactables = new ArrayList<InteractableTemplate>();
		functionalObjects = new ArrayList<FunctionalTemplate>();
		items = new ArrayList<ItemTemplate>();
		dungeon = new DungeonCore();

		collisionRectangles = new ArrayList<>();
		screenBreite =GameScreen.getScreenBreite();
		screenHoehe = GameScreen.getScreenHoehe();

		CreateObjects.createObjekts();		//standart Objekte erstellen
		dungeon.createDungeon();		//start Dorf erstellen

		gameTimer.scheduleAtFixedRate(new TimerTask() {		//5ms Timer

			public void run() {
				try {DungeonCore.thisRooms.get(DungeonCore.currentRoom).update();} catch (IndexOutOfBoundsException e) {}

				if(vertikalAxis) {playerSpeed = VerticalSpeed;}else {playerSpeed = BaseSpeed;}
				player.speed = playerSpeed;
				if(!paused) {
					Movement.playerMovement();		//bewegung Spieler + Mobs
					Movement.mobMovement();
					if(fireSpell) {SpellManager.fireSpell(player, GameScreen.selectedSpell);fireSpell=false;}	//wenn Spell ausgelöst wurde entsprechenden abfeuern und trigger zurücksetzen
					
					decreaseCooldowns();
					
				}
				changeRoom();		//Raum wechsel
				
				for(int i = 0; i<swordAttacks.size();i++) {		//Schaden hinzufügen durch Schwert angriffe und bei bedarf entfernen
					swordAttacks.get(i).dealDamage();
					if(swordAttacks.get(i).remove()) {
						swordAttacks.remove(i);
						i--;
					}
				}
				
				if(Interact) {counterInteraction++;} //Interaction mit den Interaction Objecten
				
				
				if(player.posX<0) {	//verhindern das Spieler außerhalb des Bildschirmes glitcht
					player.posX = 0;
				}else if(player.posX>screenBreite-player.breite) {
					player.posX =1150;
				}
				if(player.posY<0) {	//verhindern das Spieler außerhalb des Bildschirmes glitcht
					player.posY = 0;
				}else if(player.posY>screenHoehe-player.hoehe) {
					player.posY =screenHoehe-player.hoehe-50;
				}
				
				for(int i = 0; i<SpellManager.cooldowns.length;i++) {	//Cooldown veringern
					if(SpellManager.cooldowns[i]>0 && !paused) {SpellManager.cooldowns[i] -=0.005;}
				}
				
				if(!paused) { SpellManager.updateSpells();}	//Spell bewegungen und ander Updates durchführen
			}
		}, 0, 5);
		
		
		
		OnesTimer.scheduleAtFixedRate(new TimerTask() {		//1s Timer
			public void run() {
				try {
					GameScreen.changeBackground(DungeonCore.getImage(0));		//Hintergrund erneuern
					if(player.mana+player.restoreMana < player.maxMana && !paused) {player.mana += player.restoreMana;}else {player.mana = player.maxMana;}	//Mana erneuern über Zeit
				} catch (Exception e) {
					e.printStackTrace();
				}		
//				System.out.println("floor: "+floorObject.size());
//				System.out.println("cloums: "+columns.size());
//				System.out.println("collisionRectangles: "+collisionRectangles.size());
//				System.out.println("deathRechtecks: "+swordAttacks.size());
//				System.out.println("mobs: "+mobs.size());
//				System.out.println("arrows: "+arrows.size());
//				System.out.println("interactables: "+interactables.size());
//				System.out.println("functionable: "+functionalObjects.size());
//				System.out.println("spells: "+SpellManager.currentSpells.size());
//				System.out.println("items: "+items.size());
//				Logger.logInfo("Coins: "+ Coins);
			}
		}, 0, 1000);
	}

	public void changeRoom() {
		if(player.posX<=0&&DungeonCore.currentRoom>=1) {		//Wenn am linken ende des Raumes und weiterer Raum vorhanden
			directionRoom = 1;		//setze richtung auf rechts nach links
			DungeonCore.currentRoom--;	//aktuellen Raum um 1 nach hinten verscheieben
			resetLevel();		//Level reseten
			GameScreen.updateRoomNr(DungeonCore.currentRoom+1);		//Raum Nummer updaten
		}else if(player.posX>screenBreite-player.breite&&DungeonCore.currentRoom<dungeon.getDungeonLenght()-1) {		//Wenn am rechten Rand des Raumes und weiterer Raum vorhanden
			directionRoom=0;	//richtung auf links nach rechts setzen
			DungeonCore.currentRoom++;	//aktuellen raum um 1 nach vorne verscheiben
			GameScreen.updateRoomNr(DungeonCore.currentRoom+1);	//Raum Nummer updaten
			resetLevel();	//Level reseten
		}
		try {
			GameScreen.changeBackground(DungeonCore.getImage(0));
		} catch (Exception e) {}
	}

	public static void resetLevel() {
		Draw.clearObjects();
		DungeonCore.thisRooms.get(DungeonCore.currentRoom).createObjects(DungeonCore.currentRoom);
		SpellManager.removeAllSpells();	//alle existierenden Spells löschen und in den Pool zurückgeben
		dungeon.setSpawns();		//reset Punkte aus dem aktuellen Raum abrufen
		items.clear();
		if(directionRoom == 0) {	//wenn von links nach rechts
			player.posX = resetPos[0];	
			player.posY = resetPos[1];
		}else if(directionRoom == 1) {	//wenn von rechts nach links
			player.posX = resetPos1[0];
			player.posY = resetPos1[1];

		}
		for (int i = 0; i < mobs.size(); i++) {	//für alle mobs position auf Spawn zurücksetzen
			mobs.get(i).posX = mobs.get(i).SpawnX;
			mobs.get(i).posY = mobs.get(i).SpawnY;
		}
	}
	
	private void decreaseCooldowns() {
		if(player.HitCooldown>0) {player.HitCooldown--;}		//Cooldown verringern
		if(player.AtkCooldown>0) {player.AtkCooldown--;}
		
		for(int i  = 0; i<mobs.size();i++) {
			if(mobs.get(i).HitCooldown>0) {
				mobs.get(i).HitCooldown--;
			}else {
				mobs.get(i).HitCooldown = 0;
			}
		}
	}


}
