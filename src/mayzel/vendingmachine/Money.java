package mayzel.vendingmachine;

public class Money {

	private int numDollars;
	private int numQuarters;
	private int numDimes;
	private int numNickles;

	public Money() {

	}

	public Money(int numDollars, int numQuarters, int numDimes, int numNickles) {
		this.numDollars = numDollars;
		this.numQuarters = numQuarters;
		this.numDimes = numDimes;
		this.numNickles = numNickles;
	}

	public void add(Money money) {
		int dol = money.getNumDollars();
		this.setNumDollars(this.getNumDollars() + dol);
		int qtrs = money.getNumQuarters();
		this.setNumQuarters(this.getNumQuarters() + qtrs);
		int dime = money.getNumDimes();
		this.setNumDimes(this.getNumDimes() + dime);
		int nickle = money.getNumNickles();
		this.setNumNickles(this.getNumNickles() + nickle);

	}

	public Money remove(double amount) throws NotEnoughChangeException {
		int dol=0;
		int quat=0;
		int nick=0;
		int dime=0;
		while (amount >= 1.00 && numDollars >= 1) {
			numDollars--;
			amount = Math.round((amount - 1) * 100.0) / 100.0;
			dol++;
		}
		while (amount >= .25 &&  numQuarters >= 1) {
			numQuarters--;
			amount = Math.round((amount - .25) * 100.0) / 100.0;
			quat++;
		}
		while (amount >= .1 && numDimes>=1) {
			numDimes--;
			amount = Math.round((amount - .1) * 100.0) / 100.0;
			dime++;
		}
		while (amount >= .05 && numNickles>=1) {
			numNickles--;
			amount = Math.round((amount - .05) * 100.0) / 100.0;
			nick++;
		}
		if (amount!=0){
			throw new NotEnoughChangeException();
		}
		Money moneyLeft = new Money(dol, quat, dime, nick);
		return moneyLeft;

	}

	public double getTotal() {
		double total = numDollars + (numQuarters * .25) + (numDimes * .1) + (numNickles * .05);
		return total;
	}

	public int getNumDollars() {
		return numDollars;
	}

	public void setNumDollars(int numDollars) {
		this.numDollars = numDollars;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumNickles() {
		return numNickles;
	}

	public void setNumNickles(int numNickles) {
		this.numNickles = numNickles;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

}
