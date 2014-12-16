package mayzel.vendingmachine;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {
	private Map<String, Item> map;
	private ArrayList<Item> food;

	public Inventory() {
		map = new HashMap<String, Item>();
		food = new ArrayList<Item>();
	}

	public void load(String inventoryFilename) throws IOException {
		Scanner file = new Scanner(new File(inventoryFilename));
		String temp[] = new String[4];
		String code;
		String name;
		double price;
		int quantity;
		while (file.hasNext()) {
			temp = file.nextLine().split(",");
			code = temp[0];
			name = temp[1];
			price = Double.parseDouble(temp[2]);
			quantity = Integer.parseInt(temp[3]);
			Item item = new Item(code, name, price, quantity);
			food.add(item);
			map.put(code, item);
		}
		file.close();
	}

	/**
	 * 
	 * @param code
	 * @return the item or null if an item with that code doesn't exist
	 */
	public Item get(String code) {
		if (map.containsKey(code)) {
			return map.get(code);
		}
		return null;

	}

	/**
	 * 
	 * @param item
	 *            to add
	 */
	public void add(Item item) {
		map.put(item.getCode(), item);
		food.add(item);
	}

	/**
	 * Removes one from quantity of the specified item
	 * 
	 * @param code
	 */
	public void removeOne(String code) {
		if (map.containsKey(code)) {
			map.get(code).decreaseQuantity();
		}
	}

	/**
	 * 
	 * @param code
	 * @return true if the Item exists and there is at least one quantity,
	 *         otherwise false.
	 */
	public boolean isEmpty(String code) {
		boolean empty = false;
		if (!map.containsKey(code) || map.get(code).getQuantity() <= 0) {
			empty = true;
		}
		return empty;
	}

	/**
	 * Lists the items in the inventory one per line in the format code name @
	 * price x quantity\n
	 */
	public String toString() {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		StringBuilder info = new StringBuilder();
		for (int i = 0; i < food.size(); i++) {
			info.append(food.get(i).getCode());
			info.append(" ");
			info.append(food.get(i).getName());
			info.append(" @ ");
			info.append(formatter.format(food.get(i).getPrice()));
			info.append(" x ");
			info.append(food.get(i).getQuantity());
			info.append("\n");
		}
		return info.toString();
	}

}
