package com.rootser.bluerootser.tools.jar.problem;
/**
 * modify this test to discover where
 * tools.jar is on your system 
 */
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaHomeSysPropTest {
	private static final Logger logger = LoggerFactory.getLogger(JavaHomeSysPropTest.class);
	@Test
	public void test(){
		File libDir = new File(System.getProperty("java.home")+"/../lib/");
		logger.debug(libDir.getAbsolutePath());
		boolean foundToolsJar = false;
		for (File file: libDir.listFiles()){
			String absPath = file.getAbsolutePath();
			System.out.println(absPath);
			foundToolsJar = absPath != null && absPath.contains("tools.jar");
			if (foundToolsJar){
				break;
			}
		}
		assertTrue("java.home does not contain tools.jar", foundToolsJar);
	}

}
