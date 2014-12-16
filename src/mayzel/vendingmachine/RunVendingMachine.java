package mayzel.vendingmachine;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class RunVendingMachine {

	// print inventory
	// make selection
	// input money
	// input selection
	// output change
	private static VendingMachine vm;
	private static Inventory inv;
	private static Money bank;

	public static void main(String[] args) {
		try {
			inv = new Inventory();
			inv.load("./inventory.txt");
			bank = new Money();
			bank.add(new Money(10, 10, 10, 10));
			vm = new VendingMachine(inv, bank);
			System.out.println(vm.getInventory());
			Scanner keyboard = new Scanner(System.in);
			System.out.println("Add Money/Make Selection?\n" + "1.Dollar\n" + "2.Quarter\n" + "3.Dime\n" + "4.Nickle\n"
					+ "or enter in the item code");
			System.out.println("\nBalance: $0.00");
			DecimalFormat formatter = new DecimalFormat("$#,##0.00");
			String input;
			Money change;
			boolean go = true;
			do {
				input = keyboard.nextLine();
				switch (input) {
				case "1":
					Money d = new Money(1, 0, 0, 0);
					vm.pay(d);
					System.out.println("Balance: " + formatter.format(vm.getPaid().getTotal()));
					break;
				case "2":
					Money q = new Money(0, 1, 0, 0);
					vm.pay(q);
					System.out.println("Balance: " + formatter.format(vm.getPaid().getTotal()));
					break;
				case "3":
					Money di = new Money(0, 0, 1, 0);
					vm.pay(di);
					System.out.println("Balance: " + formatter.format(vm.getPaid().getTotal()));
					break;
				case "4":
					Money n = new Money(0, 0, 0, 1);
					vm.pay(n);
					System.out.println("Balance: " + formatter.format(vm.getPaid().getTotal()));
					break;
				default:
					change = new Money();
					try {
						change = vm.buy(input);
						System.out.println("Dispensing " + vm.getInventory().get(input).getName());
						System.out.println("Change: " + formatter.format(change.getTotal()));
						go = false;
					} catch (CodeNotFoundException e) {
						System.out.println("Code Not Found");
						go = true;
					} catch (NotEnoughPaidException e1) {
						System.out.println("Not Enough Paid");
						go = true;
					} catch (NotEnoughChangeException e2) {
						System.out.println("Not Enough Change");
					}
					break;
				}
			} while (go);

		} // end of try
		catch (IOException e) {
			System.out.println("Cannot Find File!");
		}
	}

}