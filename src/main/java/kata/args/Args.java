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

		String[] rawArguments = input.split("-");

		for (int i = 1; i < rawArguments.length; i++) {
			String[] rawArgument = rawArguments[i].split("\\s", 2);
			if (rawArgument.length > 1) {
				arguments.put(rawArgument[0], rawArgument[1].trim());
			} else {
				arguments.put(rawArgument[0], "");
			}
		}

		return arguments;
	}
}
