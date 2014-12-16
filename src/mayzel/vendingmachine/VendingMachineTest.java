package mayzel.vendingmachine;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {
	DecimalFormat formatter = new DecimalFormat("$#,##0.00");

	@Test
	public void testPay() {
		VendingMachine vm = new VendingMachine(new Inventory(),new Money(5, 0, 0, 0));
		Money additional = new Money(1, 0, 0, 0);
		String actual = formatter.format(vm.pay(additional));
		Assert.assertEquals("$1.00", actual);
	}

	@Test
	public void testBuy() throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		Inventory i = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		i.add(item);
		Money b = new Money(10, 10, 10, 10);
		VendingMachine vm = new VendingMachine(i, b);
		Money paying = new Money(3, 0, 0, 0);
		vm.pay(paying);
		Money change = vm.buy("B02");
		String change2 = formatter.format(change.getTotal());
		Assert.assertEquals("$1.25",change2);
	}
	
	@Test
	public void testBuyPaidReset()throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		Inventory i = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		i.add(item);
		VendingMachine vm = new VendingMachine(i, new Money(10,10,10,10));
		Money paid = new Money(3, 0, 0, 0);
		vm.pay(paid);
		vm.buy("B02");
		String finalPaid = formatter.format(vm.getPaid().getTotal());
		Assert.assertEquals("$0.00", finalPaid);
		
	}
	
	@Test
	public void testBuyQuantityReduced()throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		Inventory i = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		i.add(item);
		VendingMachine vm = new VendingMachine(i, new Money(10,10,10,10));
		Money paid = new Money(3, 0, 0, 0);
		vm.pay(paid);
		vm.buy("B02");
		int actual = item.getQuantity();
		Assert.assertEquals(6, actual);
		
	}
	
	@Test
	public void testBuyThrowsCodeNotFoundException() throws NotEnoughPaidException, NotEnoughChangeException {
		Inventory i = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		i.add(item);
		VendingMachine vm = new VendingMachine(i, new Money(10,10,10,10));
		try {
			Money paid = new Money(3, 0, 0, 0);
			vm.pay(paid);
			vm.buy(null);
			Assert.fail("CodeNotFoundException should be thrown here");
		} catch (CodeNotFoundException e) {
		} 
	}
	
	@Test
	public void testBuyThrowsNotEnoughPaidException() throws NotEnoughChangeException, CodeNotFoundException {
		Inventory i = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		i.add(item);
		VendingMachine vm = new VendingMachine(i, new Money(10,10,10,10));
		try {
			Money paid = new Money(0, 3, 0, 0);
			vm.pay(paid);
			vm.buy("B02");
			Assert.fail("NotEnoughPaidException should be thrown here");
		} catch (NotEnoughPaidException e) {
		} 
	}
	
	@Test
	public void testBuyThrowsNotEnoughChangeException() throws NotEnoughPaidException, CodeNotFoundException{
		Inventory i = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		i.add(item);
		VendingMachine vm = new VendingMachine(i, new Money(10,10,10,10));
		try {
			Money paid = new Money(20, 20, 20, 20);
			vm.pay(paid);
			vm.buy("B02");
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {
		} 
	}
	
	@Test
	public void testBuyThrowsNotEnoughChangeExceptionOnlyDollars() throws NotEnoughPaidException, CodeNotFoundException{
		Inventory i = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 7);
		i.add(item);
		VendingMachine vm = new VendingMachine(i, new Money(10,0,0,0));
		try {
			Money paid = new Money(20, 20, 20, 20);
			vm.pay(paid);
			vm.buy("B02");
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {
		} 
	}
	
	@Test
	public void testBuyItemOutOfStock()throws NotEnoughPaidException, NotEnoughChangeException, CodeNotFoundException {
		Inventory i = new Inventory();
		Item item = new Item("B02", "Chips", 1.75, 0);
		i.add(item);
		VendingMachine vm = new VendingMachine(i, new Money(10,10,10,10));
		Money paid = new Money(3, 0, 0, 0);
		vm.pay(paid);
		Money actual = vm.buy("B02");
		Assert.assertEquals(0, actual.getTotal(),0); 		
	}
	

}
