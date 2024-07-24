package inventory;

class Pair<Item, Count> {
	private Item item;
	private Count count;
	
	public Pair(Item item, Count count) {
		this.item = item;
		this.count = count;
	}
	
	public Item getItem() {
		return item;
	}
	
	public Count getCount() {
		return count;
	}
	
	public void setItem(Item item) {
		this.item = item;
	}
	
	public void setCount(Count count) {
		this.count = count;
	}	
}
