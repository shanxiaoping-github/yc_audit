package com.shyc.yc_audit.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;

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
	 * 送审时间
	 */
	private String reviewTime;
	/**
	 * 初审状态
	 */
	private String reviewStatus;
	/**
	 * 审核状态
	 */
	private String auditStatus;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub

	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
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

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewStatus() {
		return reviewStatus;
	}

	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

}
