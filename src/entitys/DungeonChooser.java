package entitys;

public class DungeonChooser extends InteractableTemplate{

	public DungeonChooser(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, "Enter Dungeon");
	}
	
	
	@Override
	public void performAction() {
		super.performAction();
		System.out.println("dungeon switch");
	}
}
