package entitys;

import gameObject.Rechteck;

public class MobTemplate extends Rechteck{
	public int dx;
	public int dy;
    public int SpawnX;
    public int SpawnY;
    public int speed;
	public int damage;
	public int Hp;
    
    public MobTemplate(int hoehe, int breite, int posX, int posY, int dx, int dy, int speed, int SpawnX, int SpawnY) {
        super(hoehe, breite, posX, posY);
        this.dx = dx;
        this.SpawnX = SpawnX;
        this.SpawnY = SpawnY;
        this.speed = speed;
    
    }
}
