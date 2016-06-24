package yokohama.yellow_man.sena.core.models.secure;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 口座サマリー（account_summaries）モデル。
 *
 * @author yellow-man
 * @since 1.1
 */
@SuppressWarnings("serial")
@Entity
public class AccountSummaries extends AppSecureModel {

	/** ユーザーID */
	@Column(name = "users_id")
	public Long usersId;

	/** 取得日時 */
	@Column(name = "acquisition_datetime")
	public Date acquisitionDatetime;

	/** 大引けを表す日付（null..取引中、日付..取引終了（終値）、PTS夜間取引は考慮しない） */
	@Column(name = "closing_date")
	public Date closingDate;

	/** 大引けを表すフラグ（1：大引け、0：取引中） */
	@Column(name = "closing_flg")
	public Boolean closingFlg;

	/** 信用建余力 */
	@Column(name = "free_margin")
	public Long freeMargin;

	/** 現引可能額 */
	@Column(name = "stock_purchased")
	public Long stockPurchased;

	/** 委託保証金現金 */
	@Column(name = "initial_margin_cache")
	public Long initialMarginCache;

	/** 代用有価証券評価額合計 */
	@Column(name = "initial_margin_value")
	public Long initialMarginValue;

	/** 評価損・決済損益・支払諸経費等合計 */
	@Column(name = "payment_expenses_total")
	public Long paymentExpensesTotal;

	/** 実質保証金 */
	@Column(name = "initial_margin")
	public Long initialMargin;

	/** 建代金合計 */
	@Column(name = "open_interest")
	public Long openInterest;

	/** 委託保証金率（整数部：8桁、小数部：2桁） */
	@Column(name = "maintenance_requirement")
	public BigDecimal maintenanceRequirement;

	/** 参考委託保証金率（整数部：8桁、小数部：2桁） */
	@Column(name = "ref_maintenance_requirement")
	public BigDecimal refMaintenanceRequirement;

	/** 買付余力 */
	@Column(name = "free_cache")
	public Long freeCache;

	/** 現金残高等 */
	@Column(name = "free_cache_etc")
	public Long freeCacheEtc;

	/** 株式 */
	@Column(name = "asset_value")
	public Long assetValue;

	/** 建玉評価損益額 */
	@Column(name = "margin_profit_loss")
	public Long marginProfitLoss;

	/** 計 */
	@Column(name = "account_balance")
	public Long accountBalance;

	/** 検索用 */
	public static Finder<Long, AccountSummaries> find =
			new Finder<Long, AccountSummaries>(Long.class, AccountSummaries.class).on(AppSecureModel.EBEAN_SERVER_DB_SECURE);

}
