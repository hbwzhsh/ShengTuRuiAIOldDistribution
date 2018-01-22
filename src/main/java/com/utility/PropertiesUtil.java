package com.utility;

import com.datasource.DbPoolConnection;

import java.io.*;
import java.util.Properties;

public class PropertiesUtil {
	
	
	public static Properties loadPropertyFile(String fullFile) {
		String webRootPath = null;
		if (null == fullFile || fullFile.equals(""))
			throw new IllegalArgumentException(
					"Properties file path can not be null : " + fullFile);
		webRootPath = DbPoolConnection.class.getClassLoader().getResource("")
				.getPath();
		webRootPath = new File(webRootPath).getParent();
		InputStream inputStream = null;
		Properties p = null;
		try {
			inputStream = new FileInputStream(new File(webRootPath
					+ File.separator + fullFile));
			p = new Properties();
			p.load(inputStream);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Properties file not found: "
					+ fullFile);
		} catch (IOException e) {
			throw new IllegalArgumentException(
					"Properties file can not be loading: " + fullFile);
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}

}
