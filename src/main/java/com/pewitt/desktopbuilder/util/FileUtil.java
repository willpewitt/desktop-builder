package com.pewitt.desktopbuilder.util;

import com.pewitt.desktopbuilder.constants.FileConstants;
import com.pewitt.desktopbuilder.constants.GroupHeader;
import com.pewitt.desktopbuilder.exception.DesktopFileCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

public final class FileUtil {

	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

	private static final Set<PosixFilePermission> FILE_PERMISSIONS = Set.of(PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.OWNER_WRITE, PosixFilePermission.OWNER_READ, PosixFilePermission.GROUP_EXECUTE, PosixFilePermission.GROUP_WRITE, PosixFilePermission.GROUP_READ, PosixFilePermission.OTHERS_READ, PosixFilePermission.OTHERS_EXECUTE);

	private FileUtil() {}

	public static File createDesktopFile(final String applicationName, final String programLocation, final String iconPath) {
		File outputFile = new File(applicationName + FileConstants.DESKTOP_EXTENSION);

		try {
			populateFile(outputFile.getName(), programLocation, applicationName, iconPath);
		} catch (final DesktopFileCreationException e) {
			deleteFile(outputFile.getPath());
			throw new DesktopFileCreationException(e);
		}

		setFilePermissions(outputFile.getPath());
		return outputFile;
	}

	private static void setFilePermissions(final String filePath) {
		try {
			Files.setPosixFilePermissions(Path.of(filePath), FILE_PERMISSIONS);
			Runtime.getRuntime().exec("gio set " + filePath + " metadata::trusted true");
		} catch (final IOException e) {
			LOG.error("Unable to set file permissions, desktop file is most likely not executable", e);
		}
	}

	public static void populateFile(final String fileName, final String programLocation, final String applicationName,
									final String iconPath) {
		try (final PrintWriter writer = new PrintWriter(fileName, StandardCharsets.UTF_8)) {
			writer.println(GroupHeader.DESKTOP_ENTRY);
			writer.println("Name=" + applicationName);
			writer.println("Exec=" + programLocation + " %U");
			if (StringUtils.hasText(iconPath)) {
				writer.println("Icon=" + iconPath);
			}
			writer.println("Type=Application");

		} catch (final IOException e) {
			throw new DesktopFileCreationException("Unable to populate file", e);
		}
	}

	public static boolean isFileLocationValid(final String path) {
		if (!new File(path).exists()) {
			LOG.error("{} is not a valid location", path);
			return false;
		}

		return true;
	}

	public static void deleteFile(final String path) {
		try {
			Files.delete(Path.of(path));
		} catch (final IOException e) {
			LOG.error("Unable to delete file {}", path, e);
		}
	}

}
