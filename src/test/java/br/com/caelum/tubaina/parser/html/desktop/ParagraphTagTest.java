package br.com.caelum.tubaina.parser.html.desktop;

import org.junit.Assert;
import org.junit.Test;

public class ParagraphTagTest {

	@Test
	public void testParagraphTag(){
		String result = new ParagraphTag().parse("qualquer texto", null);
		Assert.assertEquals(result, "<p>qualquer texto</p>");
	}
	
}
