package loot;

import java.util.ArrayList;

import loot.items.ItemTemplate;

public class LootTableTemplate {
	public final int rolls;
	private ItemTemplate[] possibleItems;
	private ArrayList<ItemTemplate> itemPool = new ArrayList<ItemTemplate>();
	public ArrayList<ItemTemplate> lastGeneratedLoot = new ArrayList<ItemTemplate>();
	
	public LootTableTemplate(int rolls, ItemTemplate[] possibleItems ) {
		this.rolls = rolls;
		this.possibleItems = possibleItems;
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
		lastGeneratedLoot.clear();
		for(int i = 0; i< rolls ; i++) {
			ItemTemplate item =itemPool.get((int) (Math.random()*itemPool.size()));
			lastGeneratedLoot.add(item);
		}
	}
	

}
