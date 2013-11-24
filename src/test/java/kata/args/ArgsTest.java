package kata.args;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArgsTest {

	@Test
	public void emptyInputIsParsedToEmptyArguments() {
		assertEquals(0, new Args().parse("").size());
	}

	@Test
	public void nullInputIsParsedToEmptyArguments() {
		assertEquals(0, new Args().parse(null).size());
	}

	@Test
	public void stringArgumentWithoutValueIsParsedToEmptyString() {
		assertEquals("", new Args().parse("-s").get("s"));
	}

	@Test
	public void simpleStringArgumentIsParsedToString() {
		assertEquals("string", new Args().parse("-s string").get("s"));
	}

	@Test
	public void twoSimpleStringArgumentsAreParsedToTwoStrings() {
		String input = "-s string1 -h string2";
		assertEquals("string1", new Args().parse(input).get("s"));
		assertEquals("string2", new Args().parse(input).get("h"));
	}

	@Test
	public void stringArgumentsWithSpaceShouldBeParsedToString() {
		String input = "-s string1 string2 -h string12 string22";
		assertEquals("string12 string22", new Args().parse(input).get("h"));
	}

	@Test
	public void stringArgumentWithNoValueIsParsedToEmptyString() {
		String input = "-s -h string12 string22";
		assertEquals("", new Args().parse(input).get("s"));
	}
}
