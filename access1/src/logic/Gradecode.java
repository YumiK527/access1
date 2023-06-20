package logic;


import java.util.HashMap;
import java.util.Map;

public class Gradecode {
			//public Gradecode(String grade) {
		// TODO 自動生成されたコンストラクター・スタブ
	//}

		//入力された学年から並べ替え用のコードを作成
		public static String  Gradecode(String grade) {

			// Mapの宣言
	        Map<String, String> mapGc = new HashMap<>();

	        // Mapにキーと値を格納
	        mapGc.put("小１", "11");
	        mapGc.put("小２", "12");
	        mapGc.put("小３", "13");
	        mapGc.put("小４", "14");
	        mapGc.put("小５", "15");
	        mapGc.put("小６", "16");
	        mapGc.put("中１", "21");
	        mapGc.put("中２", "22");
	        mapGc.put("中３", "23");
	        mapGc.put("高１", "31");
	        mapGc.put("高２", "32");
	        mapGc.put("高３", "33");

	        // Mapからデータを取得する
	        String gradecode=(mapGc.get(grade));
			return gradecode;
		}
}
