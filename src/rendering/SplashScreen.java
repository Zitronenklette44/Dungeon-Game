package rendering;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JLabel;

public class SplashScreen extends JLabel {
    private static final long serialVersionUID = 1L;
    public static int currentLoadingState = 0;
    private static int maxLoadingState = 10;

    public SplashScreen() {}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(Color.gray.darker());
        g.fillRect(0, 0, getWidth(), getHeight());    
        
        g.setColor(Color.black);
        g.fillRect(200, 350, 800, 50);
        
        g.setColor(Color.gray);
        int width = (int) ((currentLoadingState / (double) maxLoadingState) * 790);
        g.fillRect(205, 355, width, 40);
    }
}
