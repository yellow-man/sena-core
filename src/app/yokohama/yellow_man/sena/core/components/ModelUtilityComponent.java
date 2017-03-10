package yokohama.yellow_man.sena.core.components;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import yokohama.yellow_man.sena.core.models.AppModel;

/**
 * Modelに関するユーティリティクラス。
 * @author yellow-man
 * @since 1.1.0
 */
public class ModelUtilityComponent {

	/**
	 * モデルクラスからカラム名をキー、カラムのプロパティ名を値としたのマップを取得する。
	 * @param modelClass モデルクラス
	 * @return Map＜カラム名, プロパティ名＞
	 * @since 1.1.0
	 */
	public static Map<String, String> getColumnMap(Class<? extends AppModel> modelClass) {
		return getColumnMap(modelClass, null);
	}

	/**
	 * モデルクラスからカラム名をキー、カラムのプロパティ名を値としたのマップを取得する。
	 * @param modelClass モデルクラス
	 * @param excludeColumnNameList 返却値に含めないカラム名リスト
	 * @return Map＜カラム名, プロパティ名＞
	 * @since 1.1.0
	 */
	public static Map<String, String> getColumnMap(Class<? extends AppModel> modelClass, List<String> excludeColumnNameList) {
		Map<String, String> columnMap = new HashMap<String, String>();
		Field[] fields = modelClass.getFields();
		for (Field field : fields) {
			// フィールドの属性（修飾子）を取得
			int mod = field.getModifiers();

			// 静的要素は除外
			if (Modifier.isStatic(mod)) {
				continue;
			}

			// アノテーションを取得
			Column annotation = field.getAnnotation(Column.class);
			if (annotation != null) {
				// 除外リストに含まれていない場合は追加
				if (excludeColumnNameList == null || !excludeColumnNameList.contains(annotation.name())) {
					columnMap.put(annotation.name(), field.getName());
				}
			}
		}
		return columnMap;
	}
}
