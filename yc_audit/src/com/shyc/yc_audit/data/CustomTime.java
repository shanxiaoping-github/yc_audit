package com.shyc.yc_audit.data;

import org.json.JSONObject;

import sxp.android.framework.data.BaseData;
import sxp.android.framework.util.JsonUtil;

/**
 * 自定义时间
 * 
 * @author xiaoping.shan
 *
 */
public class CustomTime implements BaseData {


	private static final long serialVersionUID = 1L;
	private String data;
	private String day;
	private String hours;
	private String minutes;
	private String month;
	private String nanos;
	private String seconds;
	private String time;
	private String timezoneOffset;
	private String year;

	public void parser(JSONObject jo) {
		// TODO Auto-generated method stub
		data = JsonUtil.getJsonString(jo,"data");
		day = JsonUtil.getJsonString(jo,"day");
		hours = JsonUtil.getJsonString(jo,"hours");
		minutes = JsonUtil.getJsonString(jo,"minutes");
		month = JsonUtil.getJsonString(jo,"month");
		nanos = JsonUtil.getJsonString(jo,"nanos");
		seconds = JsonUtil.getJsonString(jo,"seconds");
		time = JsonUtil.getJsonString(jo,"time");
		timezoneOffset = JsonUtil.getJsonString(jo,"timezoneOffset");
		year = JsonUtil.getJsonString(jo,"year");

	}

	public JSONObject page() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getNanos() {
		return nanos;
	}

	public void setNanos(String nanos) {
		this.nanos = nanos;
	}

	public String getSeconds() {
		return seconds;
	}

	public void setSeconds(String seconds) {
		this.seconds = seconds;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTimezoneOffset() {
		return timezoneOffset;
	}

	public void setTimezoneOffset(String timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
