package com.shyc.yc_audit.data;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.data.JsonDataFactory;
import sxp.android.framework.util.JsonUtil;
import sxp.android.framework.util.StringUtil;

/**
 * 合同详情
 * 
 * @author xiaoping.shan
 *
 */
public class ContractDetail implements BaseData {

	private static final long serialVersionUID = 1L;
	private ContractInfo contractInfo;
	private ArrayList<ContractProducts> productList = new ArrayList<ContractProducts>();
	private String termName;
	private String orgName;
	private String crateContractOrg;
	private ArrayList<CheckInfo> checkInfos = new ArrayList<CheckInfo>();

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		String contractInfoStr = JsonUtil.getJsonString(jo, "contractInfo");
		if (StringUtil.isNotJsonEmpty(contractInfoStr)) {
			contractInfoStr = contractInfoStr.substring(1,
					contractInfoStr.length() - 1);
			contractInfo = new ContractInfo();
			try {
				contractInfo.parser(new JSONObject(contractInfoStr));
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		String contractProductStr = JsonUtil.getJsonString(jo,
				"contractProducts");
		if (StringUtil.isNotJsonEmpty(contractProductStr)) {
			productList = ContractProducts.getList(contractProductStr);
		}

		String bdPaytermStr = JsonUtil.getJsonString(jo, "bdPayterm");
		if (StringUtil.isNotJsonEmpty(bdPaytermStr)) {
			try {
				JSONObject bdJson = new JSONObject(bdPaytermStr);
				termName = JsonUtil.getJsonString(bdJson, "termname");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		orgName = JsonUtil.getJsonString(jo, "orgName");
		crateContractOrg = JsonUtil.getJsonString(jo, "crateContractOrg");

		String checkInfoStr = JsonUtil.getJsonString(jo, "hasCheck");
		if (StringUtil.isNotJsonEmpty(checkInfoStr)) {
			this.checkInfos = JsonDataFactory.getJsonDataArray(checkInfoStr, CheckInfo.class);
		}

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

	public ArrayList<CheckInfo> getCheckInfos() {
		return checkInfos;
	}

	public void setCheckInfos(ArrayList<CheckInfo> checkInfos) {
		this.checkInfos = checkInfos;
	}

}
