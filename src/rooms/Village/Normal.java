package rooms.Village;

import java.util.ArrayList;

import game.GameLogic;
import rooms.RoomTemplate;

public class Normal extends RoomTemplate{
	private String[] image = {"/resources/rooms/backgrounds/VillagePath1.png"}; 
	private ArrayList<Integer> dungeonPaths = new ArrayList<Integer>();
	
	
	public Normal(String name) {
		super(name);
		RoomVariant = (int) (Math.random()*image.length);
		ImagePath = image[RoomVariant];
	}
	
	private void createNewNormal(int currentRoom) {
		dungeonPaths.add(currentRoom);
		dungeonPaths.add((int) (Math.random()*image.length));
		System.out.println("new erstellt");
	}
	
	@Override
	public void createObjects(int CurrentRoom) {	//erstellen der Hitboxen und co abhängig von der Variante
		super.createObjects(CurrentRoom);
		int index = -2;
		for (int i = 0; i < dungeonPaths.size(); i += 2) {	//für jede 2 Stelle in ArrayList
            if (dungeonPaths.get(i) == CurrentRoom) {	//wenn Stelle gleich mit aktuellen Raum
                index = i;	//Raum Existiert
                break;	//beenden der schleife
            }
        }
		if(index == -1) {
			return;
		}
		
		switch (dungeonPaths.get(index+1)) {
			case 0:{
				GameLogic.createHitbox(110,105,590,255);	//höhe breite posX posY
				GameLogic.createHitbox(160,200,365,0);
				GameLogic.createHitbox(80,110,190,0);
				GameLogic.createHitbox(65,220,500,0);
				GameLogic.createHitbox(220,185,60,123);		
				GameLogic.createHitbox(150,105,0,370);
				GameLogic.createHitbox(220,140,0,530);
				GameLogic.createHitbox(220,135,140,590);
				GameLogic.createHitbox(38,100,455,350);
				GameLogic.createHitbox(130,130,455,400);
				GameLogic.createHitbox(150,190,545,625);
				GameLogic.createHitbox(90,105,720,0);
				GameLogic.createHitbox(145,200,1005,0);
				GameLogic.createHitbox(320,111,888,365);	
				GameLogic.createHitbox(175,200,1030,290);
				GameLogic.createHitbox(120,100,1100,685);
				GameLogic.createHitbox(230,40,1000,450);
				GameLogic.createHitbox(250,48,840,430);
				}
				break;
			case 1:
				System.out.println("Case 1");
				break;
				
			case 2:
				break;
		
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + dungeonPaths.get(CurrentRoom+1));
		}
		
		
	}
	
	@Override
	public void setSpawns() {
		super.setSpawns();
		GameLogic.player.breite = 25;
		GameLogic.player.hoehe = 25;
		GameLogic.floor = 750;
		GameLogic.resetPos[0] =130;
		GameLogic.resetPos[1] = 370;
		GameLogic.resetPos1[0]= 1130;
		GameLogic.resetPos1[1] = 200;
		
	}
	
	@Override
	public void VariantExists(int currentRoom) {
        super.VariantExists(currentRoom);
        
        if (dungeonPaths.isEmpty()) {	//Wenn kein Eintrag vorhanden
            createNewNormal(currentRoom);	//neuen Erstellen
            return;		//beenden
        }

        boolean roomExists = false;
        for (int i = 0; i < dungeonPaths.size(); i += 2) {	//für jede 2 Stelle in ArrayList
            if (dungeonPaths.get(i) == currentRoom) {	//wenn Stelle gleich mit aktuellen Raum
                roomExists = true;	//Raum Existiert
                break;	//beenden der schleife
            }
        }
        
        if (!roomExists) {	//wenn kein Raum gefunden
            createNewNormal(currentRoom);	//neuen Raum erstellen
        }
    }

}
