package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.utils.ExcelUtil;

public class ListToExcelTest {

	public static void main(String[] args) {
		FileOutputStream fos = null;
		try {
			Calendar calendar = Calendar.getInstance();
			String[] cols = { "编号", "部门名称", "年份", "月份", "报销金额" };
			List<String[]> dataList = new ArrayList<String[]>();
			for (int i = 0; i < 100; i++) {
				Integer r = new Random().nextInt(90000) + 10000;
				StringBuffer buffer = new StringBuffer();
				char[] CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',
						'k', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
						'y', 'z' };
				for (int j = 0; j < 6; j++) {
					buffer.append(CHARS[new Random().nextInt(CHARS.length)]);
				}
				buffer.append("//////////aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				calendar.setTime(new Date());
				String year = new Integer(calendar.get(Calendar.YEAR))
						.toString();
				String month = new Integer(calendar.get(Calendar.MONTH) + 1)
						.toString();

				dataList.add(new String[] { new Integer(i).toString(),
						buffer.toString(), year, month, r.toString() });
			}

			String filename = "D:\\测试.xls";
			if (!filename.endsWith("xls")) {
				filename += ".xls";
			}
			File file = new File(filename);
			if (!file.exists()) {
				file.createNewFile();
			}
			fos = new FileOutputStream(file);
			ExcelUtil.listToExcel(cols, dataList, "测试", fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println(1);
	}
}
