package com.shyc.yc_audit.http;

import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.util.JsonUtil;
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

	
	private String flag;
	@Override
	protected void parerAsynHcResponse(String content) {
		// TODO Auto-generated method stub
		
		try {
			JSONObject jo  = new JSONObject(content);
			flag = JsonUtil.getJsonString(jo,"flag");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.print("sss");
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	

}
