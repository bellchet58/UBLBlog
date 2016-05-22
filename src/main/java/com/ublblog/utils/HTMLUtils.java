package com.ublblog.utils;

import org.jsoup.Jsoup;

public class HTMLUtils {
	public static String html2Text(String html) {
		return Jsoup.parse(html).text();
	}
}
