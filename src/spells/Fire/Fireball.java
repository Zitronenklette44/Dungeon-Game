package spells.Fire;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import entitys.MobTemplate;
import game.Collisions;
import game.GameLogic;
import spells.SpellManager;
import spells.SpellTemplate;

public class Fireball extends SpellTemplate{
	

	public Fireball(float posX, float posY, float dx, float dy, boolean damagePlayer, String spellName) {
		super(posX, posY, dx, dy, "fire", 1,(int) ((double)(GameLogic.player.damage/100)*20), 10, damagePlayer, loadImages(), spellName);
		breite = 25;
		hoehe = 25;
		speed =  0.8F;
		Cooldown = 10;
		System.out.println("fire created");
	}
	
	private static BufferedImage[] loadImages() {
		try {
			ClassLoader classLoader = Fireball.class.getClassLoader();
			 BufferedImage[] images = {
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Fire/fireball/fireball1.png")),
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Fire/fireball/fireball2.png")),
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Fire/fireball/fireball3.png")),
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Fire/fireball/fireball4.png")),
		                ImageIO.read(classLoader.getResourceAsStream("resources/spells/Fire/fireball/fireball5.png"))
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
	            angle = Math.atan2(directionY, directionX);
	        } else {
	            this.dx = this.lastdx;
	            this.dy = this.lastdy;
	        }
	    } else {
	    	 // Berechne die Richtung zum nächstgelegenen Mob
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
	            angle = Math.atan2(directionY, directionX);
	        } else {
	            this.dx = this.lastdx;
	            this.dy = this.lastdy;
	        }
	    }
	}

	@Override
	public void drawSpell(Graphics2D g2d) {
		//checkDelete();
		rotateToNearestMob();
		// Setze die alte Transformation zurück
		AffineTransform oldTransform = g2d.getTransform();

		// Berechne das Zentrum der Kugel
		float centerX = this.posX + this.breite / 2;
		float centerY = this.posY + this.hoehe / 2;

		// Setze die Rotation um das Zentrum der Kugel
		g2d.rotate(this.angle, centerX, centerY);

		// Zeichne die Kugel als Rechteck
		//g2d.fillRect((int)this.posX, (int)this.posY, 20, 40);
		g2d.drawImage(this.getCurrentImage(), (int)this.posX, (int)this.posY, null, null);

		// Setze die alte Transformation zurück
		g2d.setTransform(oldTransform);

		// Aktualisiere die Position der Kugel
		this.posX += this.dx*speed;
		this.posY += this.dy*speed;
		//this.range -= Math.sqrt(this.dx * this.dx + this.dy * this.dy);
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
			delete = true;
		}

		return delete;
	}
	
}

