package com.shyc.yc_audit.http;



import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.shyc.yc_audit.data.ContractIntroduction;

import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.util.JsonUtil;

/**
 * 合同列表
 * 
 * @author xiaoping.shan
 *
 */
public class HttpContractListClient extends BaseAsynHttpClient {
	
	@Override
	public String[] getPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				HttpAdress.ACTION,
				"userName",
				"lv"
		};
	}
	

	private String level;
	private ArrayList<ContractIntroduction> list;
	@Override
	protected void parerAsynHcResponse(String content) {
		// TODO Auto-generated method stub

		try {
			JSONObject jo = new JSONObject(content);
			level = JsonUtil.getJsonString(jo,"level");
			String contractStr = JsonUtil.getJsonString(jo,"contract");
			list = ContractIntroduction.getList(contractStr);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public ArrayList<ContractIntroduction> getList() {
		return list;
	}
	public void setList(ArrayList<ContractIntroduction> list) {
		this.list = list;
	}

}
