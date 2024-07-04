package spells;

import java.awt.Graphics2D;

public class SpellTemplate {
	public int manaConsume;
	public int Stage;	//1-3
	public String Type;	//Fire, Water, Earth
	public boolean damagePlayer;
	public float posX;
	public float posY;
	public float dx;
	public float dy;
	public int breite;
	public int hoehe;
	public int maxHits = -1;
	public int currentHits = 0;
	public int damage;
	public double angle;
	public float speed;
	
	public SpellTemplate(float posX, float posY, float dx, float dy, String Type, int Stage, int damage, int manaConsume, boolean damagePlayer) {
		this.posX = posX;
		this.posY=posY;
		this.dx = dx;
		this.dy = dy;
		if(Type.equals("fire")|| Type.equals("water")||Type.equals("earth")) { this.Type = Type;} else {throw new IllegalArgumentException("Invallid Spelltype: "+ Type);}
		this.Stage = Stage;
		if(damage>=5)
			this.damage = damage;
		else
			this.damage = 5;
		this.damagePlayer = damagePlayer;
		this.manaConsume = manaConsume;
	}
	
	public void drawSpell(Graphics2D g) {}
	
	
	
}
