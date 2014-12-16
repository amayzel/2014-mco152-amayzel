package mayzel.vendingmachine;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {

	@Test
	public void testLoad() throws IOException {
		Inventory i = new Inventory();
		i.load("./inventory.txt");
		Assert.assertEquals(
				"A01 Candy Bar @ $1.55 x 5\nB02 Chips @ $1.30 x 3\nC03 Pretzels @ $1.00 x 1\nD04 Nuts @ $2.25 x 10\nE05 Gum @ $1.75 x 20\n",
				i.toString());
	}

	@Test
	public void testGet() {
		Inventory i = new Inventory();
		Item it = new Item("B02", "Chips", 1.75, 7);
		i.add(it);
		String code = it.getCode();
		Item actual = i.get(code);
		Assert.assertEquals(it, actual);

	}

	@Test
	public void testAdd() {
		Inventory i = new Inventory();
		Item it = new Item("B02", "Chips", 1.75, 7);
		i.add(it);
		Assert.assertEquals("B02 Chips @ $1.75 x 7\n", i.toString());
	}

	@Test
	// wrong
	public void testRemoveOne() {
		Inventory i = new Inventory();
		Item it = new Item("B02", "Chips", 1.75, 7);
		i.add(it);
		i.removeOne(it.getCode());
		int actual = it.getQuantity();
		Assert.assertEquals(6, actual);
	}

	@Test
	public void testIsEmpty() {
		Inventory i = new Inventory();
		Item it = new Item("B02", "Chips", 1.75, 7);
		String code = it.getCode();
		Assert.assertTrue(i.isEmpty(code));
		i.add(it);
		Assert.assertFalse(i.isEmpty(code));
	}

	@Test
	public void testToString() {
		Inventory i = new Inventory();
		Item it = new Item("B02", "Chips", 1.75, 7);
		i.add(it);
		Assert.assertEquals("B02 Chips @ $1.75 x 7\n", i.toString());

	}

}
