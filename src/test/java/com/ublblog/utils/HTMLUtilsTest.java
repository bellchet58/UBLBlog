package com.ublblog.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ublblog.utils.HTMLUtils;

public class HTMLUtilsTest {

	@Test
	public void testParse() {
		String str = "<h1>Hello</h1>";
		assertEquals("Hello", HTMLUtils.html2Text(str));
		
	}

}
