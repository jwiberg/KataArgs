package kata.args;

import static org.junit.Assert.assertEquals;
import kata.args.Args.ArgumentNotDefined;

import org.junit.Before;
import org.junit.Test;

public class ArgsTest {

	private Args args;

	@Before
	public void before() throws Exception {
		String schema = "s:String h:String b:Boolean";
		this.args = new Args(schema);
	}

	@Test
	public void emptyInputIsParsedToEmptyArguments() {
		assertEquals(0, args.parse("").size());
	}

	@Test
	public void nullInputIsParsedToEmptyArguments() {
		assertEquals(0, args.parse(null).size());
	}

	@Test
	public void stringArgumentWithoutValueIsParsedToEmptyString() {
		assertEquals("", args.parse("-s").get("s"));
	}

	@Test
	public void simpleStringArgumentIsParsedToString() {
		assertEquals("string", args.parse("-s string").get("s"));
	}

	@Test
	public void twoSimpleStringArgumentsAreParsedToTwoStrings() {
		String input = "-s string1 -h string2";
		assertEquals("string1", args.parse(input).get("s"));
		assertEquals("string2", args.parse(input).get("h"));
	}

	@Test
	public void stringArgumentsWithSpaceShouldBeParsedToString() {
		String input = "-s string1 string2 -h string12 string22";
		assertEquals("string12 string22", args.parse(input).get("h"));
	}

	@Test
	public void stringArgumentWithNoValueIsParsedToEmptyString() {
		String input = "-s -h string";
		assertEquals("", args.parse(input).get("s"));
	}

	@Test(expected = ArgumentNotDefined.class)
	public void argumentsShouldBeNotBeAssignedToNamesNotDefinedInSchema() {
		String input = "-p string";
		args.parse(input);
	}

	@Test
	public void emptyBooleanArgumentsShouldBeParsedBooleanFalse() {
		String input = "-s -h -b";
		assertEquals(java.lang.Boolean.class, args.parse(input).get("b")
				.getClass());
	}

	@Test
	public void booleanArgumentsTrueShouldBeParsedBooleanTrue() {
		String input = "-s -h -b true";
		assertEquals(java.lang.Boolean.TRUE, args.parse(input).get("b"));
	}
}
