package com.pewitt.desktopbuilder.exception;

public class DesktopFileCreationException extends RuntimeException {

	public DesktopFileCreationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public DesktopFileCreationException(final Throwable cause) {
		super(cause);
	}
}
