package game;

import entitys.MobTemplate;
import entitys.TestMob;

public class Movement {

	static void playerMovement() {
		boolean mLeft = GameLogic.moveLeft;
		boolean mRight = GameLogic.moveRight;
		boolean mUp = GameLogic.moveUp;
		boolean mDown = GameLogic.moveDown;
		
		// Spielerbewegung
		if(GameLogic.vertikalAxis) {
		if(mLeft && mUp) {
			if(!Collisions.checkCollision(GameLogic.player, -(GameLogic.playerSpeed+1), 0)) {
			GameLogic.player.posX -= GameLogic.playerSpeed/2;
			}
			if(!Collisions.checkCollision(GameLogic.player, 0, -(GameLogic.playerSpeed+1))) {
			GameLogic.player.posY -= GameLogic.playerSpeed/2;
			}
		}
		if(mLeft && mDown) {
			if (!Collisions.checkCollision(GameLogic.player, -(GameLogic.playerSpeed+1), 0)) {
				GameLogic.player.posX -= GameLogic.playerSpeed / 2;
			}
			if (!Collisions.checkCollision(GameLogic.player, 0, (GameLogic.playerSpeed+1))) {
				GameLogic.player.posY += GameLogic.playerSpeed / 2;
			}
		}
		if(mRight && mUp) {
			if (!Collisions.checkCollision(GameLogic.player, (GameLogic.playerSpeed+1), 0)) {
				GameLogic.player.posX += GameLogic.playerSpeed / 2;
			}
			if (!Collisions.checkCollision(GameLogic.player, 0, -(GameLogic.playerSpeed+1))) {
				GameLogic.player.posY -= GameLogic.playerSpeed / 2;
			}
		}
		if(mRight && mDown) {
			if (!Collisions.checkCollision(GameLogic.player, (GameLogic.playerSpeed+1), 0)) {
				GameLogic.player.posX += GameLogic.playerSpeed / 2;
			}
			if (!Collisions.checkCollision(GameLogic.player, 0, (GameLogic.playerSpeed+1))) {
				GameLogic.player.posY += GameLogic.playerSpeed / 2;
			}
		}
		}else {
			mDown = false;
			mUp = false;
		}
		
		if (mLeft && !Collisions.checkCollision(GameLogic.player, -(GameLogic.playerSpeed+1), 0) &&!mRight&&!mUp&&!mDown) {
			GameLogic.player.posX -= GameLogic.playerSpeed;
		}
		if (mRight && !Collisions.checkCollision(GameLogic.player, (GameLogic.playerSpeed+1), 0)&&!mLeft&&!mUp&&!mDown) {
			GameLogic.player.posX += GameLogic.playerSpeed;
		}
		if (mUp && !Collisions.checkCollision(GameLogic.player, 0, -(GameLogic.playerSpeed+1))&&GameLogic.vertikalAxis&&GameLogic.player.posY>0&&!mDown&&!mLeft&&!mRight) {
			GameLogic.player.posY -= GameLogic.playerSpeed;
		}
		if (mDown && !Collisions.checkCollision(GameLogic.player, 0, (GameLogic.playerSpeed+1))&&GameLogic.vertikalAxis&&GameLogic.player.posY<GameLogic.floor&&!mUp&&!mLeft&&!mRight) {
			GameLogic.player.posY += GameLogic.playerSpeed;
		}
	
	
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
	}

	static void mobMovement() {
	    // Movement for mobs
	    for (int i = 0; i < GameLogic.mobs.size(); i++) {
	        MobTemplate mob = GameLogic.mobs.get(i);

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

	        float diagonalSpeed = 0.5F;

	        // Diagonal movement
	        if (mob.dx != 0 && mob.dy != 0) {
	            if (!Collisions.checkCollision(mob, (int) (mob.dx * diagonalSpeed)+1, 0)) {
	                mob.posX += mob.dx * diagonalSpeed;
	            } else {
	                moveHorizontally = false;
	            }
	            if (!Collisions.checkCollision(mob, 0, (int) (mob.dy * diagonalSpeed)+1)) {
	                mob.posY += mob.dy * diagonalSpeed;
	            } else {
	                moveVertically = false;
	            }
	        }

	        // Horizontal movement
	        if (moveHorizontally && mob.dx != 0 && !Collisions.checkCollision(mob, mob.dx * mob.speed+1, 0)) {
	            mob.posX += mob.dx * mob.speed;
	        }

	        // Vertical movement
	        if (moveVertically && mob.dy != 0 && !Collisions.checkCollision(mob, 0, mob.dy * mob.speed+1)) {
	            mob.posY += mob.dy * mob.speed;
	        }

	        // Check collision with player
	        if (Collisions.checkPlayer(mob, 1, 0)) {
	            if (GameLogic.player.HitCooldown == 0) {
	                GameLogic.player.Hp -= mob.damage;
	                GameLogic.player.setHitCooldown();
	                System.out.println("Player HP: " + GameLogic.player.Hp);
	            }
	        }
	    }
	}


}
