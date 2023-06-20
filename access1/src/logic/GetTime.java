package logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetTime {
	//
	public static String getTime() {
		String getTime = "";

		// 今日の日付をカレンダーインスタンスとして取得
		Calendar startTime = Calendar.getInstance();
		// 日付フォーマットを準備
		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");//("yyyy/MM/dd/(a)KK:mm:ss");

		getTime = (f.format(startTime.getTime()));

		return getTime;
	}

}
