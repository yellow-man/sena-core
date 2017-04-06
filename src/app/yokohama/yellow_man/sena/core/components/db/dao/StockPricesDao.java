package yokohama.yellow_man.sena.core.components.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

import yokohama.yellow_man.common_tools.FieldUtils;
import yokohama.yellow_man.sena.core.components.ModelUtilityComponent;
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
 * @version 1.2.2
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

	/**
	 * 株価（stock_prices）モデルのアップデートを行う。
	 * @param data アップデート対象のデータ
	 * @param whereMap Where条件に指定するMap＜カラム名, 値＞
	 * @return UPDATE結果件数を返す。
	 * @since 1.2.2
	 */
	protected static int update(StockPrices data, Map<String, Object> whereMap) {
		String tableName = "stock_prices";

		// 除外設定（カラム名）
		List<String> excludeColumnNameList = new ArrayList<String>();
		excludeColumnNameList.add("id");

		Map<String, String> colMap = ModelUtilityComponent.getColumnMap(StockPrices.class, excludeColumnNameList);
		// SQL作成
		StringBuilder sqlStringBuilder =
				new StringBuilder("UPDATE " + tableName + " SET ");

		// 除外設定（プロパティ名）
		List<String> excludeList = new ArrayList<String>();
		excludeList.add("id");
		// フィールド情報を取得する。
		Map<String, Object> fieldMap = FieldUtils.toMapField(data, excludeList);

		// バインド変数
		StringBuilder updColBuilder = new StringBuilder();
		for (Map.Entry<String, String> entry : colMap.entrySet()) {
			String columnName = entry.getKey();
			String paramName = entry.getValue();
			if (fieldMap.containsKey(paramName)) {
				updColBuilder.append(", " + columnName + " = :" + columnName);
			}
		}
		updColBuilder.deleteCharAt(0);
		sqlStringBuilder.append(updColBuilder).append(" WHERE ");

		// WHERE句作成
		String whereAnd = " AND ";
		StringBuilder whereBuilder = new StringBuilder();
		for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
			String columnName = entry.getKey();
			Object value = entry.getValue();
			whereBuilder.append(whereAnd + columnName + " = " + value);
		}
		whereBuilder.delete(0, whereAnd.length());
		sqlStringBuilder.append(whereBuilder);

		// UPDATE用SQL作成
		SqlUpdate sqlUpdate = Ebean.createSqlUpdate(sqlStringBuilder.toString());

		// バインド変数に値をセット
		for (Map.Entry<String, String> entry : colMap.entrySet()) {
			String columnName = entry.getKey();
			String paramName = entry.getValue();
			if (fieldMap.containsKey(paramName)) {
				sqlUpdate.setParameter(columnName, fieldMap.get(paramName));
			}
		}

		// UPDATE実行
		return Ebean.execute(sqlUpdate);
	}
}
