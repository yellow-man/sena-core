package yokohama.yellow_man.sena.core.components.db.secure;

import yokohama.yellow_man.sena.core.components.db.secure.dao.UsersDao;
import yokohama.yellow_man.sena.core.models.secure.Users;

/**
 * ユーザー（users）モデルの操作を行うコンポーネントクラス。
 *
 * @author yellow-man
 * @since 1.0
 */
public class AccountStocksComponent extends UsersDao {

	/**
	 * ユーザー（users）作成する。
	 * @param accountId アカウントID
	 * @param password パスワード
	 * @param nickname ニックネーム
	 * @return 作成したユーザーモデルを返す。
	 * @since 1.1
	 */
	public static Users create(String accountId, String password, String nickname) {
		return UsersDao.create(accountId, password, nickname);
	}

	/**
	 * アクセストークンを指定し、ユーザー情報を取得する。
	 * @param accessToken アクセストークン
	 * @return 取得したユーザー情報を返す。
	 * @since 1.1
	 */
	public static Users getUsersByAccessToken(String accessToken) {
		Users users = Users.find.where()
				.eq("delete_flg", false)
				.eq("access_token", accessToken)
				.findUnique();

		return users;
	}
}
