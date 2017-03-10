package yokohama.yellow_man.sena.core.components.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import com.avaje.ebean.SqlUpdate;

import yokohama.yellow_man.sena.core.components.db.StocksComponent;
import yokohama.yellow_man.sena.core.models.AppModel;
import yokohama.yellow_man.sena.core.models.Stocks;

/**
 * 銘柄（stocks）モデルのネイティブクエリによる操作クラス。
 * <p>アプリケーションとは疎結合としたい為、直接の参照は行わない。
 * <br>利用する場合、{@link StocksComponent}を経由しアクセスする。
 *
 * @author yellow-man
 * @since 1.0.0
 * @version 1.2.0
 */
public class StocksDao {

	/**
	 * 銘柄（stocks）モデルのバルクインサートを行う。
	 * @param list バルクインサート対象リスト
	 * @return INSERT結果件数を返す。
	 * @since 1.0.0
	 */
	protected static int executeBulkInsert(List<Stocks> dataList) {

		// 基底クラスの executeBulkInsert に受け渡すパラメータを生成
		Class<? extends AppModel> modelClass = Stocks.class;
		String tableName = "stocks";
		List<String> excludeColumnNameList = new ArrayList<String>();
		excludeColumnNameList.add("id");

		// バルクインサート実行
		return AppDao.executeBulkInsert(dataList, modelClass, tableName, excludeColumnNameList);
	}

	/**
	 * 銘柄（stocks）の削除フラグ無効（{@code false}）のレコードを全件削除（{@code true}）する。
	 * @return UPDATE結果件数を返す。
	 * @since 1.0.0
	 */
	protected static int deleteAll() {
		String sql = "UPDATE stocks SET delete_flg = true, modified = :modified WHERE delete_flg = false";
		SqlUpdate sqlUpdate = Ebean.createSqlUpdate(sql);
		sqlUpdate.setParameter("modified", new Date());
		return sqlUpdate.execute();
	}

	/**
	 * 検索条件に取得日（{@code date}）を指定し、
	 * 未削除の銘柄（stocks）情報一覧総数を返す。
	 *
	 * @param date 取得日
	 * @return 未削除の銘柄（stocks）情報一覧総数を返す。
	 * @since 1.1.0
	 */
	protected static int getTotalCountByDate(Date date) {
		String sql = "SELECT count(*) as count FROM stocks WHERE delete_flg = false AND date = :date";
		SqlRow sqlRow = Ebean.createSqlQuery(sql)
				.setParameter("date", date)
				.findUnique();

		return sqlRow.getInteger("count");
	}
}
