package com.shyc.yc_audit.http;

import org.json.JSONException;
import org.json.JSONObject;

import com.shyc.yc_audit.data.ContractDetail;

import sxp.android.framework.http.BaseAsynHttpClient;
import sxp.android.framework.util.StringUtil;
/**
 * 审核详情
 * @author xiaoping.shan
 *
 */
public class HttpContractDetailClient extends BaseAsynHttpClient{
	
	
	@Override
	public String[] getPramasKeys() {
		// TODO Auto-generated method stub
		return new String[]{
				HttpAdress.ACTION,
				"contractId",
				"processId",
				"processNode"
		};
	}
	
	private ContractDetail contractDetail;
	@Override
	protected void parerAsynHcResponse(String content) {
		// TODO Auto-generated method stub
		try {
			if(!StringUtil.isJsonEmpty(content)){
				JSONObject jo  = new JSONObject(content);
				contractDetail  = new ContractDetail();
				contractDetail.parser(jo);
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public ContractDetail getContractDetail(){
		return contractDetail;
	}
	public void setContractDetail(ContractDetail contractDetail) {
		this.contractDetail = contractDetail;
	}

}
