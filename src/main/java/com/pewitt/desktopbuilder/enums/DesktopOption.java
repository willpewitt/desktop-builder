package com.pewitt.desktopbuilder.enums;

public enum DesktopOption {

	LOCATION("location", "l", "The location of the application", true),
	NAME("name", "n", "The name of the application", true),
	ICON("icon", "i", "The path to the icon of the application", false);

	private final String fullName;
	private final String shortName;
	private final String description;

	private final boolean isRequired;

	DesktopOption(final String fullName, final String shortName, final String description, final boolean isRequired) {
		this.fullName = fullName;
		this.shortName = shortName;
		this.description = description;
		this.isRequired = isRequired;
	}

	public String getFullName() {
		return fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public String getDescription() {
		return description;
	}

	public boolean isRequired() {
		return isRequired;
	}

}
