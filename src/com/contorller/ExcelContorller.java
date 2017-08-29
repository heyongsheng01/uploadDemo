package com.contorller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.utils.ExcelUtil;

@Controller
public class ExcelContorller {

	@RequestMapping("/nloadExcal")
	public void nloadExcal(HttpServletResponse response, HttpServletRequest request) {

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			String[] cols = { "编号", "部门名称", "年份", "月份", "报销金额" };
			List<String[]> dataList = new ArrayList<String[]>();
			for (int i = 0; i < 100000; i++) {
				Integer r = new Random().nextInt(90000) + 10000;
				StringBuffer buffer = new StringBuffer();
				char[] CHARS = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'n', 'p', 'q', 'r', 's', 't', 'u',
						'v', 'w', 'x', 'y', 'z' };
				for (int j = 0; j < 100; j++) {
					buffer.append(CHARS[new Random().nextInt(CHARS.length)]);
				}
				String year = new Integer(calendar.get(Calendar.YEAR)).toString();
				String month = new Integer(calendar.get(Calendar.MONTH) + 1).toString();

				dataList.add(new String[] { new Integer(i).toString(), buffer.toString(), year, month, r.toString() });
			}

			ExcelUtil.listToExcel(cols, dataList, "测试", response, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
