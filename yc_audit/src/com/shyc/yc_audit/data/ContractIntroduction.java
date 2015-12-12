package com.shyc.yc_audit.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 合同简略数据
 * 
 * @author xiaoping.shan
 *
 */
public class ContractIntroduction implements BaseData {

	private static final long serialVersionUID = 1L;
	/**
	 * 合同编号
	 */
	private String serialNumber;
	/**
	 * 供货商名称
	 */
	private String supplierName;
	/**
	 * 送审人
	 */
	private String reviewOfPeople;
	/**
	 * 审核状态
	 */
	private String auditStatus;
	/**
	 * 审核编号
	 */
	private String processId;
	/**
	 * 审核节点
	 */
	private String processNode;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		serialNumber = JsonUtil.getJsonString(jo, "contractId");
		reviewOfPeople = JsonUtil.getJsonString(jo, "operater");
		supplierName = JsonUtil.getJsonString(jo, "supplierName");
		auditStatus = JsonUtil.getJsonString(jo, "status");
		processId = JsonUtil.getJsonString(jo, "processId");
		processNode = JsonUtil.getJsonString(jo, "processNode");

	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<ContractIntroduction> getList(String jsonStr) {
		ArrayList<ContractIntroduction> list = new ArrayList<ContractIntroduction>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				ContractIntroduction contract = new ContractIntroduction();
				contract.parser(jo);
				list.add(contract);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getReviewOfPeople() {
		return reviewOfPeople;
	}

	public void setReviewOfPeople(String reviewOfPeople) {
		this.reviewOfPeople = reviewOfPeople;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessNode() {
		return processNode;
	}

	public void setProcessNode(String processNode) {
		this.processNode = processNode;
	}

}
