package yokohama.yellow_man.sena.core.models.secure;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 口座銘柄（account_stocks）モデル。
 *
 * @author yellow-man
 * @since 1.1
 */
@SuppressWarnings("serial")
@Entity
public class AccountStocks extends AppSecureModel {

	/** ユーザーID */
	@Column(name = "users_id")
	public Long usersId;

	/** 取得日時 */
	@Column(name = "acquisition_datetime")
	public Date acquisitionDatetime;

	/** 銘柄コード */
	@Column(name = "stock_code")
	public Integer stockCode;

	/** 取引種別（1..国内株式、2..金額・株数指定取引、3..信用建玉（買建）、4..信用建玉（売建）） */
	@Column(name = "type")
	public Integer type;

	/** 大引けを表す日付（null..取引中、日付..取引終了（終値）、PTS夜間取引は考慮しない） */
	@Column(name = "closing_date")
	public Date closingDate;

	/** 大引けを表すフラグ（1：大引け、0：取引中） */
	@Column(name = "closing_flg")
	public Boolean closingFlg;

	/** 保有株数（整数部：15桁、小数部：10桁） */
	@Column(name = "amount")
	public BigDecimal amount;

	/** 平均取得単価（整数部：15桁、小数部：10桁） */
	@Column(name = "average_cost")
	public BigDecimal averageCost;

	/** 現在値（整数部：15桁、小数部：5桁） */
	@Column(name = "current_value")
	public BigDecimal currentValue;

	/** 時価評価額（「保有株数 * 現在値」で算出された値、整数部：15桁、小数部：10桁） */
	@Column(name = "market_value")
	public BigDecimal marketValue;

	/** 評価損益（「（保有株数 * 現在値） - （保有株数 * 平均取得単価）」で算出された値、整数部：15桁、小数部：10桁） */
	@Column(name = "gain_loss")
	public BigDecimal gainLoss;

	/** 損益率（「（評価損益 / （保有株数 * 現在値）） * 100」で算出された値、整数部：8桁、小数部：2桁） */
	@Column(name = "gain_loss_rate")
	public BigDecimal gainLossRate;

	/** 検索用 */
	public static Finder<Long, AccountStocks> find =
			new Finder<Long, AccountStocks>(Long.class, AccountStocks.class).on(AppSecureModel.EBEAN_SERVER_DB_SECURE);

}
