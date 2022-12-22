package com.pewitt.desktopbuilder;

import com.pewitt.desktopbuilder.enums.DesktopOption;
import com.pewitt.desktopbuilder.util.FileUtil;
import com.pewitt.desktopbuilder.util.OptionUtil;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesktopBuilderApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(DesktopBuilderApplication.class);

	public static void main(final String[] args) {
		SpringApplication app = new SpringApplication(DesktopBuilderApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}

	@Override
	public void run(final String... args) throws ParseException {
		CommandLine commandLine = new DefaultParser().parse(OptionUtil.getOptions(), args);


		String fileLocation = commandLine.getOptionValue(DesktopOption.LOCATION.getFullName());

		LOG.info("Target program is {}", fileLocation);

		if (!FileUtil.isFileLocationValid(fileLocation)) {
			LOG.error("{} is not a valid location", fileLocation);
			return;
		}

		String applicationName = commandLine.getOptionValue(DesktopOption.NAME.getFullName());
		FileUtil.createDesktopFile(applicationName, fileLocation, commandLine.getOptionValue(DesktopOption.ICON.getFullName()));
	}

}