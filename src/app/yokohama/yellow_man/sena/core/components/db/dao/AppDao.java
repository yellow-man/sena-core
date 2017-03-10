package yokohama.yellow_man.sena.core.components.db.dao;

import java.util.List;
import java.util.Map;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

import yokohama.yellow_man.common_tools.FieldUtils;
import yokohama.yellow_man.sena.core.components.ModelUtilityComponent;
import yokohama.yellow_man.sena.core.models.AppModel;

/**
 * Daoの基底クラス。
 * <p>各Daoはこのクラスを継承する。
 *
 * @author yellow-man
 * @since 1.2.0
 */
public class AppDao {

	/**
	 * モデルのバルクインサートを行う。
	 * @param dataList バルクインサート対象リスト
	 * @param modelClass モデルクラス
	 * @param tableName テーブル名
	 * @param excludeColumnNameList 除外カラム名のリスト
	 * @return INSERT結果件数を返す。
	 * @since 1.2.0
	 */
	protected static int executeBulkInsert(
			List<? extends AppModel> dataList, Class<? extends AppModel> modelClass, String tableName, List<String> excludeColumnNameList) {

		Map<String, String> colMap = ModelUtilityComponent.getColumnMap(modelClass, excludeColumnNameList);
		// SQLテンプレート作成
		StringBuilder sqlStringBuffer =
				new StringBuilder("INSERT INTO " + tableName + " ( ");

		StringBuilder colBuilder = new StringBuilder();
		for (Map.Entry<String, String> entry : colMap.entrySet()) {
			String columnName = entry.getKey();
			colBuilder.append(", " + columnName);
		}
		// 先頭のカンマを除去
		colBuilder.deleteCharAt(0);
		sqlStringBuffer.append(colBuilder + " ) VALUES ");

		// バインド変数作成
		for (int i = 0; i < dataList.size(); i++) {
			if (0 == i) {
				sqlStringBuffer.append(" ( ");
			} else {
				sqlStringBuffer.append(", ( ");
			}

			colBuilder = new StringBuilder();
			for (Map.Entry<String, String> entry : colMap.entrySet()) {
				String columnName = entry.getKey();
				colBuilder.append(", :" + columnName + "_" + i);
			}
			// 先頭のカンマを除去
			colBuilder.deleteCharAt(0);
			sqlStringBuffer.append(colBuilder).append(" ) ");
		}

		// バルクインサート用SQL作成
		SqlUpdate sqlUpdate = Ebean.createSqlUpdate(sqlStringBuffer.toString());
		int j = 0;
		for (Object items : dataList) {
			// フィールド情報を取得する。
			Map<String, Object> fieldMap = FieldUtils.toMapField(items);

			for (Map.Entry<String, String> entry : colMap.entrySet()) {
				String columnName = entry.getKey();
				String paramName  = entry.getValue();
				sqlUpdate.setParameter(columnName + "_" + j, fieldMap.get(paramName));
			}
			j++;
		}

		// インサート実行
		return Ebean.execute(sqlUpdate);
	}
}
