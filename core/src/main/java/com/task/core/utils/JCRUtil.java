package com.task.core.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class JCRUtil {
	private static final String HTTPS_PROTOCOL = "https";

	public static boolean isExternalLink(String href) {
		return Pattern.compile(String.format("^%s?://.*", HTTPS_PROTOCOL)).matcher(href).matches();
	}

	public static boolean isInternalAsset(String href) {
		return Pattern.compile(".*\\.(?!html)(\\w){1,5}$").matcher(href).matches();
	}
}
