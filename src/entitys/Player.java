package entitys;

public class Player extends MobTemplate {
	public int AtkCooldown = 0;
	public int HitCooldown = 0;
	public Player(int hoehe, int breite, int posX, int posY, int dx, int dy, int speed, int SpawnX, int SpawnY,	int damage, int Hp) {
		super(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY, damage, Hp);
	}
	
	public void setAtkCooldown() {
		this.AtkCooldown = 1000;
	}
	
	public void setHitCooldown() {
		this.HitCooldown = 1000;
	}

}
