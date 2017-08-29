package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.utils.ExcelUtil;

public class ExcelToListTest {

	public static void main(String[] args) {
		File f = new File("");
		try {
			InputStream in = new FileInputStream(f);
			
			//ExcelUtil.excelToList(in, sheetName, entityClass, fieldMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
