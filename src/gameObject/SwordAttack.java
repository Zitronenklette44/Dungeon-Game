package gameObject;

import java.util.ArrayList;

import entitys.MobTemplate;
import game.GameLogic;

public class SwordAttack extends CollisionRechteck{
	public boolean damagePlayer= false;
	public boolean damageMob = false;
	public int damage = 0;
	public int existingTime= 0;
	
	
	public SwordAttack(int reach, float posX, float posY, int height, int damage, int duration) {
        super(reach, height, (int) posX, (int) posY);
        this.existingTime = duration; // Zeit in der der Angriff schaden macht
        this.damage = damage;
    }
	
	public SwordAttack(int reach, float posX, float posY, int height,int damage, int duration, boolean damagePlayer, boolean damageMobs) {
        super(reach, height, (int) posX, (int) posY);
        this.existingTime = duration; // Zeit in der der Angriff schaden macht
        this.damageMob = damageMobs;
        this.damagePlayer = damagePlayer;
        this.damage = damage;
    }
	
	public void dealDamage() {
		if(damageMob) {
		ArrayList<MobTemplate> mobsArrayList = GameLogic.mobs;
	    for (int i = 0; i < mobsArrayList.size(); i++) {
	        MobTemplate mob = mobsArrayList.get(i);	       
	        if (posX < mob.posX + mob.breite
	                && posX + breite > mob.posX
	                && posY < mob.posY + mob.hoehe
	                && posY + hoehe > mob.posY) {
	            // Kollision gefunden
	        	if(mob.HitCooldown == 0 && !mob.invulnerable) {
	        		mob.Hp-= damage;
	        		mob.HitCooldown = mob.maxHitCooldown;
	        		
	        	}
	        }
	    }
	    // Keine Kollision
		}
		if(damagePlayer) {
			// Überprüfe, ob eine Kollision mit dem Spieler auftreten würde
			if (posX < GameLogic.player.posX + GameLogic.player.breite
					&& posX + breite > GameLogic.player.posX
					&& posY < GameLogic.player.posY + GameLogic.player.hoehe
					&& posY + hoehe > GameLogic.player.posY) {
				// Kollision gefunden
				if(GameLogic.player.HitCooldown == 0) {
					GameLogic.player.Hp-= damage;
	        	}

			}
			// Keine Kollision
			
			
			
			
		}

	}
	
	public boolean remove() {
		boolean remove = false;
		if(existingTime <= 0) {
			remove = true;
		}else {
			existingTime--;
		}
		
		return remove;
	}
}
