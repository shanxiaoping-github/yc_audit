package com.shyc.yc_audit.data;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;
/**
 * 合同详情
 * @author xiaoping.shan
 *
 */
public class ContractDetail implements BaseData{
	
	private static final long serialVersionUID = 1L;
	private ContractInfo contractInfo;
	private ArrayList<ContractProducts> productList;
	private String termName;
	private String orgName;
	private String crateContractOrg;
	
	
	

	public void parser(JSONObject jo){
		// TODO Auto-generated method stub
		
		String contractInfoStr = JsonUtil.getJsonString(jo,"contractInfo");
		contractInfoStr = contractInfoStr.substring(1,contractInfoStr.length()-1);
		contractInfo = new ContractInfo();
		try {
			contractInfo.parser(new JSONObject(contractInfoStr));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String contractProductStr = JsonUtil.getJsonString(jo,"contractProducts"); 
		productList = ContractProducts.getList(contractProductStr);
		
		
		String bdPaytermStr = JsonUtil.getJsonString(jo,"bdPayterm");
		try {
			JSONObject bdJson = new JSONObject(bdPaytermStr);
			termName = JsonUtil.getJsonString(bdJson,"termname");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		orgName = JsonUtil.getJsonString(jo,"orgName");
		crateContractOrg = JsonUtil.getJsonString(jo,"crateContractOrg");
		
	
	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public ContractInfo getContractInfo() {
		return contractInfo;
	}

	public void setContractInfo(ContractInfo contractInfo) {
		this.contractInfo = contractInfo;
	}

	public ArrayList<ContractProducts> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<ContractProducts> productList) {
		this.productList = productList;
	}

	public String getTermName() {
		return termName;
	}

	public void setTermName(String termName) {
		this.termName = termName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCrateContractOrg() {
		return crateContractOrg;
	}

	public void setCrateContractOrg(String crateContractOrg) {
		this.crateContractOrg = crateContractOrg;
	}

}
