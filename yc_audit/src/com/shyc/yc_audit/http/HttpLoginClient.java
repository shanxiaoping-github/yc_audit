package com.shyc.yc_audit.http;

import sxp.android.framework.http.BaseAsynHttpClient;
/**
 * 登录http
 * @author xiaoping.shan
 *
 */
public class HttpLoginClient extends BaseAsynHttpClient{

	@Override
	protected void parerAsynHcResponse(String content) {
		// TODO Auto-generated method stub
		System.out.print(content);
	}
	@Override
	public String[] getPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				"userName",
				"passWord"
		};
	}
	

}
