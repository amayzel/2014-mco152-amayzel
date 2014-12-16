package mayzel.scrabble;

import org.junit.Assert;
import org.junit.Test;

public class ScrabbleDictionaryTest {

	//this is what we want to test first
	@Test
	public void testContainsTrue() {
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		
		Assert.assertTrue(dictionary.contains("HELLO"));
		Assert.assertTrue(dictionary.contains("Hello"));
	}

	@Test
	public void testContainsFalse() {
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		
		Assert.assertFalse(dictionary.contains("DGASNBUABOBG"));
		Assert.assertFalse(dictionary.contains("bthnbxkhbfwaptpe"));
	}
	
	@Test
	public void testContainsNull() {
		ScrabbleDictionary dictionary = new ScrabbleDictionary();
		
		Assert.assertFalse(dictionary.contains(null));
	}
}
