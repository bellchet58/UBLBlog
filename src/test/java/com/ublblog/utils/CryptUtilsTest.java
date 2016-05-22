package com.ublblog.utils;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ublblog.utils.CryptUtils;

public class CryptUtilsTest {

	@Test
	public void testEncrypt() {
		String password = "tz8888";
		String encrypted = CryptUtils.encryptString(password);
		assertEquals(password,CryptUtils.decryptString(encrypted) );
		assertEquals("E02omOVedOc=", encrypted);
	}

}
