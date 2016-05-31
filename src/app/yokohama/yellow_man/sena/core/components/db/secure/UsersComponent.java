package yokohama.yellow_man.sena.core.components.db.secure;

import yokohama.yellow_man.sena.core.components.db.secure.dao.UsersDao;
import yokohama.yellow_man.sena.core.models.secure.Users;

/**
 * ユーザー（users）モデルの操作を行うコンポーネントクラス。
 *
 * @author yellow-man
 * @since 1.0
 */
public class UsersComponent extends UsersDao {

	/**
	 * ユーザー（userss）作成する。
	 * @param accountId アカウントID
	 * @param password パスワード
	 * @param nickname ニックネーム
	 * @return 作成したユーザーモデルを返す。
	 * @since 1.1
	 */
	public static Users create(String accountId, String password, String nickname) {
		return UsersDao.create(accountId, password, nickname);
	}
}
