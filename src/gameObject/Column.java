package gameObject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Column {
    private ArrayList<Rechteck> parts;
    private Color color;
    private Color color1;
    private static int BigX;
    private static int BigY;
    private static int ColumnX;
    private static int ColumnY;
    public static int posX;
    public static int posY;
    

    public Column(int x, int y, int posX, int posY, Color MainFarbe,Color SekundaereFarbe) {
        this.color = MainFarbe;
        this.color1 = SekundaereFarbe;
        parts = new ArrayList<>();
        BigX = x;
        BigY = (int) (y*0.05);
        ColumnY = y-BigY*2;
        ColumnX = (int) (x-BigX*0.2);
        Column.posX = posX;
        Column.posY = posY;
        createColumn();
    }
    
    public Column(int x, int y, int posX, int posY, Color MainFarbe) {
        this.color = MainFarbe;
        this.color1 = MainFarbe;
        parts = new ArrayList<>();
        BigX = x;
        BigY = (int) (y*0.05);
        ColumnY = y-BigY*2;
        ColumnX = (int) (x-BigX*0.2);
        Column.posX = posX;
        Column.posY = posY;
        createColumn();
    }
    
    private void createColumn() {
		//Top
    	Rechteck topRechteck = new Rechteck(BigY, BigX, posX, posY);

    	// Columns
    	int tempWidth = ColumnX / 5; 
    	int startX = posX + (BigX - ColumnX) / 2;

    	Rechteck columRechteck1 = new Rechteck(ColumnY, tempWidth, startX, posY + BigY);
    	Rechteck columRechteck2 = new Rechteck(ColumnY, tempWidth, startX + tempWidth, posY + BigY);
    	Rechteck columRechteck3 = new Rechteck(ColumnY, tempWidth, startX + tempWidth * 2, posY + BigY);
    	Rechteck columRechteck4 = new Rechteck(ColumnY, tempWidth, startX + tempWidth * 3, posY + BigY);
    	Rechteck columRechteck5 = new Rechteck(ColumnY, tempWidth, startX + tempWidth * 4, posY + BigY);
    	
    	//bottom
    	Rechteck bottomRechteck = new Rechteck(BigY, BigX, posX, posY + BigY + ColumnY);
    	
    	parts.add(topRechteck);
    	parts.add(bottomRechteck);
    	parts.add(columRechteck1);
    	parts.add(columRechteck2);
    	parts.add(columRechteck3);
    	parts.add(columRechteck4);
    	parts.add(columRechteck5);
	}

    public void draw(Graphics g) {
        g.setColor(color);
        Rechteck akRech = parts.get(0);
        g.fillRect((int)akRech.posX, (int)akRech.posY, akRech.breite, akRech.hoehe);
        akRech = parts.get(1);
        g.fillRect((int)akRech.posX, (int)akRech.posY, akRech.breite, akRech.hoehe);
        akRech = parts.get(2);
        g.fillRect((int)akRech.posX, (int)akRech.posY, akRech.breite, akRech.hoehe);
        akRech = parts.get(4);
        g.fillRect((int)akRech.posX, (int)akRech.posY, akRech.breite, akRech.hoehe);
        akRech = parts.get(6);
        g.fillRect((int)akRech.posX, (int)akRech.posY, akRech.breite, akRech.hoehe);
        
        g.setColor(color1);
        akRech = parts.get(3);
        g.fillRect((int)akRech.posX,(int) akRech.posY, akRech.breite, akRech.hoehe);
        akRech = parts.get(5);
        g.fillRect((int)akRech.posX,(int) akRech.posY, akRech.breite, akRech.hoehe);;
    }
}










