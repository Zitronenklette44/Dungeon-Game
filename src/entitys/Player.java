package entitys;

public class Player extends MobTemplate {
	public int AtkCooldown = 0;
	public int maxAtkCooldown = 300;

	public Player(int hoehe, int breite, int posX, int posY, int dx, int dy, float speed, int SpawnX, int SpawnY,	int damage, int Hp) {
		super(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY, damage, Hp);
		maxHitCooldown = 100;
		reach = 50;
		maxMana = 100;
		mana = maxMana;
	}
	
	public void setAtkCooldown() {
		this.AtkCooldown = maxAtkCooldown;
	}
	
	public void setHitCooldown() {
		this.HitCooldown = maxHitCooldown;
	}

}
