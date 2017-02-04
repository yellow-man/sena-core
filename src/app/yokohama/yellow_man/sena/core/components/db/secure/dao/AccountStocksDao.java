package yokohama.yellow_man.sena.core.components.db.secure.dao;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;

import yokohama.yellow_man.sena.core.components.db.secure.AccountStocksComponent;
import yokohama.yellow_man.sena.core.models.secure.AccountStocks;
import yokohama.yellow_man.sena.core.models.secure.AppSecureModel;

/**
 * 口座銘柄（account_stocks）モデルのネイティブクエリによる操作クラス。
 * <p>アプリケーションとは祖結合としたい為、直接の参照は行わない。
 * <br>利用する場合、{@link AccountStocksComponent}を経由しアクセスする。
 *
 * @author yellow-man
 * @since 1.0.0
 */
public class AccountStocksDao {

	/**
	 * 口座銘柄（account_stocks）モデルのバルクインサートを行う。
	 * @param list バルクインサート対象リスト
	 * @return INSERT結果件数を返す。
	 * @since 1.0.0
	 */
	protected static int executeBulkInsert(List<AccountStocks> list) {

		// SQLテンプレート作成
		StringBuffer sqlStringBuffer =
				new StringBuffer("INSERT INTO account_stocks (users_id, acquisition_datetime, stock_code, type, closing_date, closing_flg, amount, average_cost, current_value, market_value, profit_loss, profit_loss_rate, created, modified, delete_flg) VALUES ");

		for (int i = 0; i < list.size(); i++) {
			if (0 == i) {
				sqlStringBuffer.append("(");
			} else {
				sqlStringBuffer.append(", (");
			}
			sqlStringBuffer
				.append(":users_id_").append(i).append(", ")
				.append(":acquisition_datetime_").append(i).append(", ")
				.append(":stock_code_").append(i).append(", ")
				.append(":type_").append(i).append(", ")
				.append(":closing_date_").append(i).append(", ")
				.append(":closing_flg_").append(i).append(", ")
				.append(":amount_").append(i).append(", ")
				.append(":average_cost_").append(i).append(", ")
				.append(":current_value_").append(i).append(", ")
				.append(":market_value_").append(i).append(", ")
				.append(":profit_loss_").append(i).append(", ")
				.append(":profit_loss_rate_").append(i).append(", ")
				.append(":created_").append(i).append(", ")
				.append(":modified_").append(i).append(", ")
				.append(":delete_flg_").append(i)
			.append(")");
		}

		// バルクインサート用SQL作成
		SqlUpdate sqlUpdate = Ebean.createSqlUpdate(sqlStringBuffer.toString());
		int j = 0;
		for (AccountStocks items : list) {
			sqlUpdate.setParameter("users_id_" + j, items.usersId);
			sqlUpdate.setParameter("acquisition_datetime_" + j, items.acquisitionDatetime);
			sqlUpdate.setParameter("stock_code_" + j, items.stockCode);
			sqlUpdate.setParameter("type_" + j, items.type);
			sqlUpdate.setParameter("closing_date_" + j, items.closingDate);
			sqlUpdate.setParameter("closing_flg_" + j, items.closingFlg);
			sqlUpdate.setParameter("amount_" + j, items.amount);
			sqlUpdate.setParameter("average_cost_" + j, items.averageCost);
			sqlUpdate.setParameter("current_value_" + j, items.currentValue);
			sqlUpdate.setParameter("market_value_" + j, items.marketValue);
			sqlUpdate.setParameter("profit_loss_" + j, items.profitLoss);
			sqlUpdate.setParameter("profit_loss_rate_" + j, items.profitLossRate);
			sqlUpdate.setParameter("created_" + j, items.created);
			sqlUpdate.setParameter("modified_" + j, items.modified);
			sqlUpdate.setParameter("delete_flg_" + j, items.deleteFlg);
			j++;
		}

		// インサート実行
		return Ebean.getServer(AppSecureModel.EBEAN_SERVER_DB_SECURE).execute(sqlUpdate);
	}
}
