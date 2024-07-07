package spells;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import entitys.MobTemplate;

public class SpellTemplate {
	public int manaConsume;
	public int Stage;	// 1-3
	public String Type;	// Fire, Water, Earth
	public boolean damagePlayer;
	public float posX;
	public float posY;
	public float dx;
	public float dy;
	public float lastdx;
	public float lastdy;
	public int breite;
	public int hoehe;
	public int maxHits = -1;
	public int currentHits = 0;
	public int damage;
	public double angle;
	public float speed;
	public MobTemplate originMob;
	private static int animationPos = 0;
	public int Cooldown;

	// Vorabberechnete rotierte Bilder für die Animationen
	private Map<Double, BufferedImage>[] rotatedImages;
	private BufferedImage[] originalImages;

	@SuppressWarnings("unchecked")
	public SpellTemplate(float posX, float posY, float dx, float dy, String Type, int Stage, int damage, int manaConsume, boolean damagePlayer, BufferedImage[] images) {
		this.posX = posX;
		this.posY = posY;
		this.dx = dx;
		this.dy = dy;
		if(Type.equals("fire") || Type.equals("water") || Type.equals("earth")) {
			this.Type = Type;
		} else {
			throw new IllegalArgumentException("Invalid Spelltype: " + Type);
		}
		this.Stage = Stage;
		if(damage >= 5)
			this.damage = damage;
		else
			this.damage = 5;
		this.damagePlayer = damagePlayer;
		this.manaConsume = manaConsume;

		// Initialisieren Sie die Bilder
		this.originalImages = images;
		this.rotatedImages = new HashMap[images.length];
		for (int i = 0; i < images.length; i++) {
			this.rotatedImages[i] = new HashMap<>();
		}
		precomputeRotations();
	}

	private void precomputeRotations() {
		for (int i = 0; i < originalImages.length; i++) {
			for (double angle = 0; angle < 360; angle += 5) {
				rotatedImages[i].put(angle, rotateImage(originalImages[i], angle));
			}
		}
	}

	private BufferedImage rotateImage(BufferedImage image, double angle) {
		double radians = Math.toRadians(angle);
		double sin = Math.abs(Math.sin(radians)), cos = Math.abs(Math.cos(radians));
		int w = image.getWidth(), h = image.getHeight();
		int newWidth = (int) Math.floor(w * cos + h * sin), newHeight = (int) Math.floor(h * cos + w * sin);

		BufferedImage rotated = new BufferedImage(newWidth, newHeight, image.getType());
		Graphics2D g2d = rotated.createGraphics();
		g2d.translate((newWidth - w) / 2, (newHeight - h) / 2);
		g2d.rotate(radians, w / 2, h / 2);
		g2d.drawRenderedImage(image, null);
		g2d.dispose();

		return rotated;
	}

	public void setAngle(double angle) {
		this.angle = (angle % 360 + 360) % 360; // Ensure angle is between 0 and 359
	}

	public BufferedImage getCurrentImage() {
		animationPos++;
		int animationFrame = -1;
		if(animationPos < 20 && animationPos>=0) {
			animationFrame = 0;
		}else if(animationPos < 40 && animationPos>=20) {
			animationFrame = 1;
		}else if(animationPos < 60 && animationPos>=40) {
			animationFrame = 2;
		}else if(animationPos < 80 && animationPos>=60) {
			animationFrame = 3;
		}else if(animationPos < 100 && animationPos>=80) {
			animationFrame = 4;
			animationPos = 0;
		}
		
		double closestAngle = Math.round(angle / 5) * 5;
		return rotatedImages[animationFrame].get(closestAngle);
	}

	public void drawSpell(Graphics2D g, int animationFrame) {
		BufferedImage imageToDraw = getCurrentImage();
		int x = Math.round(posX);
		int y = Math.round(posY);
		g.drawImage(imageToDraw, x, y, null);
	}

	public void drawSpell(Graphics2D g2d) {}
}
