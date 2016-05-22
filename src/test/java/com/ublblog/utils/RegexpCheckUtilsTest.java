package com.ublblog.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ublblog.utils.RegexpCheckUtils;

public class RegexpCheckUtilsTest {

	@Test
	public void testCheckWebsite() {
		assertTrue(RegexpCheckUtils.checkWebSite("http://www.ublblog.com"));
	}

}
