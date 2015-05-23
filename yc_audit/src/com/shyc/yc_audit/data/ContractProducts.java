package com.shyc.yc_audit.data;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 合同产品
 * 
 * @author xiaoping.shan
 *
 */
public class ContractProducts implements BaseData {

	// "contractId": "ZBQC20150423104340",
	// "date1": null,
	// "date2": null,
	// "flag": 0,
	// "flag2": 0,
	// "id": 59,
	// "material": "",
	// "notes": "",
	// "num1": 120,
	// "num2": 120,
	// "productid": "31011602",
	// "productname": "维生素类",
	// "quantity": 120,
	// "specifications": "",
	// "total": 1484.448,
	// "txt1": "",
	// "txt2": "",
	// "txt3": "",
	// "txt4": "",
	// "unit": "KG",
	// "unitprice": 12.3704

	private String contractId;
	private String productid;
	private String productname;
	private String quantity;
	private String total;
	private String unit;
	private String unitprice;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		contractId = JsonUtil.getJsonString(jo, "contractId");
		productid  = JsonUtil.getJsonString(jo, "productid");
		productname = JsonUtil.getJsonString(jo, "productname");
		quantity = JsonUtil.getJsonString(jo, "quantity");
		total = JsonUtil.getJsonString(jo, "total");
		unit = JsonUtil.getJsonString(jo, "unit");
		unitprice = JsonUtil.getJsonString(jo, "unitprice");

	}
	
	
	public static ArrayList<ContractProducts> getList(String jsonStr){
		ArrayList<ContractProducts> list  = new ArrayList<ContractProducts>();
		try {
			JSONArray ja = new JSONArray(jsonStr);
			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				ContractProducts contractProducts = new ContractProducts();
				contractProducts.parser(jo);
				list.add(contractProducts);
				
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
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

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUnitprice() {
		return unitprice;
	}

	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}

}
