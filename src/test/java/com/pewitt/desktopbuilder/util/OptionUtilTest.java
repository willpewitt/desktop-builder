package com.pewitt.desktopbuilder.util;

import com.pewitt.desktopbuilder.enums.DesktopOption;
import org.apache.commons.cli.Options;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class OptionUtilTest {

	@Test
	public void testGetOptions() {
		Options options = OptionUtil.getOptions();
		assertFalse(options.getOptions().isEmpty());

		assertEquals(options.getOptions().size(), 3);

		assertTrue(options.hasOption(DesktopOption.NAME.getFullName()));
		assertTrue(options.hasOption(DesktopOption.LOCATION.getFullName()));
		assertTrue(options.hasOption(DesktopOption.ICON.getFullName()));
	}

}
