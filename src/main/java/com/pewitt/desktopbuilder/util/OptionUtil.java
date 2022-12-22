package com.pewitt.desktopbuilder.util;

import com.pewitt.desktopbuilder.enums.DesktopOption;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class OptionUtil {

	private OptionUtil () {}

	public static Options getOptions() {
		Options options = new Options();
		options.addOption(getOption(DesktopOption.NAME));
		options.addOption(getOption(DesktopOption.LOCATION));
		options.addOption(getOption(DesktopOption.ICON));

		return options;
	}

	public static Option getOption(final DesktopOption desktopOption) {
		return Option.builder()
				.desc(desktopOption.getDescription())
				.hasArg()
				.required(desktopOption.isRequired())
				.argName(desktopOption.getShortName())
				.longOpt(desktopOption.getFullName())
				.build();
	}

}
