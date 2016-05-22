package com.ublblog.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ublblog.utils.StringUtils;

public class StringUtilsTest {

	@Test
	public void testGetSuffix() {
		String suffix = StringUtils.getSuffix("avatar.jpg");
		assertEquals("jpg", suffix);
	}

}
