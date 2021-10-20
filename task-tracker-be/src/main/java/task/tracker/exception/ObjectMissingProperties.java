package task.tracker.exception;

public class ObjectMissingProperties extends Exception {

	private static final long serialVersionUID = 1L;

	public ObjectMissingProperties() {
		super();
	}

	public ObjectMissingProperties(String message) {
		super(message);
	}

	public ObjectMissingProperties(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectMissingProperties(Throwable cause) {
		super(cause);
	}
}
