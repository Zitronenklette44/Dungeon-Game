package game;

import entitys.TestMob;

public class Movement {

	static void playerMovement() {
		// Spielerbewegung
	
		if (GameLogic.moveLeft && !Collisions.checkCollision(GameLogic.player, -3, 0) &&!GameLogic.moveRight) {
			GameLogic.player.posX -= 2;
		}
		if (GameLogic.moveRight && !Collisions.checkCollision(GameLogic.player, 3, 0)&&!GameLogic.moveLeft) {
			GameLogic.player.posX += 2;
		}
		if (GameLogic.moveUp && !Collisions.checkCollision(GameLogic.player, 0, -3)&&GameLogic.vertikalAxis&&GameLogic.player.posY>0&&!GameLogic.moveDown) {
			GameLogic.player.posY -= 2;
		}
		if (GameLogic.moveDown && !Collisions.checkCollision(GameLogic.player, 0, 3)&&GameLogic.vertikalAxis&&GameLogic.player.posY<GameLogic.floor&&!GameLogic.moveUp) {
			GameLogic.player.posY += 2;
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

	static void mobMovement(){
	
		//movement mobs
		for (int i = 0; i < GameLogic.mobs.size(); i++) {
			TestMob mob = GameLogic.mobs.get(i);
	
			if (GameLogic.player.posX > mob.posX) {
				mob.dx = 1;
			} else if (GameLogic.player.posX < mob.posX) {
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
				if(GameLogic.player.HitCooldown==0) {
					GameLogic.player.Hp -= mob.damage;
					GameLogic.player.setHitCooldown();
					System.out.println("Player HP: "+ GameLogic.player.Hp);
				}
			}
		}
	}

}
