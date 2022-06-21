package br.edu.univas.gabriel.controller.exception;

public class InvalidDataException extends RuntimeException {

	private static final long serialVersionUID = -5574509520281277952L;

	public InvalidDataException(String message) {
		super(message);
	}

}
