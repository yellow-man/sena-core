package yokohama.yellow_man.sena.core.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 株価（stock_prices）モデル。
 *
 * @author yellow-man
 * @since 1.2.0
 * @version 1.2.2
 */
@SuppressWarnings("serial")
@Entity
public class StockPrices extends AppModel {

	/** 取得日 */
	@Column(name = "date")
	public Date date;

	/** 銘柄コード */
	@Column(name = "stock_code")
	public Integer stockCode;

	/** 始値（整数部：15桁、小数部：5桁） */
	@Column(name = "opening_price")
	public BigDecimal openingPrice;

	/** 高値（整数部：15桁、小数部：5桁） */
	@Column(name = "high_price")
	public BigDecimal highPrice;

	/** 安値（整数部：15桁、小数部：5桁） */
	@Column(name = "low_price")
	public BigDecimal lowPrice;

	/** 終値（整数部：15桁、小数部：5桁） */
	@Column(name = "closing_price")
	public BigDecimal closingPrice;

	/** 出来高（整数部：15桁、小数部：5桁） */
	@Column(name = "turnover")
	public BigDecimal turnover;

	/** 調整後終値	（分割実施前の終値を分割後の値に調整したもの、整数部：15桁、小数部：5桁） */
	@Column(name = "adjusted_closing_price")
	public BigDecimal adjustedClosingPrice;

	/** 分割フラグ（1：分割あり、0：分割無し） */
	@Column(name = "split_flg")
	public Boolean splitFlg = false;

	/** 分割前（※例：分割: 1株 → 1.2株） */
	@Column(name = "split_from")
	public BigDecimal splitFrom;

	/** 分割後（※例：分割: 1株 → 1.2株） */
	@Column(name = "split_to")
	public BigDecimal splitTo;
}
