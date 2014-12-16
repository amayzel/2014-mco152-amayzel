package mayzel.acm;

import org.junit.Assert;
import org.junit.Test;

public class NthLargestValueTest {

	@Test
	public void testGetValue() {
		NthLargestValue nlv = new NthLargestValue();
		int [] array = {2,14,6,18,20,4,16,8,10,12};
		int  actual = nlv.getValue(array); 
		Assert.assertEquals(16, actual);
	}

}
