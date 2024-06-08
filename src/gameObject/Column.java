package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Column {
    private ArrayList<Rechteck> parts;
    private Color color;
    private static int BigX;
    private static int BigY;
    private static int ColumnX;
    private static int ColumnY;
    public static int posX;
    public static int posY;
    

    public Column(int x, int y, Color color, int posX, int posY) {
        this.color = color;
        parts = new ArrayList<>();
        BigX = x;
        BigY = (int) (y*0.05);
        ColumnY = y-BigY*2;
        ColumnX = (int) (x-BigX*0.1);
        Column.posX = posX;
        Column.posY = posY;
        createColumn();
    }
    
    private void createColumn() {
		//Top
    	Rechteck topRechteck = new Rechteck(BigY, BigX, posX, posY);
    	//Column
    	Rechteck columRechteck =new Rechteck(ColumnY, ColumnX, posX+(BigX/2-ColumnX/2), posY+BigY);
    	//Bottom
    	Rechteck bottomRechteck =new Rechteck(BigY, BigX, posX, posY+BigY+ColumnY);
    	
    	parts.add(topRechteck);
    	parts.add(columRechteck);
    	parts.add(bottomRechteck);
	}

    public void draw(Graphics g) {
        g.setColor(color);
        for (Rechteck part : parts) {
            g.fillRect(part.posX, part.posY, part.breite, part.hoehe);
        }
    }
}
