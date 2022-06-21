package br.edu.univas.gabriel.controller.exception;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 242718117185016387L;

	public ObjectNotFoundException(String message) {
		super(message);
	}

}
