package com.KoreaIT.Java.AM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	// 현재 날짜 시간 - 반환하는 용도.
	public static String getDateStr() {
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
				
		Date time = new Date();
				
		return format1.format(time);
				
	}
}
