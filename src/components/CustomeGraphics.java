package components;

import java.awt.Shape;
import java.awt.geom.GeneralPath;

public class CustomeGraphics {
	public static Shape createRoundedLeftCorners(int x, int y, int width, int height, int arcSize) {
	    GeneralPath path = new GeneralPath();

	    // Startpunkt für das Zeichnen des Rechtecks
	    path.moveTo(x + arcSize, y); // Start an der oberen linken Ecke mit Abrundung

	    // Oben rechts ohne Abrundung
	    path.lineTo(x + width, y);

	    // Rechts unten ohne Abrundung
	    path.lineTo(x + width, y + height);

	    // Unten links ohne Abrundung
	    path.lineTo(x + arcSize, y + height);

	    // Unten links abrunden
	    path.quadTo(x, y + height, x, y + height - arcSize);

	    // Links oben ohne Abrundung
	    path.lineTo(x, y + arcSize);

	    // Oben links abrunden
	    path.quadTo(x, y, x + arcSize, y);

	    // Geschlossenes Rechteck
	    path.closePath();

	    return path;
	}

	
	public static Shape createRoundedRightCorners(int x, int y, int width, int height, int arcSize) {
	    GeneralPath path = new GeneralPath();

	    // Startpunkt für das Zeichnen des Rechtecks
	    path.moveTo(x, y); // Start an der oberen linken Ecke ohne Abrundung

	    // Oben rechts ohne Abrundung
	    path.lineTo(x + width - arcSize, y);

	    // Oben rechts abrunden
	    path.quadTo(x + width, y, x + width, y + arcSize);

	    // Rechts unten ohne Abrundung
	    path.lineTo(x + width, y + height - arcSize);

	    // Unten rechts abrunden
	    path.quadTo(x + width, y + height, x + width - arcSize, y + height);

	    // Unten links ohne Abrundung
	    path.lineTo(x, y + height);

	    // Geschlossenes Rechteck
	    path.closePath();

	    return path;
	}


}
