package entitys.village;

import java.awt.Color;
import java.awt.Graphics2D;

import entitys.MobTemplate;
import loot.LootTableTemplate;

public class Swordmen extends MobTemplate{

	public Swordmen(int hoehe, int breite, float posX, float posY, float dx, float dy, float speed, int SpawnX,
			int SpawnY, int damage, int Hp) {
		super(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY, damage, Hp);
	}
	
	public Swordmen(int hoehe, int breite, float posX, float posY, float dx, float dy, float speed, int SpawnX,
			int SpawnY, int damage, int Hp, LootTableTemplate loot) {
		super(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY, damage, Hp, loot);
	}
	
	@Override
	public void drawMob(Graphics2D g) {
		super.drawMob(g);
		
		g.setColor(Color.black);
    	g.fillRect((int) posX,(int) posY, breite, hoehe);    
    	g.setColor(Color.black);
    	g.drawRect((int)posX, (int)posY, breite, hoehe);
	}

}
