package com.shyc.yc_audit.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;
/**
 * 用户
 * @author xiaoping.shan
 *
 */
public class UserInfo implements BaseData{
	private String id;
	private String userName;
	private String realName;
	

//	
//	{"userInfo":{"id":158,"userName":"1001","realName":"祝建霞"},
//	
//	"status":0,
//	"secuUser":{"age":30,"answer":"","checkor":"","checktime":null,"createtime":{"date":4,"day":3,"hours":15,"minutes":45,"month":2,"nanos":0,"seconds":51,"time":1425455151000,"timezoneOffset":-480,"year":115},"creator":"","date1":null,"date2":null,"department":"江西正邦科技股份有限公司","email":"s@s.com","flag1":0,"flag2":0,"id":158,"mobile":"13311111111","modifyor":"","modifytime":null,"name":"1001","notes":"","orgid":0,"orgsid":3,"password":"1bbd886460827015e5d605ed44252251","phone":"11111","position":"小料采购总经理","question":"","realname":"祝建霞","sex":2,"status":1,"txt1":"","txt2":"","type":1,"usercode":""}}
//	
	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		id = JsonUtil.getJsonString(jo,"id");
		userName = JsonUtil.getJsonString(jo,"userName");
		realName = JsonUtil.getJsonString(jo,"realName");
		
		
	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}
