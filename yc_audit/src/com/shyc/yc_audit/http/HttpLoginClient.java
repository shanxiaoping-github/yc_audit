package com.shyc.yc_audit.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.shyc.yc_audit.data.UserInfo;

import sxp.android.framework.application.SXPApplication;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.util.JsonUtil;
/**
 * 登录http
 * @author xiaoping.shan
 *
 */
public class HttpLoginClient extends BaseAsynHttpClient{
	
	private String status;
	
	private UserInfo userInfo;
	@Override
	protected void parerAsynHcResponse(String content) {
		// TODO Auto-generated method stub
		JSONObject jo;
		try {
			jo = new JSONObject(content);
			status = JsonUtil.getJsonString(jo,"status");
			if(status.equals("0")){
				String userInfoStr = JsonUtil.getJsonString(jo,"userInfo");
				userInfo = new UserInfo();
				userInfo.parser(new JSONObject(userInfoStr));
				SXPApplication.getInstance().getSXPRuntimeContext().savaData(UserInfo.class.getName(),userInfo);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String[] getPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				HttpAdress.ACTION,
				"userName",
				"passWord"
		};
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	

}
