package com.shyc.yc_audit.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 合同信息
 * 
 * @author xiaoping.shan
 *
 */
public class ContractInfo implements BaseData {

	private static final long serialVersionUID = 1L;

	private String contractId;
	private String date1;
	private String startDate;
	private String endDate;
	private String supplierName;
	private String txt7;


	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub

	
		contractId = JsonUtil.getJsonString(jo, "contractId");
		date1 = JsonUtil.getJsonString(jo, "date1");
		startDate = JsonUtil.getJsonString(jo, "startDate");
		endDate = JsonUtil.getJsonString(jo,"endDate");
		supplierName = JsonUtil.getJsonString(jo,"supplierName");
		txt7 = JsonUtil.getJsonString(jo,"txt7");
		
		
	
	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getTxt7() {
		return txt7;
	}

	public void setTxt7(String txt7) {
		this.txt7 = txt7;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
