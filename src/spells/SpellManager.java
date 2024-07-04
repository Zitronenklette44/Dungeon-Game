package spells;

import java.util.ArrayList;

import entitys.MobTemplate;
import game.GameLogic;
import rendering.Draw;
import spells.Fire.Fireball;

public class SpellManager {
	public static ArrayList<SpellTemplate> currentSpells = new ArrayList<SpellTemplate>();
	public static String[] availibleSpell = {"fireball"};
	
	//manage Spells
	public static void init(){
		GameLogic.player.equipedSpells[0] = SpellManager.availibleSpell[0];
	}
	
	public static void fireSpell(MobTemplate mob, int spellNum){
		boolean fromPlayer = false;
		if(mob==GameLogic.player) {fromPlayer = true;}
		switch (mob.equipedSpells[spellNum]){
		case "fireball":{
			createFireball(mob, !fromPlayer);
			break;
		}
			
			
			
		default:
			throw new IllegalArgumentException("Invalid Spell or number: " +mob.equipedSpells[spellNum] +" Num:"+spellNum);
		}
		
	}
	
	
	
	
	
	
	//create Spells
	public static void createFireball(MobTemplate object, boolean damagePlayer) {
		SpellTemplate tempSpell = new Fireball(object.posX, object.posY, object.lastdx, object.lastdy, damagePlayer);
		if(tempSpell.manaConsume>object.mana) {
			Draw.manabarBlink = 0;
			return;
		}
		object.mana-=tempSpell.manaConsume;
		currentSpells.add(tempSpell);
		System.out.println("Fireball");
		tempSpell = null;
	}
	
	
	
	
	
}
