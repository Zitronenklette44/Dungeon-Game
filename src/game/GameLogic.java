package game;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import entitys.Bullet;
import entitys.InteractableTemplate;
import entitys.Player;
import entitys.TestMob;
import gameObject.CollisionRechteck;
import gameObject.Column;
import gameObject.CreateObjects;
import gameObject.DeathRechteck;
import gameObject.Rechteck;
import gui.GameScreen;
import rooms.CreateDungeon;

public class GameLogic {

	public static boolean moveLeft = false;
	public static boolean moveRight = false;
	public static boolean moveUp = false;
	public static boolean moveDown= false;
	public static boolean jump = false;
	public static boolean Interact = false;
	public static ArrayList<Rechteck> floorObject;
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
	public static int dungeonKey=1;
	public static int counterInteraction = 0;
	public static boolean onGround=false;
	static boolean jumpInitialized = false;
	public static boolean isSpacePressed = false;
	public static boolean vertikalAxis = false;
	public static boolean debug = false;

	public static CreateDungeon dungeon;

	public GameLogic() {
		Timer gameTimer = new Timer();
		Timer OnesTimer = new Timer();
		floorObject = new ArrayList<Rechteck>();
		columns = new ArrayList<Column>();
		deathRechteck = new ArrayList<DeathRechteck>();
		mobs =new ArrayList<TestMob>();
		bullets = new ArrayList<Bullet>();
		interactables = new ArrayList<InteractableTemplate>();
		dungeon = new CreateDungeon();

		collisionRectangles = new ArrayList<>();
		screenBreite =GameScreen.getScreenBreite();
		screenHoehe = GameScreen.getScreenHoehe();

		CreateObjects.createObjekts();
		dungeon.createDungeon();








		gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				playerMovement();
				mobMovement();

				if(player.HitCooldown>0) {player.HitCooldown--;}
				if(player.AtkCooldown>0) {player.AtkCooldown--;}






				if(player.posX<=0&&CreateDungeon.currentRoom>=1) {
					directionRoom = 1;
					CreateDungeon.currentRoom--;
					resetLevel();
					GameScreen.updateRoomNr(CreateDungeon.currentRoom+1);
					GameScreen.changeBackground(CreateDungeon.getImage(0));
				}else if(player.posX>screenBreite-player.breite&&CreateDungeon.currentRoom<dungeon.getDungeonLenght()-1) {
					directionRoom=0;
					CreateDungeon.currentRoom++;
					resetLevel();
					GameScreen.updateRoomNr(CreateDungeon.currentRoom+1);
					GameScreen.changeBackground(CreateDungeon.getImage(0));
				}

				if(player.posX<0) {
					player.posX = 0;
				}else if(player.posX>screenBreite-player.breite) {
					player.posX =1150;

				}
				if(Interact) {
					counterInteraction++;
				}
				
			}
		}, 0, 5);
		
		
		
		OnesTimer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				try {
					GameScreen.changeBackground(CreateDungeon.getImage(0));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}, 0, 1000);
	}

	public static void resetLevel() {
		dungeon.setSpawns();
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

	private static void playerMovement() {
		// Spielerbewegung

		if (moveLeft && !Collisions.checkCollision(player, -3, 0) &&!moveRight) {
			player.posX -= 2;
		}
		if (moveRight && !Collisions.checkCollision(player, 3, 0)&&!moveLeft) {
			player.posX += 2;
		}
		if (moveUp && !Collisions.checkCollision(player, 0, -3)&&vertikalAxis&&player.posY>0&&!moveDown) {
			player.posY -= 2;
		}
		if (moveDown && !Collisions.checkCollision(player, 0, 3)&&vertikalAxis&&player.posY<floor&&!moveUp) {
			player.posY += 2;
		}


		if(jump&&onGround&&!vertikalAxis) {
			playerVelY=-3.5f;
		}

		if(!onGround&&!vertikalAxis) {
			playerVelY = playerVelY+gravity;

		}


		if (!Collisions.isCollisionAbovePlayer()&&!vertikalAxis) {
			if (!Collisions.checkCollision(player, 0, (int) playerVelY)) {
				player.posY += playerVelY;
			} else {
				playerVelY = 0;
			}
		} else {
			playerVelY = 0;
		}			

		// Kollisionserkennung für Spieler
		if(!vertikalAxis) {
			Collisions.updateOnGroundStatus();
		}

		if (Collisions.checkDeathBlock(player, 0, -1)) {
			resetLevel();
		}

		if(Interact) {
			for(int i = 0; i<interactables.size();i++) {
				try {
					if(interactables.get(i).actionEnabled) {
						interactables.get(i).performAction();
					}
				} catch (IndexOutOfBoundsException e) {}
			}
		}
		if(counterInteraction >=50) {
			Interact = false;
			counterInteraction = 0;
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
