package yokohama.yellow_man.sena.core.components.db;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;

import yokohama.yellow_man.sena.core.models.Indicators;

/**
 * 指標（indicators）モデルの操作を行うコンポーネントクラス。
 *
 * @author yellow-man
 * @since 1.0
 */
public class IndicatorsComponent {

	/**
	 * 検索条件に取得日（{@code date}）を指定し、
	 * 未削除の指標（indicators）情報一覧を返す。
	 *
	 * @param date 取得日
	 * @return 未削除の指標（indicators）情報一覧
	 * @since 1.0
	 */
	public static List<Indicators> getIndicatorsListByDate(Date date) {
		List<Indicators> retList =
				Ebean.find(Indicators.class)
					.where()
					.eq("delete_flg", false)
					.eq("date", date)
					.orderBy("id ASC")
					.findList();

		return retList;
	}

	/**
	 * 検索条件に取得日（{@code date}）を指定し、
	 * 未削除の指標（indicators）情報一覧をマップで返す。
	 *
	 * @param date 取得日
	 * @return 未削除の指標（indicators）情報一覧 Map<銘柄コード, 指標>
	 * @since 1.0
	 */
	public static Map<Integer, Indicators> getIndicatorsMapByDate(Date date) {
		Map<Integer, Indicators> retMap =
				Ebean.find(Indicators.class)
					.where()
					.eq("delete_flg", false)
					.eq("date", date)
					.orderBy("id ASC")
					.findMap("stockCode", Integer.class);

		return retMap;
	}
}
