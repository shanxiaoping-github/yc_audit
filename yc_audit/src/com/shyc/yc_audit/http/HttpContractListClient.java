package com.shyc.yc_audit.http;



import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.shyc.yc_audit.data.ContractIntroduction;

import sxp.android.framework.data.JsonDataFactory;
import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.util.JsonUtil;
import sxp.android.framework.util.StringUtil;

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
				"page"
		};
	}
	
    private ArrayList<ContractIntroduction> list = new ArrayList<ContractIntroduction>();
 
	@Override
	protected void parerAsynHcResponse(String content) {
		// TODO Auto-generated method stub

		try {
			JSONObject jo = new JSONObject(content);
			String contractJsonStr = JsonUtil.getJsonString(jo,"contract");
			if(StringUtil.isNotJsonEmpty(contractJsonStr)){
				list = JsonDataFactory.getJsonDataArray(contractJsonStr,ContractIntroduction.class);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<ContractIntroduction> getList() {
		return list;
	}

	public void setList(ArrayList<ContractIntroduction> list) {
		this.list = list;
	}
	

}
