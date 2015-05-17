package com.shyc.yc_audit.util;

import com.shyc.yc_audit.R;

import sxp.android.framework.manager.ActivityManager;
import sxp.android.framework.view.CustomDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
/**
 * 显示工具类
 * @author xiaoping.shan
 *
 */
public class ShowUtil {
	
	/* 加载布局文件 */
	public static View LoadXmlView(Context context, int xmlId) {
		LayoutInflater flat = LayoutInflater.from(context);
		View view = flat.inflate(xmlId, null);
		return view;
	}

	/* 开启httpDialog */
	private static CustomDialog httpDialog = null;

	public static void openHttpDialog(String showText) {

		httpDialog = getCustomDialog(ActivityManager.getInstance().peek(),
				R.layout.httpdialog, 0, R.style.Dialog);
		((TextView) httpDialog.getcView().findViewById(
				R.id.httpdialog_show_text)).setText(showText);
		httpDialog.setCanceledOnTouchOutside(false);
		httpDialog.show();

	}

	/* 关闭dialog */
	public static void closeHttpDialog() {
		if (httpDialog != null) {
			httpDialog.dismiss();
			httpDialog = null;
		}
	}

	/**
	 * 获得自定义的dialog
	 */
	public static CustomDialog getCustomDialog(Context context, int layoutId,
			int type, int dialogType) {
		View contentView = LoadXmlView(context, layoutId);
		CustomDialog customDialog = new CustomDialog(context, contentView,
				dialogType);
		customDialog.setType(type);
		return customDialog;
	}

}
