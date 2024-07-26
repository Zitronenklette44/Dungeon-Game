package entitys;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import action.Logger;
import gameObject.Rechteck;
import loot.LootTableTemplate;
import loot.SpawnLoot;


public class MobTemplate extends Rechteck{
	public float dx;
	public float dy;
	public float lastdx;
	public float lastdy;
    public int SpawnX;
    public int SpawnY;
    public float speed;
	public int damage;
	public int Hp;
	public int maxHp;
	public boolean defeated= false;
	public boolean hasCollision  = false;
	public Color typeColor;
	public int maxHitCooldown;
	public int HitCooldown;
	public int reach;
	public int maxMana;
	public int mana = maxMana;
	public int restoreMana;
	public String[] equipedSpells = {"","",""};
	public LootTableTemplate loot;
	public boolean spawnedLootTable = false;
	public boolean isSlowed = false;
	public boolean isStuned = false;
	public boolean invulnerable = false;
	public BufferedImage image;
    
    public MobTemplate(int hoehe, int breite, float posX, float posY, float dx, float dy, float speed, int SpawnX, int SpawnY, int damage ,int Hp, int MaxMana, int restoreMana) {
        super(hoehe, breite, posX, posY);
        this.dx = dx;
        this.SpawnX = SpawnX;
        this.SpawnY = SpawnY;
        this.speed = speed;
        this.damage = damage;
        this.Hp = Hp;
        this.maxHp = Hp;
        this.maxMana = MaxMana;
        mana = maxMana;
        this.restoreMana = restoreMana;
    }
    
    public MobTemplate(int hoehe, int breite, float posX, float posY, float dx, float dy, float speed, int SpawnX, int SpawnY, int damage ,int Hp) {
        super(hoehe, breite, posX, posY);
        this.dx = dx;
        this.SpawnX = SpawnX;
        this.SpawnY = SpawnY;
        this.speed = speed;
        this.damage = damage;
        this.Hp = Hp;
        this.maxHp = Hp;
        this.maxMana = 0;
        mana = maxMana;
        this.restoreMana = 0;
    }
    
    public MobTemplate(int hoehe, int breite, float posX, float posY, float dx, float dy, float speed, int SpawnX, int SpawnY, int damage ,int Hp, int MaxMana, int restoreMana, LootTableTemplate loot) {
        this(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY, damage, Hp, MaxMana, restoreMana);
        this.loot = loot;
    }
    
    public MobTemplate(int hoehe, int breite, float posX, float posY, float dx, float dy, float speed, int SpawnX, int SpawnY, int damage ,int Hp, LootTableTemplate loot) {
        this(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY, damage, Hp);
        this.loot = loot;
    }
    
    public void drawMob(Graphics2D g) {}
    
    public void setHitCooldown() {
		this.HitCooldown = maxHitCooldown;
	}
    
    public void onDeath() {
    	if(!defeated || spawnedLootTable || loot == null) {return;}
    	SpawnLoot.around(posX, posY, 20, loot);
    	spawnedLootTable = true;
    	
    }
    
    public void resetPos() {
        this.posX = SpawnX;
        this.posY = SpawnY;
    }
}
