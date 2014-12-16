package mayzel.acm;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class RepeatingCharactersTest {

	@Test
	public void testRepeatLowerCaseChars() {
		RepeatingCharacters rc = new RepeatingCharacters();
		String actual = rc.repeat(3, "a.m"); 
		Assert.assertEquals("AAA...MMM", actual);
	}
	
	@Test
	public void testRepeatUpperCaseChars() {
		RepeatingCharacters rc = new RepeatingCharacters();
		String actual = rc.repeat(2, "HELLO"); 
		Assert.assertEquals("HHEELLLLOO", actual);
	}

}
