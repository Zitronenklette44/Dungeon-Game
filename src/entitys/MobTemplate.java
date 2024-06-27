package entitys;

import java.awt.Color;
import java.awt.Graphics2D;

import gameObject.Rechteck;


public class MobTemplate extends Rechteck{
	public float dx;
	public float dy;
    public int SpawnX;
    public int SpawnY;
    public float speed;
	public int damage;
	public int Hp;
	public boolean defeated= false;
	public boolean hasCollision  = false;
	public Color typeColor;
    
    public MobTemplate(int hoehe, int breite, float posX, float posY, float dx, float dy, float speed, int SpawnX, int SpawnY, int damage ,int Hp) {
        super(hoehe, breite, posX, posY);
        this.dx = dx;
        this.SpawnX = SpawnX;
        this.SpawnY = SpawnY;
        this.speed = speed;
        this.damage = damage;
        this.Hp = Hp;
    
    }
    
    public void drawMob(Graphics2D g) {}
}
