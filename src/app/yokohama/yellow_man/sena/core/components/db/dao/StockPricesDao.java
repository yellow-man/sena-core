package yokohama.yellow_man.sena.core.components.db.dao;

import java.util.ArrayList;
import java.util.List;

import yokohama.yellow_man.sena.core.components.db.StockPricesComponent;
import yokohama.yellow_man.sena.core.models.AppModel;
import yokohama.yellow_man.sena.core.models.StockPrices;

/**
 * 株価（stock_prices）モデルのネイティブクエリによる操作クラス。
 * <p>アプリケーションとは疎結合としたい為、直接の参照は行わない。
 * <br>利用する場合、{@link StockPricesComponent}を経由しアクセスする。
 *
 * @author yellow-man
 * @since 1.2.0
 */
public class StockPricesDao extends AppDao {

	/**
	 * 株価（stock_prices）モデルのバルクインサートを行う。
	 * @param dataList バルクインサート対象リスト
	 * @return INSERT結果件数を返す。
	 * @since 1.2.0
	 */
	protected static int executeBulkInsert(List<StockPrices> dataList) {

		// 基底クラスの executeBulkInsert に受け渡すパラメータを生成
		Class<? extends AppModel> modelClass = StockPrices.class;
		String tableName = "stock_prices";
		List<String> excludeColumnNameList = new ArrayList<String>();
		excludeColumnNameList.add("id");

		// バルクインサート実行
		return AppDao.executeBulkInsert(dataList, modelClass, tableName, excludeColumnNameList);
	}
}
