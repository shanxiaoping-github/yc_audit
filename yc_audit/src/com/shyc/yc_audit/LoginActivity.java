package com.shyc.yc_audit;

import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.http.BaseAsynHttpClient.AsynHcResponseListener;
import sxp.android.framework.ui.BaseActivity;
import sxp.android.framework.util.StringUtil;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.shyc.yc_audit.http.HttpAdress;
import com.shyc.yc_audit.http.HttpLoginClient;
import com.shyc.yc_audit.util.ShowUtil;

/**
 * 登录
 * 
 * @author xiaoping.shan
 *
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

	private EditText userNameEdit;
	private EditText userPwdEdit;
	private Button loginNext;

	@Override
	protected void layout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.login_layout);
		userNameEdit = (EditText) findViewById(R.id.login_username);
		userPwdEdit = (EditText) findViewById(R.id.login_pwd);

		loginNext = (Button) findViewById(R.id.login_next);
		loginNext.setOnClickListener(this);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.login_next:
			login();
			break;

		}
	}

	/**
	 * 登录
	 */
	private void login() {
		String userNameStr = userNameEdit.getText().toString();
		String pwdStr = userPwdEdit.getText().toString();
		if (StringUtil.isEmpty(userNameStr)) {
			sxp.android.framework.util.ShowUtil.showShortToast(this, "用户名不能为空");
		} else if (StringUtil.isEmpty(pwdStr)) {
			sxp.android.framework.util.ShowUtil.showShortToast(this, "密码不能为空");

		} else {
			openActivity(ContractIntroductionActivity.class);
//			HttpLoginClient client = new HttpLoginClient();
//			client.cleanResponse();
//			client.addAsynHcResponseListenrt(new AsynHcResponseListener() {
//
//				public boolean onTimeout() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//
//				public boolean onSuccess(BaseAsynHttpClient asynHttpClient) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//
//				public boolean onEmpty() {
//					// TODO Auto-generated method stub
//					return false;
//				}
//			});
//			client.setPramas(new Object[] { userNameStr, pwdStr });
//			ShowUtil.openHttpDialog("登录中...");
//			client.subRequestGet(HttpAdress.LOGIN);

		}
	}

}
