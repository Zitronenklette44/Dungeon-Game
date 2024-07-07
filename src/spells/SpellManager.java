package spells;

import java.util.ArrayList;

import entitys.MobTemplate;
import game.GameLogic;
import rendering.Draw;
import spells.Fire.Fireball;

public class SpellManager {
	public static ArrayList<SpellTemplate> currentSpells = new ArrayList<SpellTemplate>();
	public static String[] availibleSpell = {"fireball"};
	public static float[] Cooldowns = {0,0,0};
	public static float[] maxCooldowns = {0,0,0};

	//manage Spells
	public static void init(){
		GameLogic.player.equipedSpells[0] = SpellManager.availibleSpell[0];
	}

	public static void fireSpell(MobTemplate mob, int spellNum){
		if(!GameLogic.vertikalAxis) {return;}
		boolean fromPlayer = false;
		if(mob==GameLogic.player) {fromPlayer = true;if(Cooldowns[spellNum] > 0) {return;}}
		switch (mob.equipedSpells[spellNum]){
		case "fireball":{
			createFireball(mob, !fromPlayer);
			break;
		}



		default:
			throw new IllegalArgumentException("Invalid Spell or number: " +mob.equipedSpells[spellNum] +" Num:"+spellNum);
		}

	}


	public static MobTemplate getNearestMobFromEntity(MobTemplate entity) {
		ArrayList<MobTemplate> mobsArrayList = GameLogic.mobs;

		if (mobsArrayList.isEmpty()) {
			return null;
		}

		// Verwendet die Position des aktuellen Objekts
		float currentX = entity.posX;
		float currentY = entity.posY;

		MobTemplate nearestMob = null;
		double nearestDistance = Double.MAX_VALUE;

		// Finde den nächstgelegenen Mob
		for (MobTemplate mob : mobsArrayList) {
			if(mob != entity) {
				double distance = Math.sqrt(Math.pow(mob.posX - currentX, 2) + Math.pow(mob.posY - currentY, 2));
				if (distance < nearestDistance) {
					nearestDistance = distance;
					nearestMob = mob;
				}
			}
		}

		return nearestMob;
	}

	private static int getEquipIndex(String spell){
		try {
			if(GameLogic.player.equipedSpells[0].equals(spell)) {return 0;}
		}catch (NullPointerException e) {}
		try {
			if(GameLogic.player.equipedSpells[1].equals(spell)) {return 1;}
		}catch (NullPointerException e) {}
		try {
			if(GameLogic.player.equipedSpells[2].equals(spell)) {return 2;}
		}catch (NullPointerException e) {}
		
		return -1;
	}





	//create Spells
	public static void createFireball(MobTemplate object, boolean damagePlayer) {
		SpellTemplate tempSpell = new Fireball(object.posX, object.posY, object.lastdx, object.lastdy, damagePlayer);
		tempSpell.originMob = object;
		if(tempSpell.manaConsume>object.mana) {
			Draw.manabarBlink = 0;
			return;
		}
		tempSpell.lastdx = object.lastdx;
		tempSpell.lastdy = object.lastdy;
		object.mana-=tempSpell.manaConsume;
		Cooldowns[getEquipIndex(availibleSpell[0])] = tempSpell.Cooldown;
		maxCooldowns[getEquipIndex(availibleSpell[0])] = tempSpell.Cooldown;
		currentSpells.add(tempSpell);
		tempSpell = null;
	}





}
