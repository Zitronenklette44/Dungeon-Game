package spells.Water;


import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entitys.MobTemplate;
import game.Collisions;
import game.GameLogic;
import spells.SpellManager;
import spells.SpellTemplate;

public class WaterSplash extends SpellTemplate{
	

	public WaterSplash(float posX, float posY, float dx, float dy, boolean damagePlayer, String spellName) {
		super(posX, posY, dx, dy, "water", 1,(int) ((double)(GameLogic.player.damage/100)*20), 15, damagePlayer, loadImages(), spellName);	
		breite = 25;
		hoehe = 25;
		speed =  0.8F;
		Cooldown = 10;	
		}
	
	private static BufferedImage[] loadImages() {
		try {
			ClassLoader classLoader = WaterSplash.class.getClassLoader();
			 BufferedImage[] images = {
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Water/waterSplash/watersplash1.png")),
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Water/waterSplash/watersplash1.png")),
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Water/waterSplash/watersplash1.png")),
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Water/waterSplash/watersplash1.png")),
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Water/waterSplash/watersplash1.png")),
		            };
		            return images;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public void rotateToNearestMob() {
		MobTemplate nearestMob = SpellManager.getNearestMobFromEntity(originMob);
	    if (nearestMob != null) {
	        // Berechne die Richtung zum nächstgelegenen Mob
	        float directionX = nearestMob.posX - this.posX;
	        float directionY = nearestMob.posY - this.posY;
	        
	        // Berechne die Länge des Richtungsvektors
	        double length = Math.sqrt(directionX * directionX + directionY * directionY);

	        if (length != 0) { // Verhindere Division durch Null
	            // Normalisiere den Vektor (um die Richtung zu bekommen)
	            double normalizedX = directionX / length;
	            double normalizedY = directionY / length;

	            // Setze die Bewegungsrichtung basierend auf der normierten Richtung und der Geschwindigkeit
	            this.dx = (float) (normalizedX * this.speed);
	            this.dy = (float) (normalizedY * this.speed);

	            // Berechne den Rotationswinkel
	            this.angle = Math.atan2(directionY, directionX);
	        } else {
	        	this.dx = (float) (Math.cos(this.angle) * this.speed);
	            this.dy = (float) (Math.sin(this.angle) * this.speed);
	        }
	    } else {
	    	 // Berechne die Richtung zur letzen Position
	        float directionX = this.lastdx;
	        float directionY = this.lastdy;
	        
	        // Berechne die Länge des Richtungsvektors
	        double length = Math.sqrt(directionX * directionX + directionY * directionY);

	        if (length != 0) { // Verhindere Division durch Null
	            // Normalisiere den Vektor (um die Richtung zu bekommen)
	            double normalizedX = directionX / length;
	            double normalizedY = directionY / length;

	            // Setze die Bewegungsrichtung basierend auf der normierten Richtung und der Geschwindigkeit
	            this.dx = (float) (normalizedX * this.speed);
	            this.dy = (float) (normalizedY * this.speed);

	            // Berechne den Rotationswinkel
	            this.angle = Math.atan2(directionY, directionX);
	        } else {
	        	this.dx = (float) (Math.cos(this.angle) * this.speed);
	            this.dy = (float) (Math.sin(this.angle) * this.speed);
	        }
	    }
	}

	@Override
	public void drawSpell(Graphics2D g2d) {
	    //checkDelete();

	    // Setze die alte Transformation zurück
	    AffineTransform oldTransform = g2d.getTransform();

	    // Berechne das Zentrum der Kugel
//	    float centerX = this.posX + this.breite / 2;
//	    float centerY = this.posY + this.hoehe / 2;

	    // Berechne den Endpunkt der Linie basierend auf der maximalen Länge
	    int maxLength = 50; // Maximale Länge des Wasserstrahls in Pixel
	    int endX = (int) (posX + dx * maxLength);
	    int endY = (int) (posY + dy * maxLength);

	    // Zeichne den Strahl als Linie
	    g2d.setStroke(new BasicStroke(10f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	    TexturePaint paint = new TexturePaint(getCurrentImage(), new Rectangle(75, 75));
		g2d.setPaint(paint);
	    g2d.drawLine((int) posX, (int) posY, endX, endY);

	    // Setze die alte Transformation zurück
	    g2d.setTransform(oldTransform);

	    // Aktualisiere die Position der Kugel
	    this.posX += this.dx * speed;
	    this.posY += this.dy * speed;
	}



	@Override
	public boolean checkDelete() {
		boolean delete = false;
		if(posX >= 1200 || posX < 0) {
			delete = true;
		}
		if(posY >= 750 || posY <0) {
			delete = true;
		}
		//if(range <= 0) {
			//delete = true;
		//}

		if(Collisions.checkCollision(this, this.dx+1, this.dy+1)||Collisions.checkMob(this, this.dx, this.dy, true)) {
			//delete = true;
		}

		return delete;
	}
	
}

