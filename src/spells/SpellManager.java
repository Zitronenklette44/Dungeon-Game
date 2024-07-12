package spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import action.Logger;
import entitys.MobTemplate;
import game.GameLogic;
import gameObject.CreateObjects;
import rendering.Draw;
import spells.Fire.Fireball;
import spells.Water.WaterSplash;

public class SpellManager {
    public static ArrayList<SpellTemplate> currentSpells = new ArrayList<>();
    public static String[] availableSpells = {"fireball", "waterSplash"};
    public static float[] cooldowns = {0, 0, 0};
    public static float[] maxCooldowns = {0, 0, 0};

    // Objektpool für Fireball und WaterSplash
    private static final int INITIAL_POOL_SIZE = 8;
    private static Map<String, ArrayList<SpellTemplate>> spellPool = new HashMap<>();

    // Initiale Erstellung der Spells
    public static void init() {
    	Logger.logInfo("initializing Spells");
        GameLogic.player.equipedSpells[0] = availableSpells[0];
        GameLogic.player.equipedSpells[1] = availableSpells[1];

        // Objektpool initialisieren
        initializeSpellPool();
        Logger.logInfo("initialized Spells");
    }

    private static void initializeSpellPool() {
    	Logger.logInfo("creating Spell Pool");
        // Fireball Spells initialisieren
        ArrayList<SpellTemplate> fireballPool = new ArrayList<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            Fireball fireball = new Fireball(0, 0, 0, 0, false, "fireball");
            fireballPool.add(fireball);
        }
        spellPool.put(availableSpells[0], fireballPool);

        // WaterSplash Spells initialisieren
        ArrayList<SpellTemplate> waterSplashPool = new ArrayList<>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            WaterSplash waterSplash = new WaterSplash(0, 0, 0, 0, false, "waterSplash");
            waterSplashPool.add(waterSplash);
        }
        spellPool.put(availableSpells[1], waterSplashPool);
        Logger.logInfo("Finished creating Spell Pool");
    }

    // Methode zum Abrufen eines Spells aus dem Objektpool
    private static SpellTemplate acquireSpell(String spellName) {
        ArrayList<SpellTemplate> pool = spellPool.get(spellName);
        if (pool == null || pool.isEmpty()) {
            // Wenn der Pool leer ist oder nicht existiert, erstelle einen neuen Spell
        	Logger.logWarning("Spell Pool couldn't supplie enought Spells a new one was created");
            return createSpellInstance(spellName);
        } else {
            // Nehme den Spell aus dem Pool und entferne ihn aus der Liste
            SpellTemplate spell = pool.remove(pool.size() - 1);
            return spell;
        }
    }

    // Methode zum Freigeben eines Spells in den Objektpool
    private static void releaseSpell(SpellTemplate spell) {        
        ArrayList<SpellTemplate> pool = spellPool.get(spell.spellName);
        if (pool != null) {
            pool.add(spell);
        } else {
            Logger.logError("Spell Pool is null for spell: " + spell.spellName, null);
        }
    }
    
    public static void removeAllSpells() {
    	for(int i = currentSpells.size()-1; i > 0; i--) {
    		releaseSpell(currentSpells.get(i));
    	}
    	currentSpells.clear();
    }
    
    // Methode zum Entfernen von Spells aus dem Spiel und Rückgabe in den Pool
	public static void removeSpell(SpellTemplate spell) {
		try {
			currentSpells.remove(spell);
		} catch (Exception e) {
			Logger.logError("Couldn't return Spell to Pool:" + spell, e);
		}

	}
    
    // Beispiel für die Aktualisierung der Zauber
    public static void updateSpells() {
    	for (int i = 0; i < currentSpells.size(); i++) {
    		SpellTemplate spell = currentSpells.get(i);
    		// Hier ist die Logik, um zu bestimmen, wann ein Spell entfernt werden soll
    		if (spell.checkDelete()) {
    			releaseSpell(spell);
    			removeSpell(spell);
    			i--; // Die Liste wurde verkürzt, also den Index anpassen
    		}
    	}
    }

    // Methode zur Erstellung eines neuen Spell-Objekts
    private static SpellTemplate createSpellInstance(String spellType) {
        switch (spellType) {
            case "fireball":
                return new Fireball(-100, -100, 0, 0, false, "fireball");
            case "waterSplash":
                return new WaterSplash(-100, -100, 0, 0, false, "waterSplash");
            default:
                Logger.logError("invalid SpellType", new IllegalArgumentException());
                return null;
        }
    }

    public static void fireSpell(MobTemplate mob, int spellNum) {
        if (!GameLogic.vertikalAxis) {
            return;
        }
        boolean fromPlayer = false;
        if (mob == GameLogic.player) {
            fromPlayer = true;
            if (cooldowns[spellNum] > 0) {
                return;
            }
        }
        switch (mob.equipedSpells[spellNum]) {
            case "fireball": {
                createFireball(mob, !fromPlayer);
                break;
            }
            case "waterSplash": {
                createWaterSplash(mob, !fromPlayer);
                break;
            }
            default:
            	Logger.logError("Error while fireing a Spell: Invalid Spell or number: " + mob.equipedSpells[spellNum] + " Num:" + spellNum, new IllegalArgumentException());
        }
    }

    public static MobTemplate getNearestMobFromEntity(MobTemplate entity) {
        ArrayList<MobTemplate> mobsArrayList = GameLogic.mobs;

        if (mobsArrayList.isEmpty()) {
        	CreateObjects.createTestMob(1, 1, 0,(int) (entity.posX+500*entity.dx), (int) (entity.posY+500*entity.dy), 0, 1);            
        }

        // Verwendet die Position des aktuellen Objekts
        float currentX = entity.posX;
        float currentY = entity.posY;

        MobTemplate nearestMob = null;
        double nearestDistance = Double.MAX_VALUE;

        // Finde den nächstgelegenen Mob
        for (MobTemplate mob : mobsArrayList) {
            if (mob != entity) {
                double distance = Math.sqrt(Math.pow(mob.posX - currentX, 2) + Math.pow(mob.posY - currentY, 2));
                if (distance < nearestDistance) {
                    nearestDistance = distance;
                    nearestMob = mob;
                }
            }
        }

        return nearestMob;
    }

    private static int getEquipIndex(String spell) {
        try {
            if (GameLogic.player.equipedSpells[0].equals(spell)) {
                return 0;
            }
        } catch (NullPointerException e) {
        }
        try {
            if (GameLogic.player.equipedSpells[1].equals(spell)) {
                return 1;
            }
        } catch (NullPointerException e) {
        }
        try {
            if (GameLogic.player.equipedSpells[2].equals(spell)) {
                return 2;
            }
        } catch (NullPointerException e) {
        }
        
        Logger.logWarning("Spell not equiped");
        return -1;
    }

    //create Spells
    public static void createFireball(MobTemplate object, boolean damagePlayer) {
        Fireball fireball = (Fireball) acquireSpell("fireball");
        fireball.posX = object.posX;
        fireball.posY = object.posY;
        fireball.dx = object.lastdx;
        fireball.dy = object.lastdy;
        fireball.originMob = object;
        fireball.damagePlayer = damagePlayer;
        if (fireball.manaConsume > object.mana) {
            Draw.manabarBlink = 0;
            releaseSpell(fireball);
            return;
        }
        object.mana -= fireball.manaConsume;
        cooldowns[getEquipIndex(availableSpells[0])] = fireball.Cooldown;
        maxCooldowns[getEquipIndex(availableSpells[0])] = fireball.Cooldown;
        currentSpells.add(fireball);
    }

    public static void createWaterSplash(MobTemplate object, boolean damagePlayer) {
        WaterSplash waterSplash = (WaterSplash) acquireSpell("waterSplash");
        waterSplash.posX = object.posX;
        waterSplash.posY = object.posY;
        waterSplash.dx = object.lastdx;
        waterSplash.dy = object.lastdy;
        waterSplash.originMob = object;
        waterSplash.damagePlayer = damagePlayer;
        if (waterSplash.manaConsume > object.mana) {
            Draw.manabarBlink = 0;
            releaseSpell(waterSplash);
            return;
        }
        object.mana -= waterSplash.manaConsume;
        cooldowns[getEquipIndex(availableSpells[1])] = waterSplash.Cooldown;
        maxCooldowns[getEquipIndex(availableSpells[1])] = waterSplash.Cooldown;
        currentSpells.add(waterSplash);
    }

}
