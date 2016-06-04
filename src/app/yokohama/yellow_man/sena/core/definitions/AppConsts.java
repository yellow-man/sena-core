package yokohama.yellow_man.sena.core.definitions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参照するアプリケーション全体で利用する定数定義クラス。
 * @author yellow-man
 * @since 1.0
 */
public class AppConsts {

	/** 決算種別：1..第１(int) */
	public static final int INT_SETTLEMENT_TYPES_ID_1 = 1;
	/** 決算種別：2..第２(int) */
	public static final int INT_SETTLEMENT_TYPES_ID_2 = 2;
	/** 決算種別：3..第３(int) */
	public static final int INT_SETTLEMENT_TYPES_ID_3 = 3;
	/** 決算種別：4..本(int) */
	public static final int INT_SETTLEMENT_TYPES_ID_4 = 4;

	/** 決算種別：1..第１ */
	public static final Integer SETTLEMENT_TYPES_ID_1 = INT_SETTLEMENT_TYPES_ID_1;
	/** 決算種別：2..第２ */
	public static final Integer SETTLEMENT_TYPES_ID_2 = INT_SETTLEMENT_TYPES_ID_2;
	/** 決算種別：3..第３ */
	public static final Integer SETTLEMENT_TYPES_ID_3 = INT_SETTLEMENT_TYPES_ID_3;
	/** 決算種別：4..本 */
	public static final Integer SETTLEMENT_TYPES_ID_4 = INT_SETTLEMENT_TYPES_ID_4;

	/** 決算種別：マッピング */
	public static final Map<String, Integer> SETTLEMENT_TYPES_ID_MAP = Collections.unmodifiableMap(new HashMap<String, Integer>() {
		{
			put("第１", SETTLEMENT_TYPES_ID_1);
			put("第２", SETTLEMENT_TYPES_ID_2);
			put("第３", SETTLEMENT_TYPES_ID_3);
			put("本"  , SETTLEMENT_TYPES_ID_4);
		}
	});

	/** 決算種別：マッピング */
	public static final Map<Integer, String> SETTLEMENT_TYPES_ID_MAP_STR = Collections.unmodifiableMap(new HashMap<Integer, String>() {
		{
			put(SETTLEMENT_TYPES_ID_1, "第１");
			put(SETTLEMENT_TYPES_ID_2, "第２");
			put(SETTLEMENT_TYPES_ID_3, "第３");
			put(SETTLEMENT_TYPES_ID_4, "本");
		}
	});

	/** 決算種別：リスト */
	public static final List<Integer> SETTLEMENT_TYPES_ID_LIST = Collections.unmodifiableList(new ArrayList<Integer>() {
		{
			add(SETTLEMENT_TYPES_ID_1);
			add(SETTLEMENT_TYPES_ID_2);
			add(SETTLEMENT_TYPES_ID_3);
			add(SETTLEMENT_TYPES_ID_4);
		}
	});

	/**
	 * 決算期タイプを取得する。
	 * @param str 決算種別を表す文字列
	 * @return 決算種別
	 * @since 1.0
	 */
	public static Integer getSettlementType(String str){
		if(str.indexOf("通期") > -1){
			return SETTLEMENT_TYPES_ID_4;
		}else if(str.indexOf("第3四") > -1){
			return SETTLEMENT_TYPES_ID_3;
		}else if(str.indexOf("第2四") > -1){
			return SETTLEMENT_TYPES_ID_2;
		}else if(str.indexOf("第1四") > -1){
			return SETTLEMENT_TYPES_ID_1;
		}
		return null;
	}

	/** 取引種別：1..国内株式 */
	public static final Integer STOCKS_TYPE_1 = 1;
	/** 取引種別：2..金額・株数指定取引 */
	public static final Integer STOCKS_TYPE_2 = 2;
	/** 取引種別：3..信用建玉（買建） */
	public static final Integer STOCKS_TYPE_3 = 3;
	/** 取引種別：4..信用建玉（売建） */
	public static final Integer STOCKS_TYPE_4 = 4;

	/** 取引種別：1..国内株式 */
	public static final String STOCKS_TYPE_STR_1 = "国内株式";
	/** 取引種別：2..金額・株数指定取引 */
	public static final String STOCKS_TYPE_STR_2 = "金額・株数指定取引";
	/** 取引種別：3..信用建玉（買建） */
	public static final String STOCKS_TYPE_STR_3 = "信用建玉（買建）";
	/** 取引種別：4..信用建玉（売建） */
	public static final String STOCKS_TYPE_STR_4 = "信用建玉（売建）";

	/** 取引種別：マッピング */
	public static final Map<Integer, String> STOCKS_TYPE_MAP = Collections.unmodifiableMap(new HashMap<Integer, String>() {
		{
			put(STOCKS_TYPE_1, STOCKS_TYPE_STR_1);
			put(STOCKS_TYPE_2, STOCKS_TYPE_STR_2);
			put(STOCKS_TYPE_3, STOCKS_TYPE_STR_3);
			put(STOCKS_TYPE_4, STOCKS_TYPE_STR_4);
		}
	});

}
