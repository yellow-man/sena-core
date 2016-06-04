package yokohama.yellow_man.sena.core.components;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import play.Play;
import yokohama.yellow_man.common_tools.StringUtils;

/**
 * アプリ共通{@code Logger}クラス。
 * <p>このアプリケーションで出力するログは、
 * ログの一元管理の為このクラスを経由して出力を行う。
 * <p>ログレベルの定義：
 * <ul>
 * <li>{@code ERROR}：外部連携のエラー、DBデータ登録時のエラー、その他異常系のエラー</li>
 * <li>{@code WARN}：パラメータ系の異常データ、バリデーションエラー</li>
 * <li>{@code INFO}：DBデータ登録時の内容、複雑な処理のトレース情報</li>
 * <li>{@code DEBUG}：開発時のデバッグ情報</li>
 * </ul>
 *
 * @author yellow-man
 * @since 1.0
 */
public class AppLogger {

	/**
	 * {@code Logger}名（デフォルト：{@code AppLogger}）
	 * {@code application.conf}ファイル{@code app_logger.name}キーにて値の変更可。
	 */
	public static final String APP_LOGGER_NAME = Play.application().configuration().getString("app_logger.name", AppLogger.class.getSimpleName());

	/** {@code MDC}フォーマット定義（【注意】スレッドローカルな為、キー使用後は必ず{@link #removeMDC(String)}メソッドを呼び出し破棄する）：クラスのソースコードファイル名、行番号を出力する。 */
	public static final String MDC_FORMAT_KEY_FILELINE  = "fileline";

	/** {@code MDC}フォーマット定義（【注意】スレッドローカルな為、キー使用後は必ず{@link #removeMDC(String)}メソッドを呼び出し破棄する）：セッションID（ユーザーID等） */
	public static final String MDC_FORMAT_KEY_SID = "sid";

	/** Logger定義 */
	private static Logger MY_LOGGER = (Logger) LoggerFactory.getLogger(APP_LOGGER_NAME);

	/**
	 * 新しくFileAppender定義したロガーに差し替える。
	 *
	 * @param loggerName ロガー名
	 * @param file ファイル出力先
	 * @since 1.0
	 */
	public static void addLoggerFileAppender(String loggerName, String file) {
		LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

		// 新しいEncoderを生成する
		PatternLayoutEncoder layout = new PatternLayoutEncoder();
		layout.setPattern("[%date{yyyy-MM-dd HH:mm:ss:SSS}] [%level] [%.5thread] [%logger] [%X{fileline}] [%msg] %xEx%n");
		layout.setContext(context);
		layout.start();

		// 新しいAppenderを生成する
		FileAppender<ILoggingEvent> fileAppender = new FileAppender<ILoggingEvent>();
		fileAppender.setFile(file);
		fileAppender.setAppend(false);
		fileAppender.setEncoder(layout);
		fileAppender.setContext(context);
		fileAppender.start();

		Logger logger = (Logger) LoggerFactory.getLogger(loggerName);
		logger.addAppender(fileAppender);
		logger.setLevel(MY_LOGGER.getLevel());
		logger.setAdditive(true);

		// ロガーの差し替え
		MY_LOGGER = logger;
	}

	/**
	 * 通常のロガーに差し替える。
	 *
	 * @since 1.0
	 */
	public static void resetLogger() {
		// ロガーの差し替え
		MY_LOGGER = (Logger) LoggerFactory.getLogger(APP_LOGGER_NAME);
	}

	/**
	 * ログレベル{@code ERROR}の出力を行う。
	 * <p>出力定義
	 * <ul>
	 * <li>外部連携のエラー
	 * <li>DBデータ登録時のエラー
	 * <li>その他異常系のエラー
	 * </ul>
	 * @param message ログ出力メッセージ
	 * @since 1.0
	 */
	public static void error(String message) {
		if (MY_LOGGER.isErrorEnabled()) {
			putMDC(MDC_FORMAT_KEY_FILELINE, printFileLine());
			MY_LOGGER.error(message);

			// キーの破棄
			removeMDC(MDC_FORMAT_KEY_FILELINE);
		}
	}

	/**
	 * ログレベル{@code ERROR}の出力を行う。
	 * <p>出力定義
	 * <ul>
	 * <li>外部連携のエラー
	 * <li>DBデータ登録時のエラー
	 * <li>その他異常系のエラー
	 * </ul>
	 * @param message ログ出力メッセージ
	 * @param t Throwable
	 * @since 1.0
	 */
	public static void error(String message, Throwable t) {
		if (MY_LOGGER.isErrorEnabled()) {
			putMDC(MDC_FORMAT_KEY_FILELINE, printFileLine());
			MY_LOGGER.error(message, t);

			// キーの破棄
			removeMDC(MDC_FORMAT_KEY_FILELINE);
		}
	}

	/**
	 * ログレベル{@code WARN}の出力を行う。
	 * <p>出力定義
	 * <ul>
	 * <li>パラメータ系の異常データ
	 * <li>バリデーションエラー
	 * </ul>
	 * @param message ログ出力メッセージ
	 * @since 1.0
	 */
	public static void warn(String message) {
		if (MY_LOGGER.isWarnEnabled()) {
			putMDC(MDC_FORMAT_KEY_FILELINE, printFileLine());
			MY_LOGGER.warn(message);

			// キーの破棄
			removeMDC(MDC_FORMAT_KEY_FILELINE);
		}
	}

	/**
	 * ログレベル{@code WARN}の出力を行う。
	 * <p>出力定義
	 * <ul>
	 * <li>パラメータ系の異常データ
	 * <li>バリデーションエラー
	 * </ul>
	 * @param message ログ出力メッセージ
	 * @param t Throwable
	 * @since 1.1
	 */
	public static void warn(String message, Throwable t) {
		if (MY_LOGGER.isWarnEnabled()) {
			putMDC(MDC_FORMAT_KEY_FILELINE, printFileLine());
			MY_LOGGER.error(message, t);

			// キーの破棄
			removeMDC(MDC_FORMAT_KEY_FILELINE);
		}
	}

	/**
	 * ログレベル{@code INFO}の出力を行う。
	 * <p>出力定義
	 * <ul>
	 * <li>DBデータ登録時の内容
	 * <li>複雑な処理のトレース情報
	 * </ul>
	 * @param message ログ出力メッセージ
	 * @since 1.0
	 */
	public static void info(String message) {
		if (MY_LOGGER.isInfoEnabled()) {
			putMDC(MDC_FORMAT_KEY_FILELINE, printFileLine());
			MY_LOGGER.info(message);

			// キーの破棄
			removeMDC(MDC_FORMAT_KEY_FILELINE);
		}
	}

	/**
	 * ログレベル{@code DEBUG}の出力を行う。
	 * <p>出力定義
	 * <ul>
	 * <li>開発時のデバッグ情報
	 * </ul>
	 * @param message ログ出力メッセージ
	 * @since 1.0
	 */
	public static void debug(String message) {
		if (MY_LOGGER.isDebugEnabled()) {
			putMDC(MDC_FORMAT_KEY_FILELINE, printFileLine());
			MY_LOGGER.debug(message);

			// キーの破棄
			removeMDC(MDC_FORMAT_KEY_FILELINE);
		}
	}

	/**
	 * MDCにキーと値をセットする。
	 * ※キー使用後は必ず{@link #removeMDC(String)}メソッドを呼び出し破棄する。
	 * @param key キー
	 * @param val 値
	 * @since 1.0
	 */
	public static void putMDC(String key, String val) {
		if (StringUtils.isEmptyWithTrim(key)) {
			return;
		}
		MDC.put(key, val);
	}

	/**
	 * MDCにセットしたキーを破棄する。
	 * @param key キー
	 * @since 1.0
	 */
	public static void removeMDC(String key) {
		if (StringUtils.isEmptyWithTrim(key)) {
			return;
		}
		MDC.remove(key);
	}

	/**
	 * クラスのソースコードファイル名、行番号の文字列を返す。
	 * @return クラスのソースコードファイル名、行番号の文字列。
	 * @since 1.0
	 */
	private static String printFileLine() {
		Throwable throwable = new Throwable();
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stackTrace = throwable.getStackTrace();

		if (stackTrace != null) {
			if(stackTrace.length > 2){
				sb.append(stackTrace[2].getClassName()).append(".")
					.append(stackTrace[2].getMethodName()).append("(")
					.append(stackTrace[2].getFileName()).append(":")
					.append(stackTrace[2].getLineNumber()).append(")");
			}
		}
		return sb.toString();
	}

	/**
	 * 例外発生箇所の{@code printStackTrace}文字列を返す。
	 * @param throwable Throwable
	 * @return {@code printStackTrace}文字列。
	 * @since 1.0
	 */
	public static String printTrace(Throwable throwable) {
		StringBuffer sb = new StringBuffer("printTrace[");

		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		pw.flush();
		sb.append(sw.toString());

		sb.append("]");
		return sb.toString();
	}
}
