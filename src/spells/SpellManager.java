package spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entitys.MobTemplate;
import game.GameLogic;
import rendering.Draw;
import spells.Fire.Fireball;
import spells.Water.WaterSplash;

public class SpellManager {
    public static ArrayList<SpellTemplate> currentSpells = new ArrayList<>();
    public static String[] availableSpells = {"fireball", "waterSplash"};
    public static float[] cooldowns = {0, 0, 0};
    public static float[] maxCooldowns = {0, 0, 0};

    // Objektpool für Fireball und WaterSplash
    private static final int INITIAL_POOL_SIZE = 4;
    private static Map<String, ArrayList<SpellTemplate>> spellPool = new HashMap<>();

    // Initiale Erstellung der Spells
    public static void init() {
        GameLogic.player.equipedSpells[0] = availableSpells[0];
        GameLogic.player.equipedSpells[1] = availableSpells[1];

        // Objektpool initialisieren
        initializeSpellPool();
    }

    private static void initializeSpellPool() {
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
    }

    // Methode zum Abrufen eines Spells aus dem Objektpool
    private static SpellTemplate acquireSpell(String spellName) {
        ArrayList<SpellTemplate> pool = spellPool.get(spellName);
        if (pool == null || pool.isEmpty()) {
            // Wenn der Pool leer ist oder nicht existiert, erstelle einen neuen Spell
            System.out.println("Pool leer oder nicht vorhanden. Erstelle neuen Zauber: " + spellName);
            return createSpellInstance(spellName);
        } else {
            // Nehme den Spell aus dem Pool und entferne ihn aus der Liste
            SpellTemplate spell = pool.remove(pool.size() - 1);
            System.out.println("Zauber aus dem Pool entnommen: " + spellName+ " spellID:"+spell);
            return spell;
        }
    }

    // Methode zum Freigeben eines Spells in den Objektpool
    private static void releaseSpell(SpellTemplate spell) {
        ArrayList<SpellTemplate> pool = spellPool.get(spell.spellName);
        if (pool != null) {
            pool.add(spell);
            System.out.println("Zauber zurück in den Pool gegeben: " + spell.spellName);
        } else {
            System.out.println("Fehler: Pool für den Zauber nicht gefunden: " + spell.spellName);
        }
    }

    // Methode zur Erstellung eines neuen Spell-Objekts
    private static SpellTemplate createSpellInstance(String spellType) {
        switch (spellType) {
            case "fireball":
                return new Fireball(0, 0, 0, 0, false, "fireball");
            case "waterSplash":
                return new WaterSplash(0, 0, 0, 0, false, "waterSplash");
            default:
                throw new IllegalArgumentException("Unknown spell type: " + spellType);
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
                throw new IllegalArgumentException("Invalid Spell or number: " + mob.equipedSpells[spellNum] + " Num:" + spellNum);
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

    // Methode zum Entfernen von Spells aus dem Spiel und Rückgabe in den Pool
    public static void removeSpell(SpellTemplate spell) {
        if (currentSpells.remove(spell)) {
            releaseSpell(spell);
            System.out.println("Zauber entfernt und in den Pool zurückgegeben: " + spell.Type);
        } else {
            System.out.println("Fehler beim Entfernen des Zaubers aus der Liste: " + spell.Type);
        }
    }

    // Beispiel für die Aktualisierung der Zauber
    public static void updateSpells() {
        for (int i = 0; i < currentSpells.size(); i++) {
            SpellTemplate spell = currentSpells.get(i);
            // Hier ist die Logik, um zu bestimmen, wann ein Spell entfernt werden soll
            if (spell.checkDelete()) {
                removeSpell(spell);
                i--; // Die Liste wurde verkürzt, also den Index anpassen
            }
        }
    }
}
