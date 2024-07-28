package entitys;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import game.GameLogic;
import rendering.Resources;
import test.SpriteTest;

public class Player extends MobTemplate {
    public int AtkCooldown = 0;
    public int maxAtkCooldown = 300;
    public int itemPickupRange = 20;

    private BufferedImage spriteSheet;
    private BufferedImage[][] sprites;
    private int spriteWidth = 32;  // Breite eines einzelnen Sprites
    private int spriteHeight = 32; // Höhe eines einzelnen Sprites
    private int currentFrame = 0;
    private int frameCounter = 0;
    public int animation = 0;
    private int pendingAnimation = -1;
    
    public char rank = 'X';
    

    public Player(int hoehe, int breite, int posX, int posY, int dx, int dy, float speed, int SpawnX, int SpawnY, int damage, int Hp) {
        super(hoehe, breite, posX, posY, dx, dy, speed, SpawnX, SpawnY, damage, Hp, 100, 1, 0);
        maxHitCooldown = 100;
        reach = 50;
        loadSprites();
    }

    public void setAtkCooldown() {
        this.AtkCooldown = maxAtkCooldown;
    }

    public void setHitCooldown() {
        this.HitCooldown = maxHitCooldown;
    }
    
    public void playAnimation() {
    	if(GameLogic.paused) return;
        BufferedImage[] currentAnimation = sprites[animation];

        if (currentFrame >= currentAnimation.length || currentAnimation[currentFrame] == null) {
            currentFrame = 0;
            if (pendingAnimation != -1) {
                animation = pendingAnimation;
                pendingAnimation = -1;
                currentAnimation = sprites[animation];
            }
        }

        image = currentAnimation[currentFrame];

        frameCounter++;
        if (frameCounter >= 100) { // 100 Frames pro Sprite
            currentFrame++;
            frameCounter = 0;
        }
    }

    public void setAnimation(int newAnimation) {
        if (animation == newAnimation) return; // Wenn die Animation bereits gesetzt ist, nichts tun
        if (currentFrame >= sprites[animation].length || sprites[animation][currentFrame] == null) {
            // Wenn die aktuelle Animation bereits beendet ist, sofort umschalten
            animation = newAnimation;
            currentFrame = 0;
            frameCounter = 0;
        } else {
            // Ansonsten Animation in die Warteschlange stellen
            pendingAnimation = newAnimation;
        }
    }
    
    public void forceSetAnimation(int newAnimation) {
    	if (animation == newAnimation) return; // Wenn die Animation bereits gesetzt ist, nichts tun
    	animation = newAnimation;
    	currentFrame = 0;
    	frameCounter = 0;
    }
    

    private void loadSprites() {
        // Bild laden
		spriteSheet = Resources.playerAnimation;
		// Sprites extrahieren
		int rows = 8; // Anzahl der Zeilen im Sprite Sheet
		int cols = 10; // Anzahl der Spalten im Sprite Sheet
		sprites = new BufferedImage[rows][cols];

		for (int row = 0; row < rows; row++) {
		    for (int col = 0; col < cols; col++) {
		        BufferedImage sprite = spriteSheet.getSubimage(
		            col * spriteWidth,
		            row * spriteHeight,
		            spriteWidth,
		            spriteHeight
		        );
		        if (!isSpriteEmpty(sprite)) {
		            sprites[row][col] = sprite;
		        } else {
		            sprites[row][col] = null;
		        }
		    }
		}
    }

    private boolean isSpriteEmpty(BufferedImage sprite) {
        int width = sprite.getWidth();
        int height = sprite.getHeight();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if ((sprite.getRGB(x, y) & 0xFF000000) != 0x00000000) { // Überprüfen, ob das Pixel nicht komplett transparent ist
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public void drawMob(Graphics2D g) {
    	super.drawMob(g);
    	playAnimation();
    	g.drawImage(image,(int) posX-breite/2,(int) posY-hoehe, breite*2, hoehe*2, null);
    }
}
