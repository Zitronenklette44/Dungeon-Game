package spells;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import action.Logger;

public class SpellIcons {
	//fire
	public static BufferedImage fireball;
	
	//water
	public static BufferedImage waterSplash;
	
	//earth
	public static BufferedImage stoneShot;
	
	public static void init(){
		Logger.logInfo("loading Spell Icons");
		try {
			//fire
			fireball = ImageIO.read(SpellIcons.class.getResource("/resources/spells/Fire/fireball/fireball.png"));	
			
			
			//water
			waterSplash = ImageIO.read(SpellIcons.class.getResource("/resources/spells/Water/waterSplash/waterSplash.png"));
			
			
			//earth
			stoneShot = ImageIO.read(SpellIcons.class.getResource("/resources/spells/Fire/fireball/fireball.png"));
		} catch (IOException e) {
			Logger.logError("Loading Spell Images failed", e);
		}
		Logger.logInfo("finished loading Spell Icons");
	}
	
	public static BufferedImage getIconBySpell(String spell) {
		if(spell == null) {
			return null;
		}
		switch (spell) {
		case "fireball": return fireball;
		case "waterSplash": return waterSplash;
		case "stoneShot": return stoneShot;
		
		default:
			throw new IllegalArgumentException("Unknown Spell: " + spell);
		}

	}
	
	

}
