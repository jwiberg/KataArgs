package kata.args;

class Argument {
	private final String name;
	private final Object value;

	Argument(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	String getName() {
		return name;
	}

	Object getValue() {
		return value;
	}
}