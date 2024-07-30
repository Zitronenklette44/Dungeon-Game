package loot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import action.Logger;
import loot.items.ItemTemplate;

public class LootTableTemplate {
	public final int rolls;
	public String name;
	private ItemTemplate[] possibleItems;
	private ArrayList<ItemTemplate> itemPool = new ArrayList<ItemTemplate>();
	public ArrayList<ItemTemplate> lastGeneratedLoot = new ArrayList<ItemTemplate>();
	
	public LootTableTemplate(int rolls, ItemTemplate[] possibleItems , String name) {
		this.rolls = rolls;
		this.possibleItems = possibleItems;
		this.name = name;
		initPool();
	}
	
	private void initPool() {
		for(int i = 0; i<possibleItems.length; i++) {
			for(int x = 0; x<possibleItems[i].dropChance; x++) {
				ItemTemplate itemTemplate = possibleItems[i].clone();
				itemPool.add(itemTemplate);
			}
		}
	}
	
	public void generateLoot() {
	    Set<UUID> seenUUIDs = new HashSet<>();
	    lastGeneratedLoot.clear();
	    
	    for (int i = 0; i < rolls; i++) {
	        if (itemPool.size() == 0) {
	            Logger.logWarning("Item pool is empty!");
	            break;
	        }
	        
	        ItemTemplate item = itemPool.get((int) (Math.random() * itemPool.size())).clone();
	        while (seenUUIDs.contains(item.id)) {
	            item = itemPool.get((int) (Math.random() * itemPool.size())).clone();
	            if (itemPool.size() == 0) {
	                break;
	            }
	        }
	        
	        seenUUIDs.add(item.id);
	        lastGeneratedLoot.add(item);
	    }
	}
	
	public ItemTemplate[] getPossibleItems() {
	    HashMap<String, ItemTemplate> uniqueItemsMap = new HashMap<>();

	    for (ItemTemplate item : possibleItems) {
	    	if(item.itemName == "") {
	    		continue;
	    	}
	        String uniqueKey = item.itemName + ":" + item.variant;
	        if (!uniqueItemsMap.containsKey(uniqueKey)) {
	            uniqueItemsMap.put(uniqueKey, item);
	        }
	    }
	    return uniqueItemsMap.values().toArray(new ItemTemplate[0]);
	}
}