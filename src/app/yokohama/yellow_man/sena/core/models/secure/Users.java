package yokohama.yellow_man.sena.core.models.secure;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * ユーザー（users）モデル。
 *
 * @author yellow-man
 * @since 1.1
 */
@SuppressWarnings("serial")
@Entity
public class Users extends AppSecureModel {

	/** アカウントID */
	@Column(name = "account_id")
	public String accountId;

	/** パスワード */
	@Column(name = "password")
	public String password;

	/** ニックネーム */
	@Column(name = "nickname")
	public String nickname;

	/** アクセストークン */
	@Column(name = "access_token")
	public String accessToken;

	/** 利用日時 */
	@Column(name = "use_datetime")
	public Date useDatetime;

	/** 検索用 */
	public static Finder<Long, Users> find =
			new Finder<Long, Users>(Long.class, Users.class).on(AppSecureModel.EBEAN_SERVER_DB_SECURE);

}
