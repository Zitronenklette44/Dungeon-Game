package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entitys.MobTemplate;
import functionalObjects.FunctionalTemplate;
import gameObject.CollisionRechteck;
import gameObject.SwordAttack;
import interactions.InteractableTemplate;
import loot.items.ItemTemplate;
import spells.SpellTemplate;
import gameObject.Rechteck;

public class Collisions {
	GameLogic spieLogic;

	public Collisions(GameLogic spieLogic) {
		this.spieLogic = spieLogic;
	}

	public static boolean checkCollision(Rechteck rect, float deltaX, float deltaY) {		//überprüfung Kollisionen von Mob mit CollisionRechtecken wie z.B. Hitboxen
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
	public static boolean checkCollision(SpellTemplate rect, float deltaX, float deltaY) {		//überprüfung Kollisionen von Zauber mit CollisionRechtecken wie z.B. Hitboxen
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


	public static boolean isCollisionAbovePlayer() {	//wenn Kollision über Spieler vorhanden ist
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

	public static void updateOnGroundStatus() {		//Update des Statuses on Ground
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

	public static boolean checkSwordAttack(Rechteck rect, int deltaX, int deltaY) {		//wenn in Schwert angriff
		// Berechne die zukünftige Position des Rechtecks
		float futurePosX = rect.posX + deltaX;
		float futurePosY = rect.posY + deltaY;

		// Überprüfe, ob eine Kollision mit einem Angriff auftreten würde
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

	public static boolean checkPlayer(Rechteck rect, float deltaX, float deltaY) {		//überprüfe Kollision mit Spieler kein Schaden
		// Berechne die zukünftige Position des Rechtecks
		float futurePosX = rect.posX + deltaX;
		float futurePosY = rect.posY + deltaY;

		// Überprüfe, ob eine Kollision mit dem Spieler auftreten würde
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
	
	public static boolean checkPlayer(MobTemplate mob, float deltaX, float deltaY, boolean DealDamage) {	//überprüfe Kollision mit Spieler + Schaden
		// Berechne die zukünftige Position des Rechtecks
		float futurePosX = mob.posX + deltaX;
		float futurePosY = mob.posY + deltaY;

		// Überprüfe, ob eine Kollision mit dem Spieler auftreten würde
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
	
	public static boolean checkMob(MobTemplate akuellemob, float deltaX, float deltaY) {	//überprüfen nach Kollision mit Mob kein Schaden
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
	
	public static boolean checkMob(MobTemplate akuellemob, float deltaX, float deltaY, boolean DealDamage) {	//überprüfen nach Kollision mit Mob + Schaden
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
	        	if(DealDamage && mob.HitCooldown==0 && !mob.invulnerable) {
					mob.Hp -= akuellemob.damage;
					mob.HitCooldown = akuellemob.maxHitCooldown;
				}
	            return true;
	        }
	    }
	    // Keine Kollision
	    return false;
	}
	
	public static boolean checkMob(SpellTemplate akuellerSpell, float deltaX, float deltaY, boolean DealDamage) {	//überprüfen nach Kollision mit Mob + Schaden
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
	        	if(DealDamage && mob.HitCooldown==0 && !mob.invulnerable) {
					mob.Hp -= akuellerSpell.damage;
					mob.HitCooldown = mob.maxHitCooldown;
				}
	            return true;
	        }
	    }
	    // Keine Kollision
	    return false;
	}

	public static void checkInteractable(Graphics2D g, Color color) {		//Überprüfen von möglichen Interactionen in Bereich um Spieler
		ArrayList<InteractableTemplate> temp = new ArrayList<InteractableTemplate>(GameLogic.interactables);
		for (InteractableTemplate interactable : temp) {
			interactable.update();
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
	
	public static void checkFunctionable(Graphics2D g, Color color) {		//Überprüfen von möglichen Interactionen in Bereich um Spieler
		for (FunctionalTemplate interactable : GameLogic.functionalObjects) {
			interactable.update();
			float playerCenterX = GameLogic.player.posX + GameLogic.player.breite / 2;
			float playerCenterY = GameLogic.player.posY + GameLogic.player.hoehe / 2;

			float interactableCenterX = interactable.posX + interactable.breite / 2;
			float interactableCenterY = interactable.posY + interactable.hoehe / 2;

			float dx = playerCenterX - interactableCenterX;
			float dy = playerCenterY - interactableCenterY;
			float distanceSquared = dx * dx + dy * dy;
			int rangeSquared = interactable.range * interactable.range;

			if (distanceSquared <= rangeSquared && interactable.isExisting) {
				interactable.actionEnabled = true;
				g.setColor(color);
				g.drawString("[E] "+interactable.interactionString, interactable.posX, interactable.posY-10);

			}else {
				interactable.actionEnabled = false;
			}
		}
	}
	
	public static void checkItems() {        
	    ArrayList<ItemTemplate> itemsList = new ArrayList<>(GameLogic.items); // Kopieren der Liste
	    for (ItemTemplate items : itemsList) {
	        float playerCenterX = GameLogic.player.posX + GameLogic.player.breite / 2;
	        float playerCenterY = GameLogic.player.posY + GameLogic.player.hoehe / 2;

	        float itemCenterX = items.posX + items.itemImage.getWidth() / 2;
	        float itemCenterY = items.posY + items.itemImage.getHeight() / 2;

	        float dx = playerCenterX - itemCenterX;
	        float dy = playerCenterY - itemCenterY;
	        float distanceSquared = dx * dx + dy * dy;
	        int rangeSquared = GameLogic.player.itemPickupRange * GameLogic.player.itemPickupRange;

	        if (distanceSquared <= rangeSquared) {
	            items.pickedUp();
	            return;
	        }
	    }
	}



}
