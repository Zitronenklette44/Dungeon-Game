package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import entitys.Bullet;
import entitys.TestMob;
import gameObject.CollisionRechteck;
import gameObject.Column;
import gameObject.DeathRechteck;
import gameObject.Rechteck;
import gui.TestScreen;
import rooms.CreateDungeon;
import rooms.CreateRooms;

public class GameLogic {

	public boolean moveLeft = false;
	public boolean moveRight = false;
	public boolean jump = false;
	public static ArrayList<Rechteck> spielObjekte;
	public static ArrayList<Column> columns;
	public static ArrayList<CollisionRechteck> collisionRectangles;
	public static ArrayList<DeathRechteck> deathRechteck;
	public static ArrayList<TestMob> mobs;
	public static ArrayList<Bullet> bullets;
	
	public static Rechteck player;
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
	private static boolean jumpInitialized = false;
	public static boolean isSpacePressed = false;
	public static CreateDungeon dungeon = new CreateDungeon();

	public GameLogic() {
		Timer gameTimer = new Timer();
		spielObjekte = new ArrayList<Rechteck>();
		columns = new ArrayList<Column>();
		deathRechteck = new ArrayList<DeathRechteck>();
		mobs =new ArrayList<TestMob>();
		bullets = new ArrayList<Bullet>();
		
		collisionRectangles = new ArrayList<>();
		screenBreite =TestScreen.getScreenBreite();
		screenHoehe = TestScreen.getScreenHoehe();

		createObjekts();
		dungeon.createDungeon();
		







		gameTimer.scheduleAtFixedRate(new TimerTask() {

			public void run() {

				// Spielerbewegung
				if (moveLeft && !checkCollision(player, -2, 0)) {
					player.posX -= 2;
				}
				if (moveRight && !checkCollision(player, 2, 0)) {
					player.posX += 2;
				}
				
				if(jump&&onGround) {
					playerVelY=-3.5f;
				}
				
				if(!onGround) {
					playerVelY = playerVelY+gravity;
					
				}
				
				
				if (!isCollisionAbovePlayer()) {
		            if (!checkCollision(player, 0, (int) playerVelY)) {
		                player.posY += playerVelY;
		            } else {
		                playerVelY = 0;
		            }
		        } else {
		            playerVelY = 0;
		        }			
				
				
				/*
				if (jump) {
					if (!jumpInitialized && onGround) {
						jumpStart = player.posY;
						jumpInitialized = true;
					}
					if (jumpInitialized && player.posY > jumpStart - jumpHight && !checkCollision(player, 0, -1)) {
						player.posY--;
					} else if (!onGround) {
						jump = false;
					}
				} else {
					if (!onGround && !checkCollision(player, 0, 1)) {
						player.posY++;
					}
				}*/

				// Kollisionserkennung für Spieler
				updateOnGroundStatus();

				if (checkDeathBlock(player, 0, -1)) {
					resetLevel();
				}

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
					if (mob.dx > 0 && !checkCollision(mob, mob.speed, 0)) {
						mob.posX += mob.speed;
					} else if (mob.dx < 0 && !checkCollision(mob, -mob.speed, 0)) {
						mob.posX -= mob.speed;
					}

					if(checkPlayer(mob, 1, 0)) {
						resetLevel();
					}
				}

				if(player.posX<=0&&dungeon.currentRoom>=1) {
					CreateRooms.createRoom(dungeon.getLastRoom());
					directionRoom = 1;
					resetLevel();
					dungeon.currentRoom--;
					TestScreen.updateRoomNr(dungeon.currentRoom+1);
				}else if(player.posX>screenBreite-player.breite&&dungeon.currentRoom<dungeon.DungeonLenght-1) {
					CreateRooms.createRoom(dungeon.getNextRoom());
					directionRoom=0;
					resetLevel();
					dungeon.currentRoom++;
					TestScreen.updateRoomNr(dungeon.currentRoom+1);
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
		player = new Rechteck(50, 50, screenBreite/2, floor-2);

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

	public static void createTestMob(int hoehe,int breite,int posX, int posY, int Dx, int Speed, int SpawnX, int SpawnY) {
		mobs.add(new TestMob(hoehe, breite, posX, posY, Dx, 0, Speed, SpawnX, SpawnY));
	}
	
	public static void createBullet() {
		
	}

	private boolean checkCollision(Rechteck rect, int deltaX, int deltaY) {
		// Berechne die zukünftige Position des Rechtecks
		int futurePosX = rect.posX + deltaX;
		int futurePosY = rect.posY + deltaY;

		// Überprüfe, ob eine Kollision auftreten würde
		for (CollisionRechteck collisionRect : collisionRectangles) {
			if (futurePosX < collisionRect.posX + collisionRect.breite &&
					futurePosX + rect.breite > collisionRect.posX &&
					futurePosY < collisionRect.posY + collisionRect.hoehe &&
					futurePosY + rect.hoehe > collisionRect.posY) {
				// Kollision gefunden
				return true;
			}
		}
		// Keine Kollision
		return false;
	}
	
	private boolean isCollisionAbovePlayer() {
	    for (CollisionRechteck collisionRect : collisionRectangles) {
	        if (player.posX < collisionRect.posX + collisionRect.breite &&
	            player.posX + player.breite > collisionRect.posX &&
	            player.posY > collisionRect.posY + collisionRect.hoehe &&
	            player.posY + playerVelY <= collisionRect.posY + collisionRect.hoehe) {
	            return true;
	        }
	    }
	    return false;
	}

	private void updateOnGroundStatus() {
	    onGround = false;
	    if (player.posY >= floor) {
	        onGround = true;
	        jumpInitialized = false;
	        player.posY = floor;
	    } else {
	        for (CollisionRechteck collisionRect : collisionRectangles) {
	            if (player.posX < collisionRect.posX + collisionRect.breite &&
	                    player.posX + player.breite > collisionRect.posX &&
	                    player.posY + player.hoehe <= collisionRect.posY &&
	                    player.posY + player.hoehe + playerVelY >= collisionRect.posY) {
	                onGround = true;
	                jumpInitialized = false;
	                player.posY = collisionRect.posY - player.hoehe;
	                playerVelY = 0;
	                break;
	            }
	        }
	    }
	}

	private boolean checkDeathBlock(Rechteck rect, int deltaX, int deltaY) {
		// Berechne die zukünftige Position des Rechtecks
		int futurePosX = rect.posX + deltaX;
		int futurePosY = rect.posY + deltaY;

		// Überprüfe, ob eine Kollision mit einem DeathRechteck auftreten würde
		for (DeathRechteck deathRect : deathRechteck) {
			if (futurePosX < deathRect.posX + deathRect.breite &&
					futurePosX + rect.breite > deathRect.posX &&
					futurePosY < deathRect.posY + deathRect.hoehe &&
					futurePosY + rect.hoehe > deathRect.posY) {
				// Kollision gefunden
				return true;
			}
		}
		// Keine Kollision
		return false;
	}

	private boolean checkPlayer(Rechteck rect, int deltaX, int deltaY) {
		// Berechne die zukünftige Position des Rechtecks
		int futurePosX = rect.posX + deltaX;
		int futurePosY = rect.posY + deltaY;

		// Überprüfe, ob eine Kollision mit einem DeathRechteck auftreten würde
		if (futurePosX < player.posX + player.breite &&
				futurePosX + rect.breite > player.posX &&
				futurePosY < player.posY + player.hoehe &&
				futurePosY + rect.hoehe > player.posY) {
			// Kollision gefunden
			return true;

		}
		// Keine Kollision
		return false;
	}
}
