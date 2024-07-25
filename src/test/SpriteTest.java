package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SpriteTest extends JPanel {
	
	private BufferedImage spriteSheet;
    private BufferedImage[] sprites;
    private int spriteWidth = 32;  // Breite eines einzelnen Sprites
    private int spriteHeight = 32; // Höhe eines einzelnen Sprites
    private int currentFrame = 0;

    public SpriteTest() {
        try {
            // Bild laden
            spriteSheet = ImageIO.read(SpriteTest.class.getResource("/resources/Entitys/Player/test.png"));
            // Sprites extrahieren
            int rows = 8; // Anzahl der Zeilen im Sprite Sheet
            int cols = 10; // Anzahl der Spalten im Sprite Sheet
            sprites = new BufferedImage[rows * cols];

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    sprites[row * cols + col] = spriteSheet.getSubimage(
                        col * spriteWidth,
                        row * spriteHeight,
                        spriteWidth,
                        spriteHeight
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Timer zum Wechseln der Frames
        Timer timer = new Timer(100, e -> {
            currentFrame = (currentFrame + 1) % sprites.length;
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray);
        g.fillRect(0, 0, getWidth(), getHeight());
        // Zeichnen des aktuellen Sprites
        if (sprites[currentFrame] != null) {
            g.drawImage(sprites[currentFrame], 300, 0,1000,1000, null);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sprite Sheet Example");
        SpriteTest panel = new SpriteTest();
        frame.add(panel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
