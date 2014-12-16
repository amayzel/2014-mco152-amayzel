package mayzel.vendingmachine;

import java.text.DecimalFormat;
import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {
	DecimalFormat formatter = new DecimalFormat("$#,##0.00");

	@Test
	public void testAdd() {
		Money oldMoney = new Money();
		Money newMoney = new Money(5, 2, 1, 2);
		oldMoney.add(newMoney);
		String actual = formatter.format(oldMoney.getTotal());
		Assert.assertEquals("$5.70",actual);
	}

	@Test
	public void testRemove() throws NotEnoughChangeException {
		Money money = new Money(0, 4, 1, 1);
		Money removed = money.remove(.10);
		String actual = formatter.format(removed.getTotal());
		Assert.assertEquals("$0.10",actual);
	}

	@Test
	public void testRemoveThrowsNotEnoughChangeException() {
		Money money = new Money(0, 0, 0, 0);
		try {
			money.remove(1.00);
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {
		}
	}

	@Test
	public void testGetTotal() {
		Money money = new Money(2, 4, 10, 0);
		String amount = formatter.format(money.getTotal());
		Assert.assertEquals("$4.00",amount);
	}

}
