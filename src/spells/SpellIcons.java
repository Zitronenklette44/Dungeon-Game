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
	
	public static BufferedImage getBufferedImageBySpell(String spell) {
		if(spell == null || spell.isEmpty()) {
			return null;
		}
		switch (spell) {
		case "fireball": return fireball;
		case "waterSplash": return waterSplash;
		case "stoneShot": return stoneShot;
		
		default:
			Logger.logError("Unknown Spell: " + spell, new IllegalArgumentException());
		}
		return null;

	}
	
	public static String getSpellNameByBufferedImage(BufferedImage image) {
        if (image == null) {
            return "";
        }
        if (image == fireball) {
            return "fireball";
        } else if (image == waterSplash) {
            return "waterSplash";
        } else if (image == stoneShot) {
            return "stoneShot";
        } else {
            Logger.logError("Unknown Image: " + image, new IllegalArgumentException());
        }
		return "";
    }
	
	

}
