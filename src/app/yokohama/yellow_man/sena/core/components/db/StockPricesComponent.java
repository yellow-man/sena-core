package yokohama.yellow_man.sena.core.components.db;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import yokohama.yellow_man.sena.core.components.db.dao.StockPricesDao;
import yokohama.yellow_man.sena.core.models.StockPrices;

/**
 * 株価（stock_prices）モデルの操作を行うコンポーネントクラス。
 *
 * @author yellow-man
 * @since 1.2.0
 * @version 1.2.2
 */
public class StockPricesComponent extends StockPricesDao {

	/**
	 * 検索条件に銘柄コード（{@code stock_code}）を指定し、
	 * 株価テーブルより指定日付範囲内{@code startDate} 〜 {@code endDate}の取得日（ミリ秒）降順のリストを返す。
	 *
	 * @param stockCode 銘柄コード
	 * @param startDate 取得開始日（以上）
	 * @param endDate 取得終了日（以下）
	 * @return 取得日（{@code date}）降順の株価情報を返す。
	 * @since 1.2.0
	 */
	public static List<StockPrices> getStockPricesListByStockCode(
			Integer stockCode, Date startDate, Date endDate) {

		ExpressionList<StockPrices> expressionList = Ebean.find(StockPrices.class)
					.where()
					.eq("delete_flg", false)
					.eq("stock_code", stockCode);

		if (startDate != null) {
			expressionList.ge("date", startDate);
		}
		if (endDate != null) {
			expressionList.ge("date", endDate);
		}
		List<StockPrices> retList =
				expressionList.orderBy("date DESC, id DESC").findList();

		return retList;
	}


	/**
	 * 検索条件に銘柄コード（{@code stock_code}）を指定し、
	 * 株価テーブルより指定日付範囲内{@code startDate} 〜 {@code endDate}の株価情報 Map＜取得日, 株価情報＞を返す。
	 *
	 * @param stockCode 銘柄コード
	 * @param startDate 取得開始日（以上）
	 * @param endDate 取得終了日（以下）
	 * @return 株価情報 Map＜取得日, 株価情報＞を返す。
	 * @since 1.2.2
	 */
	public static Map<Date, StockPrices> getStockPricesMapByStockCode(
			Integer stockCode, Date startDate, Date endDate) {

		ExpressionList<StockPrices> expressionList = Ebean.find(StockPrices.class)
					.where()
					.eq("delete_flg", false)
					.eq("stock_code", stockCode);

		if (startDate != null) {
			expressionList.ge("date", startDate);
		}
		if (endDate != null) {
			expressionList.ge("date", endDate);
		}
		Map<Date, StockPrices> retMap =
				expressionList.orderBy("date DESC, id DESC").findMap("date", Date.class);

		return retMap;
	}

	/**
	 * 株価（stock_prices）モデルのアップデートを行う。
	 * @param data アップデート対象のデータ
	 * @param whereMap Where条件に指定するMap＜カラム名, 値＞
	 * @return UPDATE結果件数を返す。
	 * @since 1.2.2
	 */
	public static int update(StockPrices data, Map<String, Object> whereMap) {
		return StockPricesDao.update(data, whereMap);
	}
}
