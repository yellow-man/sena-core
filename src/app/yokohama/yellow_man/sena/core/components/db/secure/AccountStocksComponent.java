package yokohama.yellow_man.sena.core.components.db.secure;

import java.util.List;

import yokohama.yellow_man.sena.core.components.db.secure.dao.AccountStocksDao;
import yokohama.yellow_man.sena.core.models.secure.AccountStocks;

/**
 * 口座銘柄（account_stocks）モデルの操作を行うコンポーネントクラス。
 *
 * @author yellow-man
 * @since 1.0.0
 */
public class AccountStocksComponent extends AccountStocksDao {

	/**
	 * 口座銘柄（account_stocks）モデルのバルクインサートを行う。
	 *
	 * @param list バルクインサート対象リスト
	 * @return INSERT結果件数を返す。
	 * @since 1.0.0
	 * @see AccountStocksDao#executeBulkInsert(List)
	 */
	public static int executeBulkInsert(List<AccountStocks> list) {
		return AccountStocksDao.executeBulkInsert(list);
	}
}
