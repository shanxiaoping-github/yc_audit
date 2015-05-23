package com.shyc.yc_audit.http;

import sxp.android.framework.http.BaseAsynHttpClient;
/**
 * 提交审核结果
 * @author xiaoping.shan
 *
 */
public class HttpContractSubmitResultClient extends BaseAsynHttpClient{
	
	
//	contractId	varchar(200)	Y	1	合同编号
//	lv	int(2)	Y	1	审核级别
//	userName	varchar(64)	Y	1	用户名
//	status	int(11)	Y	1	审核结果(单选框：通过or不通过)
//	opinion	varchar(500）	Y	1	审核意见

	
	@Override
	public String[] getPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				HttpAdress.ACTION,
				"contractId",
				"lv",
				"userName",
				"status",
				"opinion"
	    };
	}

	@Override
	protected void parerAsynHcResponse(String content) {
		// TODO Auto-generated method stub
		System.out.print("sss");
	}

}
