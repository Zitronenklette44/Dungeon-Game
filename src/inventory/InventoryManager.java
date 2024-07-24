package inventory;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import action.Logger;
import loot.items.CreateItem;
import loot.items.EmptyItem;
import loot.items.ItemTemplate;

public class InventoryManager {

	private static HashMap<String, Pair<ItemTemplate, Integer>> inventory = new HashMap<String, Pair<ItemTemplate, Integer>>();
	public static int maxInventorySlots = 10;
	private static ItemTemplate empty = new EmptyItem(0);

	public static void init() {
		for (int i = 0; i < 40; i++) {
			inventory.put("Slot" + i, new Pair<>(new EmptyItem(0), 0));
		}
	}

	public static Pair<ItemTemplate, Integer> getSlot(int Slot) {
		return inventory.get("Slot" + Slot);
	}

	public static void setSlot(Pair<ItemTemplate, Integer> item, int Slot) {
		inventory.put("Slot" + Slot, item);
	}

	public static BufferedImage getItemImage(int Slot) {
		return inventory.get("Slot" + Slot).getItem().itemImage;
	}

	public static int getItemCount(int Slot) {
		return inventory.get("Slot" + Slot).getCount();
	}

	public static boolean isSlotEmpty(int Slot) {		//überprüfung ob Slot leer ist
		if (inventory.get("Slot" + Slot).getItem() instanceof EmptyItem) {
			return true;
		}
		return false;
	}

	public static boolean hasItem(ItemTemplate item) {		//überprüfung ob das Item vorhanden ist
		boolean hasItem = false;
		for (int i = 0; i < maxInventorySlots; i++) {
			if (inventory.get("Slot" + i).getItem().getClass() == item.getClass()) {
				hasItem = true;
				break;
			}
		}
		return hasItem;
	}

	public static int getSlot(ItemTemplate item, int count) {		//Slot in dem sich das gesuchte Item befindet 		nicht vorhanden = -1
		for (int i = 0; i < maxInventorySlots; i++) {
			if (inventory.get("Slot" + i).getItem().getClass() == item.getClass()) {
				if (inventory.get("Slot" + i).getCount() <= item.maxStackSize - count) {
					return i;
				}
			}
		}
		return -1;
	}

	public static boolean canStoreItem(ItemTemplate item, int count) {	//überprüfung ob Item gespeichert werden kann
		boolean canStore = false;
		if (hasItem(item) && getSlot(item, count) != -1) {
			canStore = true;
		} else if (hasItem(empty)) {
			canStore = true;
		}
		return canStore;
	}

	public static void add(ItemTemplate item, int count) {		//hinzufügen eines Items
        int slot = getSlot(item, count);
        if (hasItem(item) && slot != -1) {
            Pair<ItemTemplate, Integer> slotPair = inventory.get("Slot" + slot);
            slotPair.setCount(slotPair.getCount() + count);
        } else if (hasItem(empty)) {
            slot = getSlot(empty, count);
            Pair<ItemTemplate, Integer> slotPair = inventory.get("Slot" + slot);
            slotPair.setItem(item);
            slotPair.setCount(count);
        } else {
            Logger.logWarning("Tried adding item to inventory, but inventory is full");
        }
    }
	
	
	public static String createSaveString() {		//erstellen eines Strings mit allen Relevanten Item Daten
	    StringBuilder saveString = new StringBuilder();
	    for (int i = 0; i < maxInventorySlots; i++) {	//Für alle Slots
	        ItemTemplate item = inventory.get("Slot" + i).getItem();	//vereinfachtes Aufrufen
	        int count = inventory.get("Slot" + i).getCount();	//anzahl der Items auf Slot
	        if (!(item instanceof EmptyItem)) {		//wenn kein Leeres item
	            saveString.append(i).append(":").append(item.ItemID).append(":").append(count).append("#");	//hinzufügen von "SlotNR", "ItemID", "Item Anzahl" getrennt durch ":" und abschließen mit einem "#"
	        }
	    }
	    // Entferne das letzte "#" Zeichen, wenn vorhanden
	    if (saveString.length() > 0) {
	        saveString.setLength(saveString.length() - 1);
	    }
	    return saveString.toString();
	}

	public static void loadSaveString(String value) {		//überschreiben des Inventares mit Item Strings
	    String[] items = value.split("#");
	    for (int i = 0; i < items.length; i++) {
	        try {
	            String[] itemInfos = items[i].split(":");
	            if (itemInfos.length != 3) {
	                Logger.logWarning("Invalid item data: " + items[i]);
	                continue;
	            }

	            String slot = "Slot" + itemInfos[0].trim();
	            int itemId = Integer.parseInt(itemInfos[1].trim());
	            int count = Integer.parseInt(itemInfos[2].trim());
	            
	            Logger.logInfo("item: "+items[i]+"  Slot:"+slot+" itemID:"+itemId+" count:"+count);
	            
	            ItemTemplate item = CreateItem.getItembyID(itemId);
	            
	            if(count>item.maxStackSize){count=item.maxStackSize;}

	            inventory.put(slot, new Pair<>(item, count));
	        } catch (NumberFormatException e) {
	            Logger.logError("Failed to parse item data: " + items[i], e);
	        } catch (Exception e) {
	            Logger.logError("Unexpected error while loading item data: " + items[i], e);
	        }
	    }    
	}

	public static void clearInventory() {
		inventory.clear();
		init();
	}

	
	

}
