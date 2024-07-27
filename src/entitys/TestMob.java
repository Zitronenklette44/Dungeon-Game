package entitys;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestMob extends MobTemplate {
    public TestMob(int hoehe, int breite, int posX, int posY, int dx, int dy, float speed, int SpawnX, int SpawnY, int damage, int Hp) {
		super(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY, damage, Hp,1);
	}
    
    @Override
    public void drawMob(Graphics2D g) {
    	super.drawMob(g);
    	
    	g.setColor(typeColor);
    	g.fillRect((int) posX,(int) posY, breite, hoehe);    
    	g.setColor(Color.black);
    	g.drawRect((int)posX, (int)posY, breite, hoehe);
    }
    
}
