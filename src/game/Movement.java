package game;

import entitys.MobTemplate;

public class Movement {

	static void playerMovement() {
		boolean mLeft = GameLogic.moveLeft;
		boolean mRight = GameLogic.moveRight;
		boolean mUp = GameLogic.moveUp;
		boolean mDown = GameLogic.moveDown;

		// Spielerbewegung
		if(GameLogic.vertikalAxis) {
			if(mLeft && mUp) {
				if(!Collisions.checkCollision(GameLogic.player, -(GameLogic.player.speed+1), 0)) {
					GameLogic.player.posX -= GameLogic.player.speed;
					GameLogic.player.dx = -GameLogic.player.speed;
				}
				if(!Collisions.checkCollision(GameLogic.player, 0, -(GameLogic.player.speed+1))) {
					GameLogic.player.posY -= GameLogic.player.speed;
					GameLogic.player.dy = -GameLogic.player.speed;
				}
			}
			if(mLeft && mDown) {
				if (!Collisions.checkCollision(GameLogic.player, -(GameLogic.player.speed+1), 0)) {
					GameLogic.player.posX -= GameLogic.player.speed ;
					GameLogic.player.dx = -GameLogic.player.speed;
				}
				if (!Collisions.checkCollision(GameLogic.player, 0, (GameLogic.player.speed+1))) {
					GameLogic.player.posY += GameLogic.player.speed ;
					GameLogic.player.dy = GameLogic.player.speed;
				}
			}
			if(mRight && mUp) {
				if (!Collisions.checkCollision(GameLogic.player, (GameLogic.player.speed+1), 0)) {
					GameLogic.player.posX += GameLogic.player.speed ;
					GameLogic.player.dx = GameLogic.player.speed;
				}
				if (!Collisions.checkCollision(GameLogic.player, 0, -(GameLogic.player.speed+1))) {
					GameLogic.player.posY -= GameLogic.player.speed ;
					GameLogic.player.dy = -GameLogic.player.speed;
				}
			}
			if(mRight && mDown) {
				if (!Collisions.checkCollision(GameLogic.player, (GameLogic.player.speed+1), 0)) {
					GameLogic.player.posX += GameLogic.player.speed ;
					GameLogic.player.dx = GameLogic.player.speed;
				}
				if (!Collisions.checkCollision(GameLogic.player, 0, (GameLogic.player.speed+1))) {
					GameLogic.player.posY += GameLogic.player.speed ;
					GameLogic.player.dy = GameLogic.player.speed;
				}
			}
		}else {
			mDown = false;
			mUp = false;
		}

		if (mLeft && !Collisions.checkCollision(GameLogic.player, -(GameLogic.player.speed+1), 0) &&!mRight&&!mUp&&!mDown) {
			GameLogic.player.posX -= GameLogic.player.speed;
			GameLogic.player.dx = -GameLogic.player.speed;
			GameLogic.player.dy = 0;
		}
		if (mRight && !Collisions.checkCollision(GameLogic.player, (GameLogic.player.speed+1), 0)&&!mLeft&&!mUp&&!mDown) {
			GameLogic.player.posX += GameLogic.player.speed;
			GameLogic.player.dx = GameLogic.player.speed;
			GameLogic.player.dy = 0;
		}
		if (mUp && !Collisions.checkCollision(GameLogic.player, 0, -(GameLogic.player.speed+1))&&GameLogic.vertikalAxis&&GameLogic.player.posY>0&&!mDown&&!mLeft&&!mRight) {
			GameLogic.player.posY -= GameLogic.player.speed;
			GameLogic.player.dy = -GameLogic.player.speed;
			GameLogic.player.dx = 0;
		}
		if (mDown && !Collisions.checkCollision(GameLogic.player, 0, (GameLogic.player.speed+1))&&GameLogic.vertikalAxis&&GameLogic.player.posY<GameLogic.floor&&!mUp&&!mLeft&&!mRight) {
			GameLogic.player.posY += GameLogic.player.speed;
			GameLogic.player.dy = GameLogic.player.speed;
			GameLogic.player.dx = 0;
		}	
		
		float lastDx = GameLogic.player.dx;
		float lastDy = GameLogic.player.dy;


		if(GameLogic.jump&&GameLogic.onGround&&!GameLogic.vertikalAxis) {
			GameLogic.playerVelY=-3.5f;
		}

		if(!GameLogic.onGround&&!GameLogic.vertikalAxis) {
			GameLogic.playerVelY = GameLogic.playerVelY+GameLogic.gravity;

		}


		if (!Collisions.isCollisionAbovePlayer()&&!GameLogic.vertikalAxis) {
			if (!Collisions.checkCollision(GameLogic.player, 0, (int) GameLogic.playerVelY)) {
				GameLogic.player.posY += GameLogic.playerVelY;
			} else {
				GameLogic.playerVelY = 0;
			}
		} else {
			GameLogic.playerVelY = 0;
		}			

		// Kollisionserkennung für Spieler
		if(!GameLogic.vertikalAxis) {
			Collisions.updateOnGroundStatus();
		}

		if (Collisions.checkDeathBlock(GameLogic.player, 0, -1)) {
			GameLogic.resetLevel();
		}

		if(GameLogic.Interact) {
			for(int i = 0; i<GameLogic.interactables.size();i++) {
				try {
					if(GameLogic.interactables.get(i).actionEnabled) {
						GameLogic.interactables.get(i).performAction();
					}
				} catch (IndexOutOfBoundsException e) {}
			}
		}
		if(GameLogic.counterInteraction >=50) {
			GameLogic.Interact = false;
			GameLogic.counterInteraction = 0;
		}
		if (isInWall(GameLogic.player)) {
			moveBack(GameLogic.player, lastDx, lastDy);
		}
	}

	static void mobMovement() {
		// Movement for mobs
		for (int i = 0; i < GameLogic.mobs.size(); i++) {
			MobTemplate mob = GameLogic.mobs.get(i);

			if(!mob.defeated) {
				boolean moveHorizontally = true;
				boolean moveVertically = true;

				// Determine direction based on player position
				if (GameLogic.player.posX > mob.posX) {
					mob.dx = mob.speed;
				} else if (GameLogic.player.posX < mob.posX) {
					mob.dx = -mob.speed;
				} else {
					mob.dx = 0;
				}

				if (GameLogic.player.posY > mob.posY) {
					mob.dy = mob.speed;
				} else if (GameLogic.player.posY < mob.posY) {
					mob.dy = -mob.speed;
				} else {
					mob.dy = 0;
				}

				float lastDx = mob.dx;
				float lastDy = mob.dy;

				// Check for obstacles and adjust direction
				if (mob.dx != 0) {
					if (Collisions.checkCollision(mob, mob.dx, 0)) {
						// Horizontal collision, try vertical movement instead
						if (!Collisions.checkCollision(mob, 0, mob.dy)) {
							mob.dx = 0;
						} else {
							mob.dx = -mob.dx; // Reverse horizontal direction
						}
					}
				}

				if (mob.dy != 0) {
					if (Collisions.checkCollision(mob, 0, mob.dy)) {
						// Vertical collision, try horizontal movement instead
						if (!Collisions.checkCollision(mob, mob.dx, 0)) {
							mob.dy = 0;
						} else {
							mob.dy = -mob.dy; // Reverse vertical direction
						}
					}
				}

				if(!Collisions.checkMob(mob, mob.dx, mob.dy)) {

					// Horizontal movement
					if (moveHorizontally && mob.dx != 0 && !Collisions.checkCollision(mob, mob.dx, 0)) {
						mob.posX += mob.dx;
					}

					// Vertical movement
					if (moveVertically && mob.dy != 0 && !Collisions.checkCollision(mob, 0, mob.dy)) {
						mob.posY += mob.dy;
					}

					if (isInWall(mob)) {
						moveBack(mob, lastDx, lastDy);
					}

					
				}else {
					//moveBack(mob, lastDx, lastDy);
				}
				// Check collision with player
				if (Collisions.checkPlayer(mob, 1, 0) || Collisions.checkPlayer(mob, 0, 1)||Collisions.checkPlayer(mob, 0, 0)) {
					if (GameLogic.player.HitCooldown == 0) {
						GameLogic.player.Hp -= mob.damage;
						GameLogic.player.setHitCooldown();
						System.out.println("Player HP: " + GameLogic.player.Hp);
					}
				}
			}
		}
	}

	private static boolean isInWall(MobTemplate mob) {
		// Check collision at the current position
		return Collisions.checkCollision(mob, 0, 0);
	}

	// Method to move the mob back if it's inside a wall
	private static void moveBack(MobTemplate mob, float lastDx, float lastDy) {
		// Move the mob back in the opposite direction of the last movement
		if (!Collisions.checkCollision(mob, -lastDx, 0)) {
			mob.posX -= lastDx;
		}
		if (!Collisions.checkCollision(mob, 0, -lastDy)) {
			mob.posY -= lastDy;
		}
	}


}
