package kata.args;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Args {
	public Map<String, Object> parse(String input) {
		Map<String, Object> arguments = new HashMap<String, Object>();

		if (StringUtils.isBlank(input)) {
			return arguments;
		}

		for (String rawArgument : input.split("-")) {
			String[] parsedArgument = parseArgument(rawArgument);
			arguments.put(parsedArgument[0], parsedArgument[1].trim());
		}

		return arguments;
	}

	private String[] parseArgument(String rawArgument) {
		String[] parsedArgument = new String[] { "", "" };
		String[] splittedRawArgument = rawArgument.split("\\s", 2);
		if (splittedRawArgument.length < 2) {
			parsedArgument[0] = splittedRawArgument[0];
		} else {
			parsedArgument[0] = splittedRawArgument[0];
			parsedArgument[1] = splittedRawArgument[1];
		}
		return parsedArgument;
	}
}
