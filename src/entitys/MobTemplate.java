package entitys;

import java.awt.Color;
import java.awt.Graphics2D;

import gameObject.Rechteck;


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
	public boolean isSlowed = false;
	public boolean isStuned = false;
    
    public MobTemplate(int hoehe, int breite, float posX, float posY, float dx, float dy, float speed, int SpawnX, int SpawnY, int damage ,int Hp) {
        super(hoehe, breite, posX, posY);
        this.dx = dx;
        this.SpawnX = SpawnX;
        this.SpawnY = SpawnY;
        this.speed = speed;
        this.damage = damage;
        this.Hp = Hp;
        this.maxHp = Hp;
    }
    
    public void drawMob(Graphics2D g) {}
    
    public void setHitCooldown() {
		this.HitCooldown = maxHitCooldown;
	}
}
