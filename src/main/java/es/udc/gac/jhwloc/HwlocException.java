package es.udc.gac.jhwloc;

public class HwlocException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8865287217115502153L;

	HwlocException() {
		super();
	}

	HwlocException(String message) {
		super(message);
	}

	HwlocException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
