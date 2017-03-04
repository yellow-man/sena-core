package yokohama.yellow_man.sena.core.components;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;

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
	public static Map<String, String> getColumnMap(Class<?> modelClass) {
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
				columnMap.put(annotation.name(), field.getName());
			}
		}
		return columnMap;
	}
}
