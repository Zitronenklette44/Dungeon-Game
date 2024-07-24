package inventory;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import action.Logger;
import loot.items.EmptyItem;
import loot.items.ItemTemplate;
import loot.items.coins.CopperCoin;

public class InventoryManager {

	private static HashMap<String, Pair<ItemTemplate, Integer>> inventory = new HashMap<String, Pair<ItemTemplate,Integer>>();
	public static int maxInventorySlots = 10;

	public static void init(){
		for (int i = 0; i < 40; i++) {
			inventory.put("Slot" + i, new Pair<>(new EmptyItem(0), 0));
		}

		inventory.put("Slot0", new Pair<>(new CopperCoin(0), 1));
//		inventory.put("Slot5", new Pair<>(new CopperCoin(0), 10));
//		inventory.put("Slot10", new Pair<>(new CopperCoin(0), 100));
//		inventory.put("Slot15", new Pair<>(new CopperCoin(0), 1000));
//		inventory.put("Slot20", new Pair<>(new CopperCoin(0), 10000));
	}


	public static Pair<ItemTemplate, Integer> getSlot(int Slot) {
		return inventory.get("Slot"+Slot);
	}

	public static void setSlot(Pair<ItemTemplate, Integer> item, int Slot) {
		inventory.put("Slot"+Slot,item);
	}

	public static BufferedImage getItemImage(int Slot) {
		return inventory.get("Slot"+Slot).getItem().itemImage;
	}

	public static int getItemCount(int Slot) {
		return inventory.get("Slot"+Slot).getCount();
	}

	public static boolean isSlotEmpty(int Slot) {
		if(inventory.get("Slot"+Slot).getItem() instanceof EmptyItem) {
			return true;
		}
		return false;
	}
	
	public static boolean hasItem(ItemTemplate item) {
		boolean hasItem = false;
		for (int i = 0; i < maxInventorySlots; i++) {
			if(inventory.get("Slot"+i).getItem().getClass() == item.getClass()) {
				hasItem = true;
				break;
			}
		}
		return hasItem;
	}
	
	public static int getSlot(ItemTemplate item, int count) {
		for (int i = 0; i < maxInventorySlots; i++) {
			if(inventory.get("Slot"+i).getItem().getClass() == item.getClass()) {
				if(inventory.get("Slot"+i).getCount()<100-count) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public static void add(ItemTemplate item, int count){
		ItemTemplate empty = new EmptyItem(0);
		if(hasItem(item) && getSlot(item, count) != -1) {inventory.get("Slot"+getSlot(item, count)).setCount(inventory.get("Slot"+getSlot(item, count)).getCount()+count);}	//füge Item entsprechenden Slot hinzu
		else if(hasItem(empty)) {inventory.get("Slot"+getSlot(empty, count)).setItem(item);inventory.get("Slot"+getSlot(item, count)).setCount(count);}
		else {Logger.logWarning("tried adding Item to inventory but inventory is Full");}
	}


}
