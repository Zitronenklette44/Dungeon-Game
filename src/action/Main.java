package action;


import gui.ShopPotions;
import gui.ShopTools;
import gui.TestScreen;

public class Main {
    public static void main(String[] args) {
        TestScreen.erstellen();
        ShopPotions.erstellenShopPotions(5);
        ShopTools.erstellenShopTools(5);
    }
}
