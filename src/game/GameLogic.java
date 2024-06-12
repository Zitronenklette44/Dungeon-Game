package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import entitys.Bullet;
import entitys.DungeonChooser;
import entitys.InteractableTemplate;
import entitys.Player;
import entitys.ShopOpenPotions;
import entitys.ShopOpenTools;
import entitys.TestMob;
import gameObject.CollisionRechteck;
import gameObject.Column;
import gameObject.DeathRechteck;
import gameObject.Rechteck;
import gui.GameScreen;
import rooms.CreateDungeon;
import rooms.CreateRooms;

public class GameLogic {

	public static boolean moveLeft = false;
	public static boolean moveRight = false;
	public static boolean jump = false;
	public static boolean Interact = false;
	public static ArrayList<Rechteck> spielObjekte;
	public static ArrayList<Column> columns;
	public static ArrayList<CollisionRechteck> collisionRectangles;
	public static ArrayList<DeathRechteck> deathRechteck;
	public static ArrayList<TestMob> mobs;
	public static ArrayList<Bullet> bullets;
	public static ArrayList<InteractableTemplate> interactables;
	
	public static Player player;
	public static Rechteck FloorObject;
	public static int screenHoehe;
	public static int screenBreite;
	public static int floor = 700;
	public static int jumpHight = 70;
	public static int playerSpeed = 2;
	public static int[] resetPos = {50,700};
	public static int[] resetPos1 = {1100,700};
	public static int directionRoom = 0;
	public static float playerVelY = 0;
	public static float gravity = 0.1f;
	private static int jumpStart;
	public static boolean onGround=false;
	static boolean jumpInitialized = false;
	public static boolean isSpacePressed = false;
	public CreateDungeon dungeon = new CreateDungeon();

	public GameLogic() {
		Timer gameTimer = new Timer();
		spielObjekte = new ArrayList<Rechteck>();
		columns = new ArrayList<Column>();
		deathRechteck = new ArrayList<DeathRechteck>();
		mobs =new ArrayList<TestMob>();
		bullets = new ArrayList<Bullet>();
		interactables = new ArrayList<InteractableTemplate>();
		
		collisionRectangles = new ArrayList<>();
		screenBreite =GameScreen.getScreenBreite();
		screenHoehe = GameScreen.getScreenHoehe();

		createObjekts();
		dungeon.createDungeon();
		







		gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				playerMovement();
				mobMovement();
				
				if(player.HitCooldown>0) {player.HitCooldown--;}
				if(player.AtkCooldown>0) {player.AtkCooldown--;}
				
				
				
				
				
				
				if(player.posX<=0&&dungeon.currentRoom>=1) {
					directionRoom = 1;
					resetLevel();
					dungeon.currentRoom--;
					GameScreen.updateRoomNr(dungeon.currentRoom+1);
				}else if(player.posX>screenBreite-player.breite&&dungeon.currentRoom<dungeon.getDungeonLenght()-1) {
					directionRoom=0;
					resetLevel();
					dungeon.currentRoom++;
					GameScreen.updateRoomNr(dungeon.currentRoom+1);
				}
				
				if(player.posX<0) {
					player.posX = 0;
				}else if(player.posX>screenBreite-player.breite) {
					player.posX =1150;
					
				}


			}
		}, 0, 5);
	}

	private static void createObjekts() {
		player = new Player(50, 50, screenBreite/2, floor-2, 0, 0, playerSpeed, 0, 0, 3, 20);

		FloorObject = new Rechteck(50, screenBreite, 0, screenHoehe-50);
		spielObjekte.add(FloorObject);
	}

	public static void resetLevel() {
		if(directionRoom == 0) {
			player.posX = resetPos[0];
			player.posY = resetPos[1];
		}else if(directionRoom == 1) {
			player.posX = resetPos1[0];
			player.posY = resetPos1[1];

		}
		for (int i = 0; i < mobs.size(); i++) {
			mobs.get(i).posX = mobs.get(i).SpawnX;
			mobs.get(i).posY = mobs.get(i).SpawnY;
		}
	}

	public static void createColumn(int breite, int hoehe , Color farbe,Color farbe2, int posX, int posY) {
		columns.add(new Column(breite, hoehe, posX, posY, farbe, farbe2));
	}

	public static void createCollisionRechteck(int hoehe,int breite,int posX, int posY) {
		collisionRectangles.add(new CollisionRechteck(hoehe, breite, posX, posY));
	}

	public static void createDeathReckteck(int hoehe,int breite,int posX, int posY) {
		deathRechteck.add(new DeathRechteck(hoehe, breite, posX, posY));
	}

	public static void createTestMob(int hoehe,int breite,int posX, int posY, int Dx, int Speed, int SpawnX, int SpawnY, int damage, int Hp) {
		mobs.add(new TestMob(hoehe, breite, posX, posY, Dx, 0, Speed, SpawnX, SpawnY, damage, Hp));
	}
	
	public static void createBullet() {
		
	}
	
	public static void createDungeonChooser(int posX, int posY) {
		interactables.add(new DungeonChooser(10, 10, posX, posY));
	}
	
	public static void createShopToolsOpen(int posX, int posY) {
		interactables.add(new ShopOpenTools(10, 10, posX, posY));
	}
	public static void createShopPotionsOpen(int posX, int posY) {
		interactables.add(new ShopOpenPotions(10, 10, posX, posY));
	}
	
	private static void playerMovement() {
		// Spielerbewegung
		if (moveLeft && !Collisions.checkCollision(player, -2, 0)) {
			player.posX -= 2;
		}
		if (moveRight && !Collisions.checkCollision(player, 2, 0)) {
			player.posX += 2;
		}
		
		if(jump&&onGround) {
			playerVelY=-3.5f;
		}
		
		if(!onGround) {
			playerVelY = playerVelY+gravity;
			
		}
		
		
		if (!Collisions.isCollisionAbovePlayer()) {
            if (!Collisions.checkCollision(player, 0, (int) playerVelY)) {
                player.posY += playerVelY;
            } else {
                playerVelY = 0;
            }
        } else {
            playerVelY = 0;
        }			

		// Kollisionserkennung für Spieler
		Collisions.updateOnGroundStatus();

		if (Collisions.checkDeathBlock(player, 0, -1)) {
			resetLevel();
		}
		
		if(Interact) {
			for(int i = 0; i<interactables.size();i++) {
				if(interactables.get(i).actionEnabled) {
					interactables.get(i).performAction();
				}
			}
			Interact = false;
		}

	}
	
	private static void mobMovement(){

		//movement mobs
		for (int i = 0; i < mobs.size(); i++) {
			TestMob mob = mobs.get(i);

			if (player.posX > mob.posX) {
				mob.dx = 1;
			} else if (player.posX < mob.posX) {
				mob.dx = -1;
			} else {
				mob.dx = 0;
			}

			// Bewege den Mob nur, wenn keine Kollision vorliegt
			if (mob.dx > 0 && !Collisions.checkCollision(mob, mob.speed, 0)) {
				mob.posX += mob.speed;
			} else if (mob.dx < 0 && !Collisions.checkCollision(mob, -mob.speed, 0)) {
				mob.posX -= mob.speed;
			}

			if(Collisions.checkPlayer(mob, 1, 0)) {
				if(player.HitCooldown==0) {
					player.Hp -= mob.damage;
					player.setHitCooldown();
					System.out.println("Player HP: "+ player.Hp);
				}
			}
		}
	}

	
}
