package yokohama.yellow_man.common_tools;

import java.math.BigDecimal;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * 数値操作に関する機能を提供します。
 *
 * @author yellow-man
 * @since 1.0.0
 */
public class NumberUtils {

	/** Logger定義 */
	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(NumberUtils.class.getName());

	/**
	 * 引数の文字列を{@link BigDecimal}型に変換します。
	 * 変換に失敗した場合、値0の{@code BigDecimal}型を返します。
	 *
	 * @param str {@code BigDecimal}に変換する文字列
	 * @return 変換後の値
	 * @since 1.0.0
	 */
	public static BigDecimal toBigDecimal(String str) {
		return toBigDecimal(str, "0");
	}

	/**
	 * 引数の文字列を{@link BigDecimal}型に変換します。
	 * 変換に失敗した場合、{@code defaultValue}の値で{@code BigDecimal}を返します。
	 *
	 * @param str {@code BigDecimal}に変換する文字列
	 * @param defaultValue デフォルト値（{@code null}をデフォルトとすることも可能です。）
	 * @return 変換後の値
	 * @since 1.0.0
	 */
	public static BigDecimal toBigDecimal(String str, String defaultValue) {
		if (str == null) {
			if (defaultValue == null) {
				return null;
			}
			return new BigDecimal(defaultValue);
		}
		try {
			return new BigDecimal(str);
		} catch (Exception e) {
			LOGGER.warn("変換に失敗しました。", e);
		}
		if (defaultValue == null) {
			return null;
		}
		return new BigDecimal(defaultValue);
	}

}
