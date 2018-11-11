package com.teqlip.exceptions;

public class AlgorithmNotExistException extends Exception {

	public AlgorithmNotExistException() {
		super("No such algorithm");
	}
}
