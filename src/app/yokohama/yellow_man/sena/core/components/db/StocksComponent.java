package yokohama.yellow_man.sena.core.components.db;

import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.ExpressionList;

import yokohama.yellow_man.sena.core.components.db.dao.StocksDao;
import yokohama.yellow_man.sena.core.models.Stocks;

/**
 * 銘柄（stocks）モデルの操作を行うコンポーネントクラス。
 *
 * @author yellow-man
 * @since 1.0.0
 * @version 1.2.1
 * @see yokohama.yellow_man.sena.core.components.db.dao.StocksDao
 */
public class StocksComponent extends StocksDao {

	/**
	 * 銘柄（stocks）モデルのバルクインサートを行う。
	 *
	 * @param list バルクインサート対象リスト
	 * @return INSERT結果件数を返す。
	 * @since 1.0.0
	 * @see StocksDao#executeBulkInsert(List)
	 */
	public static int executeBulkInsert(List<Stocks> list) {
		return StocksDao.executeBulkInsert(list);
	}

	/**
	 * 銘柄（stocks）の削除フラグ無効（{@code false}）のレコードを全件削除（{@code true}）する。
	 *
	 * @return UPDATE結果件数を返す。
	 * @since 1.0.0
	 * @see StocksDao#deleteAll()
	 */
	public static int deleteAll() {
		return StocksDao.deleteAll();
	}

	/**
	 * 未削除の銘柄（stocks）情報一覧を返す。
	 *
	 * @return 未削除の銘柄（stocks）情報一覧
	 * @since 1.0.0
	 */
	public static List<Stocks> getStocksList() {
		List<Stocks> retList =
				Ebean.find(Stocks.class)
					.where()
					.eq("delete_flg", false)
					.orderBy("id ASC")
					.findList();

		return retList;
	}

	/**
	 * 未削除の{@code minStockCode}以上、{@code maxStockCode}以下の銘柄（stocks）情報一覧を返す。
	 * <p>また、{@code minStockCode}、{@code maxStockCode}の指定がない場合全件返す。
	 *
	 * @param minStockCode 取得対象最小銘柄コード
	 * @param maxStockCode 取得対象最大銘柄コード
	 * @return 未削除の銘柄（stocks）情報一覧
	 * @since 1.2.1
	 */
	public static List<Stocks> getStocksList(Integer minStockCode, Integer maxStockCode) {

		ExpressionList<Stocks> expressionList =
				Ebean.find(Stocks.class)
					.where()
					.eq("delete_flg", false);

		if (minStockCode != null) {
			expressionList.ge("stock_code", minStockCode);
		}
		if (maxStockCode != null) {
			expressionList.le("stock_code", maxStockCode);
		}

		List<Stocks> retList = expressionList.orderBy("id ASC").findList();

		return retList;
	}

	/**
	 * 検索条件に取得日（{@code date}）を指定し、
	 * 未削除の銘柄（stocks）情報一覧を返す。
	 *
	 * @param date 取得日
	 * @return 未削除の銘柄（stocks）情報一覧
	 * @since 1.0.0
	 */
	public static List<Stocks> getStocksListByDate(Date date) {
		List<Stocks> retList =
				Ebean.find(Stocks.class)
					.where()
					.eq("delete_flg", false)
					.eq("date", date)
					.orderBy("id ASC")
					.findList();

		return retList;
	}

	/**
	 * 検索条件に取得日（{@code date}）を指定し、
	 * 未削除の銘柄（stocks）情報一覧総数を返す。
	 *
	 * @param date 取得日
	 * @return 未削除の銘柄（stocks）情報一覧総数を返す。
	 * @since 1.1.0
	 * @see StocksDao#getTotalCountByDate(Date)
	 */
	public static int getTotalCountByDate(Date date) {
		return StocksDao.getTotalCountByDate(date);
	}
}
