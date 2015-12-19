package com.shyc.yc_audit.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;
/**
 * 审核信息
 * @author shanxiaoping
 *
 */
public class CheckInfo implements BaseData{
	private static final long serialVersionUID = 1L;
	private String rolename;//审核岗位
	private String realname;//审核人姓名
	private String opinion;//已审核意见
	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		rolename = JsonUtil.getJsonString(jo,"rolename");
		realname = JsonUtil.getJsonString(jo,"realname");
		opinion = JsonUtil.getJsonString(jo,"opinion");
	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	

}
