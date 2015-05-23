package com.shyc.yc_audit.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
/**
 * 安全用户
 * @author xiaoping.shan
 *
 */
public class SecuUser implements BaseData{
	private String age;
	private String answer;
	private String checkor;
	private String checktime;
	private String creator;
	private String date1;
	private String date2;
	private String department;
	private String email;
	private String flag1;
	private String flag2;
	private String id;
	private String mobile;
	private String modifyor;
	private String modifytime;
	private String name;
	private String notes;
	private String orgid;
	private String orgsid;
	private String password;
	private String phone;
	private String position;
	private String question;
	private String realname;
	private String sex;
	private String status;
	private String txt1;
	private String txt2;
	private String type;
	private String usercode;
	
//	"secuUser":{
//		"age":30,
//		"answer":"",
//		"checkor":"",
//		"checktime":null,
//		"createtime":{"date":4,"day":3,"hours":15,"minutes":45,"month":2,"nanos":0,"seconds":51,"time":1425455151000,"timezoneOffset":-480,"year":115},
//		"creator":"",
//		"date1":null,
//		"date2":null,
//		"department":"江西正邦科技股份有限公司",
//		"email":"s@s.com",
//		"flag1":0,
//		"flag2":0,
//		"id":158,
//		"mobile":"13311111111",
//		"modifyor":"",
//		"modifytime":null,
//		"name":"1001",
//		"notes":"",
//		"orgid":0,
//		"orgsid":3,
//		"password":"1bbd886460827015e5d605ed44252251",
//		"phone":"11111",
//		"position":"小料采购总经理",
//		"question":"",
//		"realname":"祝建霞",
//		"sex":2,
//		"status":1,
//		"txt1":"",
//		"txt2":"",
//		"type":1,
//		"usercode":""}}

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		
	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

}
