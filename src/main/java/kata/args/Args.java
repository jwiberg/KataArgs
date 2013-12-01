package kata.args;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Args {
	private final Map<String, Class<? extends Object>> schema = new HashMap<>();

	public Args(String schemaString) throws ClassNotFoundException {
		String[] splittedSchema = schemaString.split("\\s");
		for (String schemaItem : splittedSchema) {
			String key = schemaItem.split(":")[0];
			String clazz = schemaItem.split(":")[1];
			schema.put(key, Class.forName("java.lang." + clazz));
		}
	}

	public Map<String, Object> parse(String input) {
		Map<String, Object> arguments = new HashMap<String, Object>();

		if (StringUtils.isBlank(input)) {
			return arguments;
		}

		return parseArguments(input);
	}

	private Map<String, Object> parseArguments(String input) {
		Map<String, Object> arguments = new HashMap<>();
		for (String rawArgument : input.split("-")) {
			// First item should be passed (its empty, always)
			if (rawArgument.trim().length() == 0)
				continue;
			Argument argument = null;
			argument = parseArgument(rawArgument);
			arguments.put(argument.name, argument.value);
		}
		return arguments;
	}

	private Argument parseArgument(String rawArgument) {
		String[] splittedRawArgument = rawArgument.split("\\s", 2);

		validateArgumentAgainstSchema(splittedRawArgument);

		if (isStringArgument(splittedRawArgument[0])) {
			return parseStringArgument(splittedRawArgument);
		} else {
			return parseBooleanArgument(splittedRawArgument);
		}
	}

	private Argument parseBooleanArgument(String[] splittedRawArgument) {
		if (splittedRawArgument.length < 2) {
			return new Argument(splittedRawArgument[0], Boolean.FALSE);
		} else {
			return new Argument(splittedRawArgument[0],
					Boolean.valueOf(splittedRawArgument[1]));
		}
	}

	private Argument parseStringArgument(String[] splittedRawArgument) {
		if (splittedRawArgument.length < 2) {
			return new Argument(splittedRawArgument[0], "");
		} else {
			return new Argument(splittedRawArgument[0],
					splittedRawArgument[1].trim());
		}
	}

	private boolean isStringArgument(String argumentName) {
		return schema.get(argumentName).equals(java.lang.String.class);
	}

	private void validateArgumentAgainstSchema(String[] splittedRawArgument) {
		if (!schema.keySet().contains(splittedRawArgument[0])) {
			throw new ArgumentNotDefined(splittedRawArgument[0]);
		}
	}

	private class Argument {
		final String name;
		final Object value;

		public Argument(String name, Object value) {
			this.name = name;
			this.value = value;
		}
	}

	public class ArgumentNotDefined extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public ArgumentNotDefined(String message) {
			super(message);
		}
	}
}
