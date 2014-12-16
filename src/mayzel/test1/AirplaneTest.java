package mayzel.test1;
import java.util.ArrayList;
import java.util.List;

import mayzel.vendingmachine.NotEnoughChangeException;

import org.junit.Assert;
import org.junit.Test;

public class AirplaneTest {

	@Test
	public void testToStringWithEmptyPlane() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals(
				"    AB_CD_EF\n" +
				"001 .._.._..\n" + 
				"002 .._.._..\n" + 
				"003 .._.._..\n", plane.toString());
	}
	
	@Test
	public void testToStringWithFullPlane() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		Assert.assertEquals(
				"    AB_CD_EF\n" +
				"001 OO_OO_OO\n" + 
				"002 OO_OO_OO\n" + 
				"003 OO_OO_OO\n", plane.toString());
	}
	
	@Test
	public void testGetNumSeats() {
		Airplane plane = new Airplane("AB_CD_EF", 2);
		int actual = plane.getNumSeats();
		Assert.assertEquals(12,actual);
	}
	
	@Test
	public void testGetNumEmptySeats() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CDE_FG", 3);
		String []array ={"1B"};
		plane.occupy(array);
		int actual = plane.getNumEmptySeats();
		Assert.assertEquals(20,actual);
	}

	@Test
	public void testIsFull() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy(
				"1A", "1B", "1C", "1D", "1E", "1F",
				"2A", "2B", "2C", "2D", "2E", "2F",
				"3A", "3B", "3C", "3D", "3E", "3F");
		boolean condition = plane.isFull();
		Assert.assertTrue(condition);
	}
	
	@Test
	public void testGetSeatThrowsUnknownSeatException() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		try {
			plane.getSeat("5J");
			Assert.fail("UnknownSeatException should be thrown here");
		} catch (UnknownSeatException e) {
		}
	}
	
	@Test
	public void testOccupySeats() 
			throws UnknownSeatException, FullPlaneException, NotEnoughSeatsTogeatherException {
		Airplane plane = new Airplane("AB_CDE_FG", 3);
		plane.occupy("1D");
		List<Seat> actual = plane.occupySeats(3);
		List<Seat> expected = new ArrayList<Seat>();
		expected.add(plane.getSeat("2C"));	
		expected.add(plane.getSeat("2D"));
		expected.add(plane.getSeat("2E"));
		Assert.assertEquals(expected,actual);
	}
	
	@Test
	public void testOccupySeatsThrowsNotEnoughSeatsTogeatherException() throws FullPlaneException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		try {
			plane.occupySeats(5);
			Assert.fail("NotEnoughSeatsTogeatherException should be thrown here");
		} catch (NotEnoughSeatsTogeatherException e) {
		}
		
	}
	
	@Test
	public void testOccupySeatsThrowsFullPlaneException() throws NotEnoughSeatsTogeatherException, UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		try {
			plane.occupy(
					"1A", "1B", "1C", "1D", "1E", "1F",
					"2A", "2B", "2C", "2D", "2E", "2F",
					"3A", "3B", "3C", "3D", "3E", "3F");
			plane.occupySeats(2);
			Assert.fail("FullPlaneException should be thrown here");
		} catch (FullPlaneException e) {
		}
	}

}
