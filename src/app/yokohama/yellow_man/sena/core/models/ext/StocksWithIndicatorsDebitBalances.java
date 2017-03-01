package yokohama.yellow_man.sena.core.models.ext;

import javax.persistence.Entity;
import javax.persistence.Table;

import yokohama.yellow_man.sena.core.models.DebitBalances;
import yokohama.yellow_man.sena.core.models.Indicators;
import yokohama.yellow_man.sena.core.models.Stocks;

/**
 * 銘柄（stocks）モデル拡張。
 * 指標情報、信用残情報を保持する。
 *
 * @author yellow-man
 * @since 1.1.0
 */
@SuppressWarnings("serial")
@Entity @Table(name="stocks")
public class StocksWithIndicatorsDebitBalances extends Stocks {

	/** 指標（indicators）モデル */
	public Indicators indicators;

	/** 信用残（debit_balances）モデル */
	public DebitBalances debitBalances;
}
