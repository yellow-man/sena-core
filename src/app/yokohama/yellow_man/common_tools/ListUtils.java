package yokohama.yellow_man.common_tools;

import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * リスト操作に関する機能を提供します。
 *
 * @author yellow-man
 * @since 1.0.0
 */
public class ListUtils {

	/** Logger定義 */
	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(ListUtils.class.getName());

	/**
	 * 引数の{@code list}要素をカンマ区切りの文字列として返します。
	 * 値が{@code null}または、{@code empty}の場合、空文字列（ブランク）を返します。
	 * @param list リスト
	 * @return カンマ区切りの文字列。
	 * @since 1.0.0
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
