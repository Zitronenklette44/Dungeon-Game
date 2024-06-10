package game;

import gameObject.CollisionRechteck;
import gameObject.DeathRechteck;
import gameObject.Rechteck;

public class Collisions {
	GameLogic spieLogic;

	public Collisions(GameLogic spieLogic) {
		this.spieLogic = spieLogic;
	}

	public static boolean checkCollision(Rechteck rect, int deltaX, int deltaY) {
		// Berechne die zukünftige Position des Rechtecks
		int futurePosX = rect.posX + deltaX;
		int futurePosY = rect.posY + deltaY;

		// Überprüfe, ob eine Kollision auftreten würde
		for (CollisionRechteck collisionRect : GameLogic.collisionRectangles) {
			if (futurePosX < collisionRect.posX + collisionRect.breite && futurePosX + rect.breite > collisionRect.posX
					&& futurePosY < collisionRect.posY + collisionRect.hoehe
					&& futurePosY + rect.hoehe > collisionRect.posY) {
				// Kollision gefunden
				return true;
			}
		}
		// Keine Kollision
		return false;
	}

	public static boolean isCollisionAbovePlayer() {
		for (CollisionRechteck collisionRect : GameLogic.collisionRectangles) {
			if (GameLogic.player.posX < collisionRect.posX + collisionRect.breite
					&& GameLogic.player.posX + GameLogic.player.breite > collisionRect.posX
					&& GameLogic.player.posY > collisionRect.posY + collisionRect.hoehe
					&& GameLogic.player.posY + GameLogic.playerVelY <= collisionRect.posY + collisionRect.hoehe) {
				return true;
			}
		}
		return false;
	}

	public static void updateOnGroundStatus() {
		GameLogic.onGround = false;
		if (GameLogic.player.posY >= GameLogic.floor) {
			GameLogic.onGround = true;
			GameLogic.jumpInitialized = false;
			GameLogic.player.posY = GameLogic.floor;
		} else {
			for (CollisionRechteck collisionRect : GameLogic.collisionRectangles) {
				if (GameLogic.player.posX < collisionRect.posX + collisionRect.breite
						&& GameLogic.player.posX + GameLogic.player.breite > collisionRect.posX
						&& GameLogic.player.posY + GameLogic.player.hoehe <= collisionRect.posY && GameLogic.player.posY
								+ GameLogic.player.hoehe + GameLogic.playerVelY >= collisionRect.posY) {
					GameLogic.onGround = true;
					GameLogic.jumpInitialized = false;
					GameLogic.player.posY = collisionRect.posY - GameLogic.player.hoehe;
					GameLogic.playerVelY = 0;
					break;
				}
			}
		}
	}

	public static boolean checkDeathBlock(Rechteck rect, int deltaX, int deltaY) {
		// Berechne die zukünftige Position des Rechtecks
		int futurePosX = rect.posX + deltaX;
		int futurePosY = rect.posY + deltaY;

		// Überprüfe, ob eine Kollision mit einem DeathRechteck auftreten würde
		for (DeathRechteck deathRect : GameLogic.deathRechteck) {
			if (futurePosX < deathRect.posX + deathRect.breite && futurePosX + rect.breite > deathRect.posX
					&& futurePosY < deathRect.posY + deathRect.hoehe && futurePosY + rect.hoehe > deathRect.posY) {
				// Kollision gefunden
				return true;
			}
		}
		// Keine Kollision
		return false;
	}

	public static boolean checkPlayer(Rechteck rect, int deltaX, int deltaY) {
		// Berechne die zukünftige Position des Rechtecks
		int futurePosX = rect.posX + deltaX;
		int futurePosY = rect.posY + deltaY;

		// Überprüfe, ob eine Kollision mit einem DeathRechteck auftreten würde
		if (futurePosX < GameLogic.player.posX + GameLogic.player.breite
				&& futurePosX + rect.breite > GameLogic.player.posX
				&& futurePosY < GameLogic.player.posY + GameLogic.player.hoehe
				&& futurePosY + rect.hoehe > GameLogic.player.posY) {
			// Kollision gefunden
			return true;

		}
		// Keine Kollision
		return false;
	}
}
