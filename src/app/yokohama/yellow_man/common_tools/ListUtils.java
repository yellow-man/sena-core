package yokohama.yellow_man.common_tools;

import java.util.List;

/**
 * リスト操作に関する機能を提供します。
 *
 * @author yellow-man
 * @since 1.0
 */
public class ListUtils {

	/**
	 * 引数の{@code list}要素をカンマ区切りの文字列として返します。
	 * 値が{@code null}または、{@code empty}の場合、空文字列（ブランク）を返します。
	 * @param list リスト
	 * @return カンマ区切りの文字列。
	 */
	public static String toString(List<?> list) {
		if (CheckUtils.isEmpty(list)) {
			return "";
		}

		StringBuffer buff = new StringBuffer();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			buff.append(list.get(i)).append(",");
		}

		if (buff.length() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		return buff.toString();
	}
}
