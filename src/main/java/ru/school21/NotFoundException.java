package ru.school21;

public class NotFoundException extends RuntimeException {

	NotFoundException(String message) {
		super(message);
	}
}
