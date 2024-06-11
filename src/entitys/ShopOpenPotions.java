package entitys;

public class ShopOpenPotions extends InteractableTemplate{

	public ShopOpenPotions(int hoehe, int breite, int posX, int posY) {
		super(hoehe, breite, posX, posY, 100, "Shop für Potions öffnen");
	}
	
	@Override
	public void performAction() {
		super.performAction();
		gui.Shops.ShopPotions.erstellen();
		
	}
	
	
	
}
