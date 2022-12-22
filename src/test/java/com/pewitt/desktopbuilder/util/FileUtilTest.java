package com.pewitt.desktopbuilder.util;

import com.pewitt.desktopbuilder.constants.FileConstants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;

import static org.testng.Assert.*;


public class FileUtilTest {

	private static final String APP_NAME = "DesktopBuilder";
	private static final String PROGRAM_LOCATION = "/test.app";
	private static final String ICON_PATH = "/testing.png";

	@Test
	public void testCreateDesktopFile() {
		File outputFile = FileUtil.createDesktopFile(APP_NAME, PROGRAM_LOCATION, ICON_PATH);
		validateOutputFile(outputFile.getAbsolutePath());

		FileUtil.deleteFile(outputFile.getAbsolutePath());
	}

	@Test
	public void testIsFileLocationValid() throws IOException {
		File file = File.createTempFile(UUID.randomUUID().toString(), FileConstants.DESKTOP_EXTENSION);
		boolean fileExists = FileUtil.isFileLocationValid(file.getAbsolutePath());

		assertTrue(fileExists);
		FileUtil.deleteFile(file.getAbsolutePath());
	}

	@Test
	public void testIsFileLocationNotValid() {
		boolean fileExists = FileUtil.isFileLocationValid(UUID.randomUUID().toString());

		assertFalse(fileExists);
	}

	private void validateOutputFile(final String filePath) {
		validateOutputFile(filePath, "Name", "Name=" + APP_NAME);
		validateOutputFile(filePath, "Exec", "Exec=" + PROGRAM_LOCATION + " %U");
		validateOutputFile(filePath, "Icon", "Icon=" + ICON_PATH);
		validateOutputFile(filePath, "Type", "Type=Application");
	}

	private void validateOutputFile(final String filePath, final String linePrefix ,final String expectedLine) {
		try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
			stream.filter(lines -> lines.contains(linePrefix))
					.forEach(line -> Assert.assertEquals(line, expectedLine));
		} catch (final IOException e) {
			fail();
		}
	}



}
