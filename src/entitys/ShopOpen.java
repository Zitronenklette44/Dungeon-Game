package entitys;

public class ShopOpen extends InteractableTemplate{

	public ShopOpen(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, "Shop öffnen");
	}
	
	@Override
	public void performAction() {
		super.performAction();
		gui.Shops.ShopTools.erstellen();
		
	}

}
