package kata.args;

abstract class ArgumentParser {
	protected Class<? extends Object> parseableType;

	protected abstract Argument parse(String rawArgument);

	Argument parse(String rawArgument, Class<? extends Object> typeToParse) {
		if (typeToParse.equals(parseableType)) {
			return this.parse(rawArgument);
		}
		return null;
	}
}
