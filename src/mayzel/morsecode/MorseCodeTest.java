package mayzel.morsecode;

import org.junit.Assert;
import org.junit.Test;

public class MorseCodeTest {

	@Test
	public void testToMorseCode() {
		MorseCode text = new MorseCode();
		
		String encoded = text.toMorseCode("input");
		Assert.assertEquals(".. -. .--. ..- -", encoded);
		
		encoded = text.toMorseCode("");
		Assert.assertEquals("", encoded);	
	}
	
	@Test
	public void testToPlainText() {
		MorseCode code = new MorseCode();
		
		String decoded = code.toPlainText(".. -. .--. ..- -");
		Assert.assertEquals("INPUT", decoded);
		Assert.assertEquals("input".toUpperCase(), decoded);
		
		decoded = code.toPlainText("");
		Assert.assertEquals("", decoded);
	}


}
