package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entitys.InteractableTemplate;
import entitys.MobTemplate;
import gameObject.CollisionRechteck;
import gameObject.SwordAttack;
import spells.SpellTemplate;
import gameObject.Rechteck;

public class Collisions {
	GameLogic spieLogic;

	public Collisions(GameLogic spieLogic) {
		this.spieLogic = spieLogic;
	}

	public static boolean checkCollision(Rechteck rect, float deltaX, float deltaY) {
		ArrayList<CollisionRechteck> collisionRechtecks = new ArrayList<>(GameLogic.collisionRectangles);
	    float futurePosX = rect.posX + deltaX;
	    float futurePosY = rect.posY + deltaY;

	    for (CollisionRechteck collisionRect : collisionRechtecks) {
	        if (collisionRect != null) {
	            if (futurePosX < collisionRect.posX + collisionRect.breite 
	                    && futurePosX + rect.breite > collisionRect.posX
	                    && futurePosY < collisionRect.posY + collisionRect.hoehe
	                    && futurePosY + rect.hoehe > collisionRect.posY) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	public static boolean checkCollision(SpellTemplate rect, float deltaX, float deltaY) {
		ArrayList<CollisionRechteck> collisionRechtecks = new ArrayList<>(GameLogic.collisionRectangles);
	    float futurePosX = rect.posX + deltaX;
	    float futurePosY = rect.posY + deltaY;

	    for (CollisionRechteck collisionRect : collisionRechtecks) {
	        if (collisionRect != null) {
	            if (futurePosX < collisionRect.posX + collisionRect.breite 
	                    && futurePosX + rect.breite > collisionRect.posX
	                    && futurePosY < collisionRect.posY + collisionRect.hoehe
	                    && futurePosY + rect.hoehe > collisionRect.posY) {
	                return true;
	            }
	        }
	    }
	    return false;
	}


	public static boolean isCollisionAbovePlayer() {
		ArrayList<CollisionRechteck> collisionRechtecks = new ArrayList<>(GameLogic.collisionRectangles);
		for (CollisionRechteck collisionRect : collisionRechtecks) {
			if(collisionRect != null) {
				if (GameLogic.player.posX < collisionRect.posX + collisionRect.breite
						&& GameLogic.player.posX + GameLogic.player.breite > collisionRect.posX
						&& GameLogic.player.posY > collisionRect.posY + collisionRect.hoehe
						&& GameLogic.player.posY + GameLogic.playerVelY <= collisionRect.posY + collisionRect.hoehe) {
					return true;
				}
			}
		}
		return false;
	}

	public static void updateOnGroundStatus() {
		ArrayList<CollisionRechteck> collisionRechtecks = new ArrayList<>(GameLogic.collisionRectangles);
		GameLogic.onGround = false;
		if (GameLogic.player.posY >= GameLogic.floor) {
			GameLogic.onGround = true;
			GameLogic.jumpInitialized = false;
			GameLogic.player.posY = GameLogic.floor;
		} else {
			for (CollisionRechteck collisionRect : collisionRechtecks) {
				if(collisionRect != null) {
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
	}

	public static boolean checkSwordAttack(Rechteck rect, int deltaX, int deltaY) {
		// Berechne die zukünftige Position des Rechtecks
		float futurePosX = rect.posX + deltaX;
		float futurePosY = rect.posY + deltaY;

		// Überprüfe, ob eine Kollision mit einem DeathRechteck auftreten würde
		for (SwordAttack deathRect : GameLogic.swordAttacks) {
			if (futurePosX < deathRect.posX + deathRect.breite && futurePosX + rect.breite > deathRect.posX
					&& futurePosY < deathRect.posY + deathRect.hoehe && futurePosY + rect.hoehe > deathRect.posY) {
				// Kollision gefunden
				return true;
			}
		}
		// Keine Kollision
		return false;
	}

	public static boolean checkPlayer(Rechteck rect, float deltaX, float deltaY) {
		// Berechne die zukünftige Position des Rechtecks
		float futurePosX = rect.posX + deltaX;
		float futurePosY = rect.posY + deltaY;

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
	
	public static boolean checkPlayer(MobTemplate mob, float deltaX, float deltaY, boolean DealDamage) {
		// Berechne die zukünftige Position des Rechtecks
		float futurePosX = mob.posX + deltaX;
		float futurePosY = mob.posY + deltaY;

		// Überprüfe, ob eine Kollision mit einem DeathRechteck auftreten würde
		if (futurePosX < GameLogic.player.posX + GameLogic.player.breite
				&& futurePosX + mob.breite > GameLogic.player.posX
				&& futurePosY < GameLogic.player.posY + GameLogic.player.hoehe
				&& futurePosY + mob.hoehe > GameLogic.player.posY) {
			// Kollision gefunden
			if(DealDamage && !mob.defeated) {
				GameLogic.player.Hp -= mob.damage;
			}
			
			return true;

		}
		// Keine Kollision
		return false;
	}
	
	public static boolean checkMob(MobTemplate akuellemob, float deltaX, float deltaY) {
	    ArrayList<MobTemplate> mobsArrayList = GameLogic.mobs;
	    // Berechne die zukünftige Position des Rechtecks
	    float futurePosX = akuellemob.posX + deltaX;
	    float futurePosY = akuellemob.posY + deltaY;

	    for (int i = 0; i < mobsArrayList.size(); i++) {
	        MobTemplate mob = mobsArrayList.get(i);
	        
	        // Vermeide Selbst-Kollision
	        if (mob == akuellemob) {
	            continue;
	        }
	       
	        if (futurePosX < mob.posX + mob.breite
	                && futurePosX + akuellemob.breite > mob.posX
	                && futurePosY < mob.posY + mob.hoehe
	                && futurePosY + akuellemob.hoehe > mob.posY) {
	            // Kollision gefunden
	            return true;
	        }
	    }
	    // Keine Kollision
	    return false;
	}
	
	public static boolean checkMob(MobTemplate akuellemob, float deltaX, float deltaY, boolean DealDamage) {
	    ArrayList<MobTemplate> mobsArrayList = GameLogic.mobs;
	    // Berechne die zukünftige Position des Rechtecks
	    float futurePosX = akuellemob.posX + deltaX;
	    float futurePosY = akuellemob.posY + deltaY;

	    for (int i = 0; i < mobsArrayList.size(); i++) {
	        MobTemplate mob = mobsArrayList.get(i);
	        
	        // Vermeide Selbst-Kollision
	        if (mob == akuellemob) {
	            continue;
	        }
	       
	        if (futurePosX < mob.posX + mob.breite
	                && futurePosX + akuellemob.breite > mob.posX
	                && futurePosY < mob.posY + mob.hoehe
	                && futurePosY + akuellemob.hoehe > mob.posY) {
	            // Kollision gefunden
	        	if(DealDamage) {
					mob.Hp -= akuellemob.damage;
				}
	            return true;
	        }
	    }
	    // Keine Kollision
	    return false;
	}
	
	public static boolean checkMob(SpellTemplate akuellerSpell, float deltaX, float deltaY, boolean DealDamage) {
	    ArrayList<MobTemplate> mobsArrayList = GameLogic.mobs;
	    // Berechne die zukünftige Position des Rechtecks
	    float futurePosX = akuellerSpell.posX + deltaX;
	    float futurePosY = akuellerSpell.posY + deltaY;

	    for (int i = 0; i < mobsArrayList.size(); i++) {
	        MobTemplate mob = mobsArrayList.get(i);
	       
	        if (futurePosX < mob.posX + mob.breite
	                && futurePosX + akuellerSpell.breite > mob.posX
	                && futurePosY < mob.posY + mob.hoehe
	                && futurePosY + akuellerSpell.hoehe > mob.posY) {
	            // Kollision gefunden
	        	if(DealDamage) {
					mob.Hp -= akuellerSpell.damage;
				}
	            return true;
	        }
	    }
	    // Keine Kollision
	    return false;
	}

	public static void checkInteractable(Graphics2D g, Color color) {
		for (InteractableTemplate interactable : GameLogic.interactables) {
			float playerCenterX = GameLogic.player.posX + GameLogic.player.breite / 2;
			float playerCenterY = GameLogic.player.posY + GameLogic.player.hoehe / 2;

			float interactableCenterX = interactable.posX + interactable.breite / 2;
			float interactableCenterY = interactable.posY + interactable.hoehe / 2;

			float dx = playerCenterX - interactableCenterX;
			float dy = playerCenterY - interactableCenterY;
			float distanceSquared = dx * dx + dy * dy;
			int rangeSquared = interactable.range * interactable.range;

			if (distanceSquared <= rangeSquared) {
				interactable.actionEnabled = true;
				g.setColor(color);
				g.drawString("[E] "+interactable.interactionString, interactable.posX, interactable.posY-10);

			}else {
				interactable.actionEnabled = false;
			}
		}
	}


}
