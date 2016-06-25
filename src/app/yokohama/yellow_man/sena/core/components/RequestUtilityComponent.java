package yokohama.yellow_man.sena.core.components;

import java.util.Map;

import play.mvc.Http.MultipartFormData;
import play.mvc.Http.Request;

/**
 * Requestに関するユーティリティクラス。
 * @author yellow-man
 * @since 1.1
 */
public class RequestUtilityComponent {

	/** ブランク文字列 */
	public static final String BLANK = "";

	/**
	 * リクエストからkeyのクエリ文字列を取得する。
	 * @param request リクエスト
	 * @param key キー
	 * @return クエリ文字列
	 * @since 1.1
	 */
	public static String getQueryString(Request request, String key) {
		if (request == null
				|| key == null
				|| key.length() <= 0) {

			AppLogger.debug("Parameter error：request=" + request + ", key=" + key);
			return null;
		}
		return request.getQueryString(key);
	}

	/**
	 * リクエストからkeyのFrom文字列を取得する。
	 * @param request リクエスト
	 * @param key キー
	 * @return Post文字列
	 * @since 1.1
	 */
	public static String getFromPostString(Request request, String key) {
		if (request == null
				|| request.body() == null
				|| request.body().asMultipartFormData() == null
				|| key == null
				|| key.length() <= 0) {

			AppLogger.debug("Parameter error：request=" + request + ", key=" + key);
			return null;
		}

		MultipartFormData form = request.body().asMultipartFormData();
		if (form == null
				|| form.asFormUrlEncoded() == null
				|| form.asFormUrlEncoded().get(key) == null
				|| form.asFormUrlEncoded().get(key).length <= 0) {

			AppLogger.debug("Form error：form=" + form + ", key=" + key);
			return null;
		}

		String str = form.asFormUrlEncoded().get(key)[0];
		return str;
	}

	/**
	 * リクエストからkeyのFrom文字列を取得する。
	 * @param request リクエスト
	 * @param key キー
	 * @return Post文字列
	 * @since 1.1
	 */
	public static String[] getFromPostStrings(Request request, String key) {
		if (request == null
				|| request.body() == null
				|| request.body().asMultipartFormData() == null
				|| key == null
				|| key.length() <= 0) {

			AppLogger.debug("Parameter error：request=" + request + ", key=" + key);
			return null;
		}

		MultipartFormData form = request.body().asMultipartFormData();
		if (form == null
				|| form.asFormUrlEncoded() == null
				|| form.asFormUrlEncoded().get(key) == null) {

			AppLogger.debug("Form error：form=" + form + ", key=" + key);
			return null;
		}

		String[] str = form.asFormUrlEncoded().get(key);
		return str;
	}

	/**
	 * リクエストからPostパラメータのMapを取得する。
	 * @param request リクエスト
	 * @return PostパラメータのMap
	 * @since 1.1
	 */
	public static Map<String, String[]> getFromPostMap(Request request) {
		if (request == null
				|| request.body() == null
				|| request.body().asMultipartFormData() == null) {

			AppLogger.debug("Parameter error：request=" + request);
			return null;
		}

		MultipartFormData form = request.body().asMultipartFormData();
		if (form == null
				|| form.asFormUrlEncoded() == null) {

			AppLogger.debug("Form error：form=" + form);
			return null;
		}
		return form.asFormUrlEncoded();
	}

	/**
	 * リクエストからkeyのFrom文字列を取得する。
	 * @param request リクエスト
	 * @param key キー
	 * @return Post文字列
	 * @since 1.1
	 */
	public static String getFromPostStringNullToBlank(Request request, String key) {
		if (request == null
				|| request.body() == null
				|| request.body().asMultipartFormData() == null
				|| key == null
				|| key.length() <= 0) {

			AppLogger.debug("Parameter error：request=" + request + ", key=" + key);
			return BLANK;
		}

		MultipartFormData form = request.body().asMultipartFormData();
		if (form == null
				|| form.asFormUrlEncoded() == null
				|| form.asFormUrlEncoded().get(key) == null
				|| form.asFormUrlEncoded().get(key).length <= 0) {

			AppLogger.debug("Form error：form=" + form + ", key=" + key);
			return BLANK;
		}

		String str = form.asFormUrlEncoded().get(key)[0];
		return (str == null) ? BLANK : str;
	}
}
