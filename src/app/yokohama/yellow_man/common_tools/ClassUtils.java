package yokohama.yellow_man.common_tools;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

/**
 * Class関する付属情報を提供します。
 *
 * @author yellow-man
 * @since 1.0
 */
public class ClassUtils {

	/** Logger定義 */
	private static Logger LOGGER = (Logger) LoggerFactory.getLogger(ClassUtils.class.getName());

	/**
	 * このメソッドを参照しているメソッド名を返します。
	 *
	 * @return このメソッドを参照しているメソッド名を返します。
	 * @since 1.0
	 */
	public static String getMethodName() {
		Throwable throwable = new Throwable();
		String methodName = "";
		StackTraceElement[] stackTrace = throwable.getStackTrace();

		if (stackTrace != null) {
			if(stackTrace.length > 2){
				methodName = stackTrace[2].getMethodName();
			}
		}
		return methodName;
	}

}
